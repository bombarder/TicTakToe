package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread {

    private ServerSocket serverSocket;
    private int portNumber = 2004;
    private Socket connection = null;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String message;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("waiting for client request");
            connection = serverSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            oos = new ObjectOutputStream(connection.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(connection.getInputStream());
            sendMessage("Connection successful");
            do {
                try {
                    message = (String) ois.readObject();
                    System.out.println("client > " + message);
                    if (message.equals("bye"))
                        sendMessage("bye");
                } catch (ClassNotFoundException classnot) {
                    System.err.println("Data received in unknown format");
                }
            } while (!message.equals("bye"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                oos.close();
                serverSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void sendMessage(String msg) {
        try {
            oos.writeObject(msg);
            oos.flush();
            System.out.println("server > " + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
