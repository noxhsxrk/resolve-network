/*
- Server เพื่อเขียนโปรแกรมคำนวณเดือนถัดไปหรือเดือนก่อนหน้า ที่รับข้อมูลมาจากตัว Client
- Server ส่งคำย่อเดือนที่คำนวณได้ กลับไปที่ Client เครื่องที่ส่งข้อมูลมาให้
- เมื่อส่งไปแล้วให้แสดงข้อความที่ฝั่ง Server ถ้าเป็นเดือนถัดไป แสดงคำว่า “Future” ถ้าเป็นเดือนก่อนหน้า แสดงคำว่า “Past”
*/

import java.net.*;
import java.util.Date;
public class Server {

    static String[] months = {"jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
    public static void main(String[] args) {
        
        try {
            DatagramSocket s = new DatagramSocket(55555);
            while(true){
                Date now = new Date();
                byte[] sendBuff = now.toString().getBytes();
                byte[] recvBuff = new byte[8000];
                
                // Cretae a datagram packet to receive "MONTH" from a client
                DatagramPacket dp = new DatagramPacket(recvBuff,recvBuff.length);
                s.receive(dp);
                String month = new String(dp.getData(),0,dp.getLength()).toLowerCase();

                // Find the index of the month
                int monthNum = Integer.parseInt(getNumMonth(month));

                // Cretae a datagram packet to receive "NUMBER" from a client
                dp = new DatagramPacket(recvBuff,recvBuff.length);
                s.receive(dp);
                String number = new String(dp.getData(),0,dp.getLength());

                //get the next or previous month
                String output = getMonth(Integer.parseInt(number), monthNum);

                // Create a buffer of OUTPUT to send the output to the client
                sendBuff = output.toUpperCase().getBytes();

                // Get the period of the month
                String indicate = "";
                if(Integer.parseInt(number)>0){
                    indicate = "Future";
                }else if(Integer.parseInt(number)<0){
                    indicate=  "Past";
                }
                else if(Integer.parseInt(number) == 0){
                    indicate = "Now";
                }

                System.out.println(indicate);

                // Create a datagram packet to send "OUTPUT" to a client
                DatagramPacket dp2 = new DatagramPacket(sendBuff,sendBuff.length,dp.getAddress(),dp.getPort());
                s.send(dp2);
                     
            }
        } catch (Exception e) {
        }
    }

    //return number of month
    public static String getNumMonth(String month){
        for(int i=0;i<months.length;i++){
            if(month.equals(months[i])){
                return String.valueOf(i+1);
            }
        }
        return "";
    }

    //return the month before or after the input number
    public static String getMonth(int number,int monthNum){
        number = number % 12;
            if(number>0){
                monthNum = monthNum+number;
                if(monthNum>12){
                    monthNum = monthNum-12;
                }
            }else{
                monthNum = monthNum+number;
                if(monthNum<1){
                    monthNum = monthNum+12;
                }
            }
        return months[monthNum-1];
    }
}

