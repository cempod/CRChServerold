import java.io.IOException;
import java.util.ArrayList;

public class Messenger {
    private ArrayList<ConnectedClient> clients;

    public Messenger(){
        this.clients = new ArrayList<>();
    }
    public void addClient(ConnectedClient connectedClient){
        this.clients.add(connectedClient);
    }
    public void send(String message){

        for(int i = 0; i<clients.size();i++){
            try {
                clients.get(i).getOut().write(message+ "\n");
                clients.get(i).getOut().flush();
            } catch (IOException e) {
                clients.remove(i);
                i--;
                e.printStackTrace();
            }
        }
        System.out.println("Активно клиентов: "+ this.clients.size());
    }

}
