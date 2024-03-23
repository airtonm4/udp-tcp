package com.tcp.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5001);
        System.out.println("Server is running and listening for clients...");

        Socket clientSocket = serverSocket.accept();

        String clientSocketIP = clientSocket.getInetAddress().toString();
        int clientSocketPort = clientSocket.getPort();

        System.out.println("Client connected from " + clientSocketIP + ":" + clientSocketPort);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            String clientMessage = dataInputStream.readUTF();
            if (!clientMessage.isEmpty()) {
                System.out.println("Client says: " + clientMessage);
            }

            String cmd = scanner.nextLine();
            dataOutputStream.writeUTF(cmd);

            if (cmd.equals("QUIT")) {
                dataInputStream.close();
                dataOutputStream.close();
                clientSocket.close();
                serverSocket.close();
                System.exit(1);
                scanner.close();
            }
        }

    }
}
