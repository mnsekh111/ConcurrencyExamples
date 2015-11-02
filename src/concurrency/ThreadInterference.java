/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrency;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mnsekh111
 */
class Counter {

    private int c = 0;

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    public int value() {
        return c;
    }

}

class IncrementRunnable implements Runnable {

    private int count;
    private Counter cntr;

    public IncrementRunnable(int count, Counter c) {
        super();
        this.count = count;
        this.cntr = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            cntr.increment();
        }
    }

}

public class ThreadInterference {

    public static void main(String[] args) {

        Counter c = new Counter();
        int cnt1 = 100000;
        int cnt2 = 50000;
        Thread t1 = new Thread(new IncrementRunnable(cnt1, c));
        Thread t2 = new Thread(new IncrementRunnable(cnt2, c));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadInterference.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Expected Value of counter is "+(cnt1+cnt2));
        System.out.println("Actual Value of counter is "+c.value());
    }

}
