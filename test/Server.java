/* 

รับคำสั่งและรับค่าตัวเลขจำนวนเต็มจาก Client (สามารถรับได้ตั้งแต่ 1 ค่าขึ้นไป)             

- ถ้าคำสั่งเป็น  mode ให้คำนวณหาค่าฐานนิยมจากตัวเลขที่รับมา ถ้าไม่มีค่าฐานนิยมให้คืน mode = -1   (3 คะแนน)
    ถ้ามี mode หลายค่า(จำนวนมากสุดเท่ากัน) ให้คืน mode = -1
- ถ้าคำสั่งเป็น sum ให้คำนวณหาค่าผลรวมทั้งหมดจากจากตัวเลขที่รับมา                  (2 คะแนน)
- Server คำนวณค่าเรียบร้อย จะส่งผลลัพธ์กลับไปยัง Client

*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{
    Socket s = null;
    

    public Server(Socket s) {
        this.s = s;
    }
    
    public static void main(String[] args) {
        ServerSocket serv = null;
        ExecutorService es = Executors.newFixedThreadPool(20);

        try{
            serv = new ServerSocket(55555);
        }catch(Exception e){
            System.out.println("Server Error");
            System.exit(1);
        }
        
        while(true){
            try{
                Socket s = serv.accept();
                Server sv = new Server(s);
                es.execute(sv);
            }catch(Exception e){}
        }
    }

    @Override
    public void run() {
        try{
            BufferedReader br = new BufferedReader(
                                new InputStreamReader(
                                s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            String command = br.readLine();
            List<Integer> list = new ArrayList<Integer>();
            String msg;

            if(command.equals("mode"))
            {
   
                while((msg = br.readLine()) != null){
                    if(msg.equals("end"))
                        break;
                    list.add(Integer.parseInt(msg));
                }

                int maxValue = -1, maxCount = 0;

                for (int i = 0; i < list.size(); ++i) {
                    int count = 0;
                    for (int j = 0; j < list.size(); ++j) {
                        if (list.get(j) == list.get(i)) ++count;
                    }
                    if (count > maxCount) {
                        maxCount = count;
                        maxValue = list.get(i);
                    }
                    else if((count == maxCount) && (list.get(i)) != maxValue ){
                        maxValue = -1;
                    }
                }
                    pw.println("Mode = "+maxValue);
                    System.out.println("Server OK");
            }

            else if(command.equals("sum"))
            {
                while((msg = br.readLine()) != null){
                    if(msg.equals("end"))
                        break;
                    
                    list.add(Integer.parseInt(msg));
                }

                int sum = 0;
                for(int num : list){
                    sum += num;
    
                }
               
                pw.println("Sum = "+sum);
                System.out.println("Server OK");

            }
            else{
                pw.println("Command not found");
            }
            
            pw.flush();
            pw.close();
            br.close();
            s.close();

        }catch(Exception e){
            System.out.println("Error");
        }
    }

}

