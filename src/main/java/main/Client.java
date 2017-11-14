package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private ObjectOutputStream toServer;
    private ObjectInputStream fromServer;

    Client() {
        try {
            clientSocket = new Socket("localhost", 2004);
            System.out.println("Connected to localhost fromServer port 2004");
            toServer = new ObjectOutputStream(clientSocket.getOutputStream());
            toServer.flush();
            fromServer = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setToServer(ObjectOutputStream toServer) {
        this.toServer = toServer;
    }

    public ObjectInputStream getFromServer() {
        return fromServer;
    }

    void setFromServer(ObjectInputStream fromServer) {
        this.fromServer = fromServer;
    }

    public ObjectOutputStream getOutputToServer() {
        return toServer;
    }
}

