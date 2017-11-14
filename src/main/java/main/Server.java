package main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private int portNumber = 2004;
    private Socket connection = null;
    private ObjectInputStream inputFromClient;
    private ObjectOutputStream outputToClient;

    Server() {
        try {
            serverSocket = new ServerSocket(portNumber);
            System.out.println("waiting for client request");
            connection = serverSocket.accept();
            System.out.println("Connection received from " + connection.getInetAddress().getHostName());
            outputToClient = new ObjectOutputStream(connection.getOutputStream());
            inputFromClient = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectOutputStream getOutputToClient() {
        return outputToClient;
    }

    ServerSocket getServerSocket() {
        return serverSocket;
    }

    public ObjectInputStream getInputFromClient() {
        return inputFromClient;
    }

    void setInputFromClient(ObjectInputStream inputFromClient) {
        this.inputFromClient = inputFromClient;
    }
}
