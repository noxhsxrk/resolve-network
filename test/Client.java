
/*

- รับค่า argument จากผู้ใช้ เป็นคำสั่ง และค่าตัวเลขจำนวนเต็มจาก Client (สามารถรับได้ตั้งแต่ 1 ค่าขึ้นไป)
- คำสั่งมี 2 คำสั่งคือ sum และ mode
- Client จะส่งคำสั่งแล้วค่าตัวเลขจำนวนเต็มไปยัง Server เพื่อคำนวณผลลัพธ์ แล้วรับผลลัพธ์จาก Server มาแสดงออกบนหน้าจอ

- ถ้าผู้ใช้ป้อน argument ตัวที่ 1 ไม่ใช่ “mode” และ “sum” ให้แสดง “Command not found”

  ถ้าผู้ใช้ป้อนคำสั่งถูกแต่ไม่มี argument ตัวที่ 2 เป็นต้นไป ให้แสดง “Data not found”

  ถ้าผู้ใช้ไม่ป้อน argument หรือค่าของ argument ตัวที่ 2 เป็นต้นไป ไม่เป็นจำนวนเต็มให้แสดง “Error Args” 
  
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String command = args[0].toLowerCase();
        try{
        Socket s = new Socket("127.0.0.1",55555);
            
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        // Check a command if Mode or Sum or not
        if(!command.equals("mode") && !command.equals("sum")){
            System.out.println("Command not found");
            System.exit(1);
        }

        try{
            for( int i = 1; i < args.length; i++){
                int n = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error Args");
            System.exit(1);
        }
        if(command.equals("sum")){
           if(args.length < 2){
               //Data not found
                System.out.println("Data not found");
                System.exit(1);
            }
            else{
                pw.println(command);
                for(int i = 1; i < args.length; i++){
                    pw.println(args[i]);
                }
                pw.println("end");
            }
        }
        else if(command.equals("mode")){
            if(args.length < 2){
               //Data not found
                System.out.println("Data not found");
                System.exit(1);
            }
            else{
                pw.println(command);
                for(int i = 1; i < args.length; i++){
                    pw.println(args[i]);
                }
                pw.println("end");
            }
        } 

        pw.flush();
        BufferedReader br = new BufferedReader(
                                new InputStreamReader(
                                s.getInputStream()));
        String msg = br.readLine();
        System.out.println(msg);
        
        }catch(Exception e){
            
        }
    }
}
