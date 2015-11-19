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
class SampleThread extends Thread {

    private final String[] mStrings = {"String1", "String2", "String3", "String4", "String5"};

    SampleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < mStrings.length; ++i) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SleepAndInterrupt.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(mStrings[i] + " printed from " + this.getName() + " " + this.getId());
        }
    }

}

class SampleThread2 extends Thread {

    private final String[] mStrings = {"String1", "String2", "String3", "String4", "String5"};

    public SampleThread2(String name) {
        super(name);
    }

    
    @Override
    public void run() {
        int i = 0;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SleepAndInterrupt.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
           
            System.out.println(mStrings[i++ % mStrings.length] + " printed from " + this.getName());
        }
    }
}

public class SleepAndInterrupt {

    public static void main(String[] args) throws InterruptedException {
        SampleThread st = new SampleThread("Thread 1");
        SampleThread2 st2 = new SampleThread2("Thread 2");
        st.start();
        st2.start();
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + " is interrupting " + st.getName());
        st.interrupt();
        Thread.sleep(5000);
        System.out.println(Thread.currentThread().getName() + " is interrupting " + st2.getName());
        st2.interrupt();
       
    }

}
