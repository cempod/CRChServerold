import java.io.*;
import java.net.SocketTimeoutException;

public class ConnectionThread extends Thread{

    private ConnectedClient connectedClient;
    private BufferedReader in;
    private BufferedWriter out;

    private Messenger messenger;

    ConnectionThread(ConnectedClient connectedClient, Messenger messenger){
        this.connectedClient = connectedClient;
this.messenger = messenger;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(connectedClient.getClientSocket().getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(connectedClient.getClientSocket().getOutputStream()));
            messenger.addClient(connectedClient);
        } catch (IOException e) {
            e.printStackTrace();
        }



        while (connectedClient.getClientSocket().isConnected() ){
            String word = null;
            try {
                word = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            if (word != null) {
                System.out.println("Сообщение от клиента: "+word);
                messenger.send(word);
                }
            }
        System.out.println("Клиент отвалился");
        }




}
