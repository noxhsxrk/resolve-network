package network.lab6;

import java.io.*;
import java.net.*;

public class PortScan {

    public static void main(String[] args) {
        if(args.length < 1 || args.length > 1){
            System.out.println("Usage: java PortScan <hostname/IP>");
            System.exit(0);
        }

        for (int i = 70; i <= 100; i++) {
            try {
                Socket s = new Socket();
                s.connect(new InetSocketAddress(args[0], i), 100);
                System.out.println("port " + i + ": open");
                s.close();
            } catch (IOException s) {
            }
        }

    }
}
