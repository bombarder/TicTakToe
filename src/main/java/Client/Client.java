package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread{
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String message;

    @Override
    public void run() {
        try {
            clientSocket = new Socket("localhost", 2004);
            System.out.println("Connected to localhost in port 2004");
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());
            do {
                try {
                    message = (String) in.readObject();
                    System.out.println(" server> " + message);
                    sendMessage("Hi my server");
                    message = "bye";
                    sendMessage(message);
                } catch (ClassNotFoundException classNot) {
                    System.err.println("data received in unknown format");
                }
            } while (!message.equals("bye"));
        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("client > " + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

