package network.lab5;
import java.io.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class FileThreadPool implements Runnable {
    int num;
    @Override
    public void run()  {
        System.out.println(num*num);
        try {
            Thread.sleep(1000);
         } catch (Exception e) {
         }
    }
    public FileThreadPool(int num) {
        this.num = num;
     }
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3);
        try {
            String msg = "";
            File Rfile = new File(args[0]);

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(Rfile)));
            
            while((msg = br.readLine()) != null){
                FileThreadPool ftp = new FileThreadPool(Integer.parseInt(msg));  
                es.execute(ftp);
            }
            es.shutdown();
            br.close();
        } catch (Exception e) {}


    }


}
