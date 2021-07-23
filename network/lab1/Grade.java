package network.lab1;
public class Grade{
    public static void main(String[] args) { 
        try {
           int score = Integer.parseInt(args[0]);
           if(score < 0 || score > 100){
            System.out.println("Please enter number 0-100");
           }
           String grade = score > 79 ? "A": score > 69 ? "B": score > 59 ? "C": score > 49 ? "D":"F" ;
           System.out.println(grade);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter your score");
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter integer number");
        }
    }
}