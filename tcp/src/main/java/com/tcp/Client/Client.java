package com.tcp.Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Connecting to server....");
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("localhost", 5001), 1000);
        System.out.println("Connected to server.");

        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("Type a message to send to the server.");
        System.out.println("Type QUIT to exit.");

        while (true) {

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String serverMessage = dataInputStream.readUTF();
            System.out.println("Broke here 1");
            if (!serverMessage.isEmpty()) {
                System.out.println("Server says: " + serverMessage);
            }
            System.out.println("Broke here 2");

            String cmd = scanner.nextLine();
            dataOutputStream.writeUTF(cmd);

            if (cmd.equals("QUIT")) {
                System.exit(1);
                scanner.close();
                dataInputStream.close();
                dataOutputStream.close();
                socket.close();
            }

        }

    }
}
