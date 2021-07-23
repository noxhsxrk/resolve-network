package network.lab4.philosophers;

import java.util.*;

public class Philosopher extends Thread {
    Fork left, right; 
    String name;
    String status = "---";
    int numEat = 0;
    boolean done = false;
    Random r = new Random();

    public Philosopher(String name, Fork left, Fork right) { 
        this.left = left;
        this.right = right;
        this.name = name;
    }

    public String getStatus() {
        return status;
    } 

    public int getNumEat() {
        return numEat;
    } 

    public void done() {
        done = true;
    } 

    public void think() { 
        status = "THK";
        try {
            Thread.sleep(r.nextInt(150) + 150);
        } catch (Exception e) {
        }
    }

    public void eat() {
        status = "EAT";
        try {
            Thread.sleep(r.nextInt(150) + 150);
        } catch (Exception e) {
        }
    }

   
    public void run() {
        while (!done) {
            think();
            if (right.take(name) && left.take(name)) {
                eat();
                numEat++;
                right.putDown();
                left.putDown();
            }
        }
    }
}