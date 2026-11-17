import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{
    Socket s = null;
    
    public static HashMap map;

    public Server(Socket s) {
        this.s = s;
    }
    
    public static void main(String[] args) {
        ServerSocket serv = null;
        ExecutorService es = Executors.newFixedThreadPool(15);
        map = new HashMap();
        
        try{
            serv = new ServerSocket(23410);
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
            String check = br.readLine();
            if(check.equals("add"))
            {
                String i = br.readLine();
                String j = br.readLine();
                Server.map.put(i, j);
                pw.println("OK");
            }
            else if(check.equals("search"))
            {
                String i = br.readLine();
                if(Server.map.get(i) == null)
                    pw.println("N/A");
                else pw.println(Server.map.get(i));
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
