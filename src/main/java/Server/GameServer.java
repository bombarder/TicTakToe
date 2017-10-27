package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class GameServer {

    private static ServerSocket serverSocket;
    private static int portNumber = 4444;
//    private static BlockingQueue clientsQueue = null;
//    static ArrayList<ClientThread> clients;

    GameServer() {
        try {
            serverSocket = new ServerSocket(portNumber);
            acceptClients();
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + portNumber);
            System.exit(1);
        }
    }

    private static void acceptClients() {
//        clients = new ArrayList<ClientThread>();
        while (true){
            try {
                Socket socket = serverSocket.accept();
//                ClientThread clientThread = new ClientThread(socket);
//                Thread thread = new Thread(clientThread);
//                thread.start();
//                clients.add(clientThread);
            } catch (IOException e){
                System.out.println("Accept failed on: " + portNumber);
            }
        }
    }
}
