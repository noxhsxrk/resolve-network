import java.io.*;
import java.net.Socket;

public class FileClient {
    public static void main(String[] args) {
        String command = args[0].toLowerCase();
  
        if(command.equals("upload")){

            if(args.length != 2){
                System.out.println("Upload Need 2 Parameter <command>,<file>");
                System.exit(1);
            }

            File f  = new File(args[1]);

            if(!f.exists()){
                System.out.println("File upload not found");
                System.exit(1);
            }

            try{
                Socket s = new Socket("127.0.0.1",6789);
                BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                    s.getInputStream()));
                PrintWriter pw = new PrintWriter(s.getOutputStream());
            
                pw.println("upload"+'\n'+f.getName());
                pw.flush();
            
                String response = br.readLine();
                if(response.equals("OK_UP")){
                    System.out.println("Send new file to Server");

                    FileInputStream fin = new FileInputStream(f);
                    OutputStream out = s.getOutputStream();
                    byte[] buffer = new byte[65535];
                    int size;

                    while((size = fin.read(buffer)) > 0 ){
                        out.write(buffer, 0, size);
                    }

                    out.flush();
                    fin.close();

                    System.out.println("Upload Complete");
                }

                pw.close();
                br.close();
                s.close();

            }catch(Exception e){
                System.out.println("Upload Error");
                System.exit(1);
            }
        }
        else if(command.equals("download")){
            if(args.length != 2){
                System.out.println("Dowload Need 2 Parameter <command>,<file>");
                System.exit(1);
            }
            try{
                File f  = new File("./"+args[1]);
                Socket s = new Socket("127.0.0.1",6789);
                BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                    s.getInputStream()));
                PrintWriter pw = new PrintWriter(s.getOutputStream());

                pw.println("download"+'\n'+f.getName());
                pw.flush();

                String response = br.readLine();

                if(response.equals("OK_DOW")){
                    FileOutputStream fout = new FileOutputStream(f);
                    byte[] b = new byte[65535];
                    int size;

                    while((size = s.getInputStream().read(b)) > 0){
                        fout.write(b,0,size);
                    }

                    fout.flush();
                    fout.close();
                }
                else{
                    System.out.println(f.getName()+" not found on Server");
                    System.exit(1);
                }

                System.out.println("Donwload "+f.getName()+" Complete");

                pw.close();
                br.close();
                s.close();
                
            }catch(Exception e){
                System.out.println("Download Error");
                System.exit(1);
            }
            
        }
        else if(command.equals("list")){
            try{
                Socket s = new Socket("127.0.0.1",6789);
                BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                    s.getInputStream()));
                PrintWriter pw = new PrintWriter(s.getOutputStream());
                
                pw.println("list");
                pw.flush();
                
                String filename;
                while((filename = br.readLine()) != null ){
                    System.out.println(filename);
                }
                br.close();
                s.close();
                
            }catch(Exception e){
                System.out.println("List Error");
                System.exit(1);
            }
        }
        else{
            System.out.println("Command not found");
            System.exit(1);
        }
     
    }
    
}
