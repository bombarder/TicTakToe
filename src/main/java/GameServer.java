
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer implements ClientServerInteraction {

    private ServerSocket serverSocket;
    private int portNumber = 2004;
    private Socket connection = null;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String message;

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

    @Override
    public int send(int output) throws IOException {
        oos.flush();
        oos.writeObject("Bye Bye");
        oos.flush();
        oos.close();
        return 0;
    }

    @Override
    public void receive(int input) throws IOException, ClassNotFoundException {
        message = (String) ois.readObject();
        System.out.println("client > " + message);
        ois.close();
    }
}
