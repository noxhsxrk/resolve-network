# Thread ในจาวา (3)

![JAVA](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)


#### FileThreadPool.java

```java
จงเขียนโปรแกรม FileThreadPool.java 
เพื่ออ่านไฟล์จากอาร์กิวเม้นตัวที่ 1 ซึ่งในไฟล์จะมีข้อมูลเป็นเลขจานวนเต็ม 1 ค่าต่อ 1 บรรทัด 
โดยโปรแกรมจะนาค่าที่อ่านได้จากไฟล์ทีละบรรทัดส่งไป Thread ทางาน (1 บรรทัดต่อ 1 Thread) 
ภายในการทางานของ Thread จะเป็นการคานวณค่าที่รับมาแล้วนาไปยกกาลัง 2 เพื่อแสดงผลลัพธ์ออกมาบนหน้าจอ

กำหนด ให้ใช้คลาส Executors และคลาส ExecutorService โดยให้แต่ละช่วงเวลามี Thread ทางานพร้อมกันได้ไม่เกิน 3 ตัว

ตัวอย่าง
ไฟล์ input.txt        ผลการรัน: FileThreadPool input.txt
    5                           10 -> 100
    10                          15 -> 225
    15                          5 -> 25
    12                          12 -> 144

```
