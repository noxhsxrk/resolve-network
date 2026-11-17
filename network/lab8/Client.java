import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try{
            Socket s = new Socket("127.0.0.1",23410);
            BufferedReader br = new BufferedReader(
                                new InputStreamReader(
                                s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            
            if((args.length == 3) && args[0].equals("add") )
            {
                pw.print(args[0]+'\n'+args[1]+'\n'+args[2]+'\n');
            } 

            else if((args.length == 2) && args[0].equals("search") ) 
            {
            pw.print(args[0]+"\n"+args[1]+'\n');
            }
            
            else {
                System.out.println("Command not found");
                System.exit(1);
            }
            pw.flush();
            
            String msg = br.readLine();
            System.out.println(msg);
            
            br.close();
            pw.close();
            s.close();
        }catch(Exception e){
            System.out.println("Client Error");
            System.exit(1);
        }
    }
}