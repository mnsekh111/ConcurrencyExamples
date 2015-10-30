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
public class ThreadJoins {

    public static void main(String[] args) {
        try {
            SampleThread st = new SampleThread("Thread 1");
            SampleThread st2 = new SampleThread("Thread 2");
            
            st.start();
            System.out.println("Main thread waiting for Thread 1");
            st.join();
            System.out.println("Main thread Resuming");
            
            // st.start(); will cause Illegal state exception
            st2.start();
            System.out.println("Main thread starts 2 second wait");
            st2.join(2000); 
            System.out.println("Main exits");
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadJoins.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
