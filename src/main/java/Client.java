import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements ClientServerInteraction {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String message;

    Client() {
        try {
            clientSocket = new Socket("localhost", 2004);
            System.out.println("Connected to localhost in port 2004");
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int send(int output) throws IOException {
        try {
            out.writeObject("Hi");
            out.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return 0;
    }

    @Override
    public void receive(int input) throws IOException, ClassNotFoundException {
        message = (String) in.readObject();
        System.out.println(" server> " + message);
        message = "bye";
    }
}

