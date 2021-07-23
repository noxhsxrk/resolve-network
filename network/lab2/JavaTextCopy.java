package network.lab2;
import java.io.*;

public class JavaTextCopy {
    public static void main(String[] args) {
        try {

            if(args.length == 0 || args.length < 2){
                throw new Exception();
            }

            String msg = "";
            File Rfile = new File(args[0]);
            File Wfile = new File(args[1]);

            if(!Rfile.exists()){
                throw new Exception();
            }

            FileOutputStream writer = new FileOutputStream(Wfile);
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(Rfile)));
            PrintWriter pw = new PrintWriter(writer);
            
            while((msg = br.readLine()) != null){
                pw.println(msg);
            }

            pw.flush();
            pw.close();
            br.close();

        } catch (Exception e) {
            System.out.println("Usage : java JavaBinaryCopy <source file> <destination file>");
        }
    }
}
