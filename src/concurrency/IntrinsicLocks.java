package concurrency;

/**
 * Created by mns on 12/5/15.
 */

class Counters {
    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void inc1() {
        synchronized (lock1) {
            c1++;
        }
    }

    public void inc2() {
        synchronized (lock2) {
            c2++;
        }
    }
}

class CountersGlobalLock {
    private long c1 = 0;
    private long c2 = 0;

    synchronized public void inc1() {
        c1++;
    }

    synchronized void inc2() {
        c2++;
    }
}

class MyRunnable implements Runnable{
    Counters sharedCounters;

    public MyRunnable(Counters c){
        sharedCounters = c;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            sharedCounters.inc1();
            sharedCounters.inc2();
        }
    }
}


class MyRunnable2 implements Runnable{
    CountersGlobalLock sharedCounters;

    public MyRunnable2(CountersGlobalLock c){
        sharedCounters = c;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            sharedCounters.inc1();
            sharedCounters.inc2();
        }
    }
}

public class IntrinsicLocks {

    public static void main(String[] args) {

        Counters sharedCounters = new Counters();
        Counters sharedCounters2 = new Counters();

        Thread t1 = new Thread(new MyRunnable(sharedCounters));
        Thread t2 = new Thread(new MyRunnable(sharedCounters));

        long startTime = System.currentTimeMillis();
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException ie) {
        }
        System.out.println("Total time taken to complete " + (System.currentTimeMillis() - startTime));

        CountersGlobalLock sc1 = new CountersGlobalLock();
        CountersGlobalLock sc2 = new CountersGlobalLock();

        Thread t3 = new Thread(new MyRunnable2(sc1));
        Thread t4 = new Thread(new MyRunnable2(sc2));

        startTime = System.currentTimeMillis();
        t3.start();
        t4.start();

        try {
            t3.join();
            t4.join();
        } catch (InterruptedException ie) {
        }
        System.out.println("Total time taken to complete " + (System.currentTimeMillis() - startTime));


    }
}
