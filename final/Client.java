/* 
- Client ส่งคำสั่งการคำนวณเดือนดังนี้ => java Client  ชื่อเดือน  เลขจำนวนเต็ม

- Client จะส่งข้อความที่รับมาจาก argument (args[0] และ args[1]) ไปยัง Server

- ถ้าผู้ใช้เรียกใช้ Client โดยไม่ได้ป้อน argument ครบ 2 ตัวให้แสดงว่า “incorrect command”

- โดยชื่อเดือนที่ผู้ใช้ป้อน ต้องเป็นคำย่อเดือน 12 เดือน ไม่สนใจตัวพิมพ์ใหญ่หรือพิมพ์เล็ก คือ
    "JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"

- ถ้าผู้ใช้เรียกใช้ Client โดยไม่ได้ป้อนชื่อเดือนตรงตามที่กำหนด ให้แสดงว่า “illegal month abbreviation”

- ถ้าผู้ใช้เรียกใช้ Client ไม่ได้ป้อนเลขเป็นจำนวนเต็ม ให้แสดงว่า “invalid number”

ตัวอย่างของคำสั่ง   C:\Final>java Client jAn -2  หมายถึง สองเดือนก่อนหน้าคือเดือนอะไร
                             C:\Final>java Client APR 3  หมายถึง สามเดือนถัดไปคือเดือนอะไร
- Client รับคำตอบจาก Server แล้ว แสดงชื่อเดือนออกทางหน้าจอ 
*/

import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            byte[] sendBuffer;
            byte[] recvBuffer = new byte[512];
            int port = 55555;
            InetAddress ia = InetAddress.getByName("127.0.0.1");

            DatagramSocket s = new DatagramSocket();

            if (args.length != 2) {
                System.out.println("incorrect command");
                System.exit(0);
            }
            

            String inputMonth = args[0];
            String inputNumber = args[1];

            if (getNumMonth(inputMonth.toLowerCase()) == ""){
                System.out.println("illegal month abbreviation");
                System.exit(0);
            }

            if (isInteger(inputNumber) == false){
                System.out.println("invalid number");
                System.exit(0);
            }

            sendBuffer = inputMonth.getBytes();
            DatagramPacket sendDp = new DatagramPacket(sendBuffer, sendBuffer.length, ia, port);
            s.send(sendDp);

            sendBuffer = inputNumber.getBytes();
            sendDp = new DatagramPacket(sendBuffer, sendBuffer.length, ia, port);
            s.send(sendDp);

            DatagramPacket recvDp = new DatagramPacket(recvBuffer, recvBuffer.length);
            s.receive(recvDp);

            String t = new String(recvDp.getData(), 0, recvDp.getLength());
            System.out.println(t);
            s.close();

        } catch (Exception e) {

        }
    }

    // Check if the input is month
    public static String getNumMonth(String month) {
        String[] months = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };
        for (int i = 0; i < months.length; i++) {
            if (month.equals(months[i])) {
                return String.valueOf(i + 1);
            }
        }
        return "";
    }


    // cCeck if the input is integer
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}
