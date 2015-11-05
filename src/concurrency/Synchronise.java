/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrency;

class SyncCounter {

    private int c = 0;

    public void increment2() {
        c++;
    }

    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }
}

/**
 *
 * @author mnsekh111
 */
public class Synchronise {

    public static void main(String[] args) throws InterruptedException {

        SyncCounter sc = new SyncCounter();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                sc.increment();
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    sc.increment();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Expected value = " + 20000);
        System.out.println("Value of Counter = " + sc.value());

        t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    sc.increment2();
                }
            }
        });

        t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    sc.increment2();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Expected value = " + 40000);
        System.out.println("Value of Counter = " + sc.value());

    }
}
