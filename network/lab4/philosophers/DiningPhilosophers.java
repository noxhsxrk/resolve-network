package network.lab4.philosophers;
public class DiningPhilosophers {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        Fork[] fork = new Fork[n];
        Philosopher[] philosopher = new Philosopher[n];
        
        for (int i = 0; i < fork.length; i++) {
            fork[i] = new Fork();
        }

        for (int i = 0; i < philosopher.length; i++) {
            Fork leftFork = fork[i];
            Fork rightFork = fork[(i + 1) % fork.length];

            philosopher[i] = new Philosopher("P"+i,leftFork, rightFork);
            
            Thread t = new Thread(philosopher[i], "Philosopher " + (i + 1));
            t.start();
        }


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
