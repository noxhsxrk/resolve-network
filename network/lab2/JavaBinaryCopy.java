package network.lab2;
import java.io.*;
public class JavaBinaryCopy {
    public static void main(String[] args) {
        try {
            if(args.length == 0 || args.length < 2){
                throw new Exception();
            }
            int n;
            File Rfile = new File(args[0]);
            if(!Rfile.exists()){
                throw new Exception();
            }
            File Wfile = new File(args[1]);
            FileInputStream reader = new FileInputStream(Rfile); 
            FileOutputStream writer = new FileOutputStream(Wfile) ;
            while((n = reader.read()) != -1){
                writer.write(n);
            }
            writer.close();
            reader.close();

        } catch (Exception e) {
            System.out.println("Usage : java JavaBinaryCopy <source file> <destination file>");
        }
    }
}
