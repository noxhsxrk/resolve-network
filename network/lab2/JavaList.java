package network.lab2;
import java.io.*;

public class JavaList {
    public static void main(String[] args) {
        try {
            File file = new File(args[0]);
            if (!file.exists()) {
                throw new Exception("File not Found");
            }
            if(args.length == 0){
                throw new Exception("Usage : JavaList <File/Directory Name>");
            }
            if (file.isDirectory()) {
                for (int i = 0; i < file.listFiles().length; i++)
                    System.out.println(file.listFiles()[i]);
            }
            if (file.isFile()) {
                System.out.println(file.length() + " Bytes");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}