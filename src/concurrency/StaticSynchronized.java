/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrency;

/**
 *
 * @author mnsekh111
 */
public class StaticSynchronized {

    private static class Counter {
        int c = 0;
        public void increment(){
            c++;
        }
        
    }
    
    public static void main(String[] args) {
        Counter sc = new Counter();
        Counter sc2 = new Counter();
        
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                
            }
        });
    }
}
