package network.lab1;
public class TestArgs{
    public static void main(String[] args) { 
        try {
            float sum = 0;
            sum = Float.parseFloat(args[0])*Float.parseFloat(args[1]);
            System.out.println(sum);
        } 
        catch (Exception e) {
            System.out.println("Usage:Java TestArgs <number1> <number2>");
        }

    }
}