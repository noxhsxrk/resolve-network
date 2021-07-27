# Thread ในจาวา

![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)

#### JavaThread.java

```sh
จงเขียนโปรแกรมโดยให้โปรแกรมต้นฉบับชื่อ JavaThread.java 
ซึ่งโปรแกรมจะรับพารามิเตอร์ 1 ค่าเป็นจานวนเต็ม
จากนั้นให้สร้างโปรแกรมสร้าง Thread ที่เกิดจาก Class ที่ “extends Thread” ขึ้นมาตามจานวนที่ผู้ใช้ป้อนโดยแต่ละ Thread จะพิมพ์หมายเลข Thread 
และคำว่า HelloWorld ตามตัวอย่างข้างล่าง (ผลการรันอาจจะสลับตำแหน่งกันได้)

ผลการรัน : ถ้าป้อนค่าพารามิเตอร์เป็น 3
          0 HelloWorld
          1 HelloWorld
          2 HelloWorld
```

#### JavaTwoThread.java

```sh
จงเขียนโปรแกรมโดยให้โปรแกรมต้นฉบับชื่อ JavaTwoThread.java 
โดยภายในโปรแกรมจะมีการใช้งาน 2 Thread ที่สร้างจาก class ที่ “implements Runnable” ซึ่ง
  • Thread ที่ 1 จะทาการบวกค่าตั้งแต่เลข 1 – 5000 แล้วหลับรอ 5 วินาที
  • Thread ที่ 2 จะทาการบวกค่าตั้งแต่ 5001 – 10000 แล้วหลับรอ 10 วินาที
  • จากนั้น Thread main จะนาผลลัพธ์ของ Thread 1 และ Thread 2 มาบวกกันแล้วแสดงผลลัพธ์ออกทางหน้าจอ
```

#### JavaSyncTest.java

```java
จงเติมช่องว่างของโปรแกรมต่อไปนี้ให้สมบูรณ์ 
โดยโปรแกรมจะมีการสร้าง Thread จานวน 3 Thread แต่ละ Thread จะวนลูปเพิ่มค่าในตัวแปร balance ซึ่งเป็นประเภท static (ทุก Thread จะเห็นตัวแปรนี้) 
เมื่อทั้ง 3 Thread ทางานเสร็จให้แสดงค่าผลบวกออกทางหน้าจอ (ผลลัพธ์จะได้ 300000)

import java.io.*;
public class JavaSyncTest implements __________________________ {
static volatile int balance = 0;
______________ Object o = new ____________________( );
public void run( ) {
for(int i = 0; i < 100000; i++) {
synchronized( _________________ ) { balance++; }
}
}
public int getBalance( ) { return _______________________________ ; }
public static void main(String[ ] args) {
JavaSyncTest j1 = new ______________________________( );
JavaSyncTest j2 = new ______________________________( );
JavaSyncTest j3 = new ______________________________( );
Thread ______ = new ________________________ ;
Thread ______ = new ________________________ ;
Thread _______ = new ________________________ ;
t1._________________ ; t2.__________________; _________________;
try {
t1.________________( );
t2.________________( );
t3.________________( );
} catch(Exception e) { }
System.out.println(“Balance = “ + __________________________________ );
}
}

```

