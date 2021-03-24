package inf112.skeleton.app;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


abstract class NetworkComponent{
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public void sendString(String s, Socket socket){
        try{
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            out.writeUTF(s);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public String receiveString(Socket socket){
        String s = "";
        try{
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            s = in.readUTF();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return s;
    }

    public void sendInputs(playerInputs inputs, Socket socket){
        sendString(inputs.getInputString(), socket);
    }

    public playerInputs receiveInputs(Socket socket){
        return new playerInputs(receiveString(socket));
    }

    abstract ArrayList<playerInputs> communicateToPlayers(playerInputs inputs);

}

class Client extends NetworkComponent {
    private Socket socket = null;
    private int playerNr = 0;
    private long seed = 0;
    Client(String ip, int port)  {
        try{
            socket = new Socket(ip,port);
            playerNr = Integer.parseInt(receiveString(socket));
            seed = Long.valueOf(receiveString(socket));
        }
        catch(IOException i){
            System.out.print(i);
        }
    }

    public int getPlayerNr(){
        return playerNr;
    }

    public long getSeed(){
        return seed;
    }

    @Override
    ArrayList<playerInputs> communicateToPlayers(playerInputs inputs) {
        String s = receiveString(socket); // wait for confirmation
        ArrayList<playerInputs> pInputs = new ArrayList<playerInputs>();
        if(s.equals("v")){ // got confirmation to send inputs
            sendInputs(inputs,socket);
            String numberOfPlayers = receiveString(socket);// get the number of player inputs that will be sent
            if(numberOfPlayers.equals("v")){ // if you recieve a ping afterwards
                numberOfPlayers = receiveString(socket);
            }
            for(int i = 0; i < Integer.parseInt(numberOfPlayers); i++){
                pInputs.add(receiveInputs(socket));//get inputs from players
            }
        }
        return pInputs;
    }
}


class Host extends NetworkComponent{
    private ArrayList<Socket> sockets = new ArrayList<Socket>();
    private ServerSocket server = null;
    ArrayList<playerInputs> pInputs = new ArrayList<playerInputs>();
    int playerToCheck = 0;

    Host(int port, long seed) {
        try{
            server = new ServerSocket(port);
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("host ip is:"+ip);
            sockets.add(server.accept());
            System.out.println("CONNECTED");
            sendString(String.valueOf(sockets.size()),sockets.get(sockets.size()-1)); // send player number
            sendString(String.valueOf(seed), sockets.get(sockets.size()-1)); // send seed
        }
        catch(IOException i){
            System.out.print(i);
        }
    }
    class receiveInputsThread extends Thread{
        @Override
        public void run(){
            pInputs.add(receiveInputs(sockets.get(playerToCheck)));
        }
    }

    @Override
    ArrayList<playerInputs> communicateToPlayers(playerInputs inputs) {
        playerToCheck = 0;
        pInputs.clear();
        for(int i = 0; i < sockets.size(); i++){
            receiveInputsThread thread = new receiveInputsThread();
            thread.start(); // starts a thread that listens for a players input
            while(thread.isAlive()){ // while waiting for inputs, send a message to send inputs every 100ms
                sendString("v",sockets.get(i));
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
           playerToCheck++;
        }
        for(int i = 0; i < sockets.size();i++){ // sending all inputs back to each player
            sendString(Integer.toString(pInputs.size()),sockets.get(i));
            sendInputs(inputs,sockets.get(i)); // send host inputs
            for(int j = 0; j < pInputs.size(); j++){
                if(i != j){ // not to send the inputs back to its owner
                    sendInputs(pInputs.get(j),sockets.get(i));
                }
            }

        }

        return pInputs;
    }


}




