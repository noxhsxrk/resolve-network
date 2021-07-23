package network.lab3;

public class JavaTwoThread implements Runnable {

    long sleep;
    int begin;
    int last;
    int result = 0;

    public JavaTwoThread(long sleep, int begin, int last) {
        this.sleep = sleep;
        this.begin = begin;
        this.last = last;
    }

    @Override
    public void run() {
        for (int i = begin; i <= last; i++) {
            result += i;
        }
        try {
            Thread.sleep(sleep);
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {

        JavaTwoThread first = new JavaTwoThread(5, 1, 5000);
        JavaTwoThread second = new JavaTwoThread(10, 5001, 10000);

        Thread firstThread = new Thread(first);
        Thread secondThread = new Thread(second);

        firstThread.start();
        secondThread.start();

        try {

            firstThread.join(0);
            secondThread.join(0);

            System.out.println("sum = " + (first.result + second.result));

        } catch (Exception e) {

        }

    }
}
