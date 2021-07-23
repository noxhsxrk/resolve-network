package network.lab3;

public class JavaThread extends Thread {
    int time;

    public JavaThread(int t){
        time = t;
    }

    @Override
    public void run() {
        for(int i = 0 ; i<time ; i++){
            System.out.println(i + " HelloWorld");
        }
    }
    public static void main(String[] args) {
        JavaThread t = new JavaThread(Integer.parseInt(args[0]));
        t.start();
    }
}
