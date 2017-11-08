import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class Client {
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

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

    void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    ObjectInputStream getIn() {
        return in;
    }

    void setIn(ObjectInputStream in) {
        this.in = in;
    }

    ObjectOutputStream getOut() {
        return out;
    }
}

