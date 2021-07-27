# Thread ในจาวา (2)

![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)

```java
จงเขียนโปรแกรมเพื่อแสดงการแก้ไข Dining Philosophers 
โดยกาหนดให้iรับจานวนเต็ม n (int) จากผู้ใช้ เพื่อสร้าง Philosopher n คน และส้อม n อัน 
นั่งกินอาหารอยู่บนโต๊ะกลม 

โดยกาหนดให้ต้องเขียนทั้งหมด 3 คลาส : Philosopher, Fork และ DiningPhilosophers (เป็น main class)
```

#### Fork.java

```java
public class Fork {
    volatile boolean taked = false; // เก็บสถานะว่าส้อมนี้ถูกใช้งานอยู่หรือไม่
    String holderName = " "; // เก็บชื่อ Philosopher ที่ใช้งานส้อมนี้อยู่

    public String getHolderName() {
        return holderName;
    }

    // Philosopher เรียกใช้เมื่อต้องการใช้ส้อม ถ้าส้อมไม่ว่างให้คืนค่า false
    // ถ้าส้อมว่างจะต้องตั้งค่า taked และใส่ชื่อคนที่จะใช้ส้อม และคืนค่า true
    // ว่าสามารถหยิบส้อมมาใช้งานได้
    public synchronized boolean take(String holderName) {
    }

    // Philosopher จะเรียกใช้เมื่อต้องการเลิกใช้งานส้อมนี้
    public synchronized void putDown() {
    }
}
```

#### Philosopher.java

```java
import java.util.*;

public class Philosopher extends Thread {
    Fork left, right; // อ้างอิงถึง object ของส้อมด้านซ้ายและด้านขวาของ Philosopher
    String name; // ชื่อของ Philosopher
    String status = "---"; // สถานะของ Philosopher
    int numEat = 0;
    boolean done = false;
    Random r = new Random();

    public Philosopher(String name, Fork left, Fork right) { // รับชื่อ Philosopher และ object ของส้อมซ้าย-ขวา
        this.left = left;
        this.right = right;
        this.name = name;
    }

    public String getStatus() {
        return status;
    } // เรียกเพื่อคืนค่าสถานะของ Philosopher

    public int getNumEat() {
        return numEat;
    } // เรียกเพื่อคืนค่าจานวนครั้งที่ Philosopher ได้กินอาหาร

    public void done() {
        done = true;
    } // เรียกเพื่อหยุดการทางานของ Philosopher

    public void think() { // เรียกเพื่อให้ Philosopher อยู่ในสถานะ คิด (THK)
        status = "THK";
        try {
            Thread.sleep(r.nextInt(150) + 150);
        } catch (Exception e) {
        }
    }

    public void eat() { // เรียกเพื่อให้ Philosopher อยู่ในสถานะ กิน (EAT)
        status = "EAT";
        try {
            Thread.sleep(r.nextInt(150) + 150);
        } catch (Exception e) {
        }
    }

    // Philosopher จะเริ่มต้นที่คิด เมื่อคิดเสร็จจะหยิบส้อม เมื่อได้ส้อม 2
    // อันถึงจะสามารถกินได้ เมื่อกินสาเร็จให้เพิ่มค่า numEat
    public void run() {
        while (!done) {
        }
    }
}
```

#### DiningPhilosophers.java

```java
public class DiningPhilosophers {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Fork[] fork = new Fork[n]; // สร้าง Array สาหรับ Object ของส้อม n อัน
        Philosopher[] philosopher = new Philosopher[n]; // สร้าง Array Object ของ Philosopher n คน
        // ใส่ code ตรงนี้เพื่อสร้าง Object ของส้อม และ Philosopher ให้ชื่อของ
        // Philosopher คือ P0, P1, … ตามลาดับ
        // ส่วนแสดงผลทุกๆ 100 ms เป็นจานวน 100 ครั้งก่อนให้จบโปรแกรม
        String info;
        for (int i = 0; i < 100; i++) {
            info = "|";
            for (int j = 0; j < n; j++) {
                info += (philosopher[j].getStatus() + "|");
            }
            info += "\t\t|";
            for (int j = 0; j < n; j++) {
                info += (fork[j].getHolderName() + "|");
            }
            System.out.println(info);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
        for (int i = 0; i < n; i++) {
            philosopher[i].done();
            System.out.println("P" + i + ": eat " + philosopher[i].getNumEat() + " times");
        }
    }
}

```

