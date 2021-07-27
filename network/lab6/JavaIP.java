package network.lab6;

import java.net.*;
public class JavaIP {

    public static void main(String[] args) {
        if(args.length < 1 || args.length > 1){
            System.out.println("Usage : java JavaIP <hostname/IP>");
            System.exit(0);
        }
        try {
            InetAddress ad = InetAddress.getByName(args[0]);
            System.out.println("Host name : " + ad.getHostName());
            System.out.println("IP Address : " + ad.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("Error: unknown host or IP address");
        }

    }

}
