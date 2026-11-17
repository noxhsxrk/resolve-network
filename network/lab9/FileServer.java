import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileServer implements Runnable{
    Socket s = null;

    public FileServer(Socket s) {
        this.s = s;
    }
    
    public static void main(String[] args) {
        ServerSocket serv = null;
        ExecutorService es = Executors.newFixedThreadPool(10);
        
        try{
            serv = new ServerSocket(6789);
        }catch(Exception e){
            System.out.println("Server Error");
            System.exit(1);
        }
        
        while(true){
            try{
                Socket s = serv.accept();
                FileServer sv = new FileServer(s);
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
            if(command.equals("upload")){

                String filename = br.readLine();

                System.out.println("Request upload :"+filename); 
                File f = new File("./Server_Storage/"+filename);

                pw.println("OK_UP");
                pw.flush();

                FileOutputStream fout = new FileOutputStream(f);
                byte[] b = new byte[65535];
                int size;

                while((size = s.getInputStream().read(b)) > 0){
                    fout.write(b,0,size);
                }

                fout.flush();
                fout.close();
            }
            else if(command.equals("download")){
                String filename = br.readLine();
                System.out.println("Request download :"+filename);
                File f = new File("./Server_Storage/"+filename);

                if(f.exists()){
                    pw.println("OK_DOW");
                    pw.flush();

                    FileInputStream fin = new FileInputStream(f);
                    OutputStream out = s.getOutputStream();
                    byte[] buffer = new byte[65535];
                    int size;

                    while((size = fin.read(buffer)) > 0 ){
                        out.write(buffer, 0, size);
                    }

                    out.flush();
                    fin.close();
                }
                else{
                    pw.println("NOK_DOW");
                    pw.flush();
                }
            }
            else if(command.equals("list")){
                File f = new File("./Server_Storage");
                String[] filename = f.list();

                for(int i = 0; i < filename.length ; i++){
                    pw.println(filename[i]);
                }

                pw.flush();
            }
            
            br.close();
            pw.close();
            s.close();
        }catch(Exception e){
            System.out.println("Error");
        }
    }

}
