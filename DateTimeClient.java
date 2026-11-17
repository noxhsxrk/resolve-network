import java.net.*;

public class DateTimeClient {

    public static void main(String[] args) {
        
        try{
            byte[] sendBuffer; 
            byte[] recvBuffer = new byte[512];
            InetAddress ia = InetAddress.getByName("127.0.0.1");
            
            DatagramSocket s = new DatagramSocket();
            
            String msg = "WhatT";
            sendBuffer = msg.getBytes();
            
            DatagramPacket sendDp = new DatagramPacket(sendBuffer,sendBuffer.length,ia,9876);
            s.send(sendDp);
            
            DatagramPacket recvDp = new DatagramPacket(recvBuffer,recvBuffer.length);
            s.receive(recvDp);
            
            String t = new String(recvDp.getData(),0,recvDp.getLength());
            System.out.println("Server Time --> " + t);
            s.close();
            
        }
        catch(Exception e){
        
        }
    }
    
}
