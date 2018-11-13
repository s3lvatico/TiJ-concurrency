package org.gmnz.tij.cc;

/*
    esempi su join dei thread
*/

class Out {
    static void println(String s) {
        System.out.println(s);
    }

    static void print(String s) {
        System.out.print(s);
    }
}

class Sleeper extends Thread {
    private int duration;

    Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    public void run() {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            Out.println(getName() + " was interrupted. isInterrupted(): " + isInterrupted());
            return;
        }
        Out.println(getName() + " has finished its job");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;

    Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run() {
        try {
            sleeper.join(); // attende indefinitamente  il completamento del thread
        } catch (InterruptedException e) {
            Out.println("interrupted");
        }
        Out.println(getName() + " join completed.");
    }
}

public class Joining {
    public static void main(String[] args) {
        Sleeper sleepy = new Sleeper("Sleepy", 1500);
        Sleeper grumpy = new Sleeper("Grumpy", 1500);

        /* Joiner dopey = */  new Joiner("Dopey", sleepy);
        /* Joiner doc = */ new Joiner("Doc", grumpy);

        // interruzione forzata
        grumpy.interrupt();
    }
}
