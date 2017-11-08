import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

    ObjectOutputStream getOos() {
        return oos;
    }

    ServerSocket getServerSocket() {
        return serverSocket;
    }

    ObjectInputStream getOis() {
        return ois;
    }

    void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }
}
