
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class GameServer {

    private ServerSocket serverSocket;
    private int portNumber = 2004;
    private Socket connection = null;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    GameServer() {
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("waiting for client request");
            connection = serverSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            oos = new ObjectOutputStream(connection.getOutputStream());
            ois = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void acceptClients() {
        while (true){
            try {
                Socket socket = serverSocket.accept();
                ois = new ObjectInputStream(socket.getInputStream());
                int result = (int) ois.readObject();
                oos.writeObject(result);
            } catch (IOException e){
                System.out.println("Accept failed on: " + portNumber);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
