package test.concurrent;

import java.util.concurrent.Phaser;

class PhaserTest2 {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();

        Thread t1 = new MyThread(phaser, 1000, "thread-1sec");
        Thread t2 = new MyThread(phaser, 3000, "thread-3sec");
        Thread t3 = new MyThread(phaser, 10000, "thread-10sec");

        System.out.println("main start threads...");

        t1.start();
        t2.start();
        t3.start();

        System.out.println("main end...");
    }

    private static class MyThread extends Thread {
        Phaser phaser;
        int sleep;
        String name;

        MyThread(Phaser phaser, int sleep, String name) {
            this.phaser = phaser;
            this.sleep = sleep;
            this.name = name;
        }

        @Override
        public void run() {
            phaser.register();
            System.out.println(name + " registered");

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + " arrived");
            phaser.arriveAndAwaitAdvance();
            System.out.println(name + " advanced");

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + " end");
        }
    }
}
