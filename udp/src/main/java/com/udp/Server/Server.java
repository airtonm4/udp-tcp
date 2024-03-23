package com.udp.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Timestamp;

public class Server extends Thread {

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(4445);
        byte[] buf = new byte[1042];
        DatagramPacket receivePacket;

        System.out.println("Server is running.");
        while (true) {
            receivePacket = new DatagramPacket(buf, buf.length);
            socket.receive(receivePacket);
            InetAddress address = receivePacket.getAddress();

            int port = receivePacket.getPort();

            String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("[" + timestamp + "] " + address + ":" + port + " - " + clientMessage);
        }

    }
}