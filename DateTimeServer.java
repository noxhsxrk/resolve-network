import java.net.*;
import java.util.Date;

public class DateTimeServer {
    public static void main(String[] args) {

        try {
            DatagramSocket s = new DatagramSocket(9876);
            while(true){
                Date now = new Date();
                byte[] sendBuff = now.toString().getBytes();
                byte[] recvBuff = new byte[8000];
             
                DatagramPacket dp = new DatagramPacket(recvBuff,recvBuff.length);
                s.receive(dp);
                DatagramPacket dp2 = new DatagramPacket(sendBuff,sendBuff.length,dp.getAddress(),dp.getPort());
             
                s.send(dp2);
                     
            }
        } catch (Exception e) {
        }
    }
    
}
