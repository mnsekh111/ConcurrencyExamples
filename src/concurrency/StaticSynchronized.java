/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrency;

/**
 *
 * @author mnsekh111
 * When a thread invokes a synchronized method, it automatically acquires the intrinsic lock for that method's object and releases 
 * it when the method returns. The lock release occurs even if the return was caused by an uncaught exception.

You might wonder what happens when a static synchronized method is invoked, since a static method is associated with a class,
* not an object. In this case, the thread acquires the intrinsic lock for the Class object associated with the class.
* Thus access to class's static fields is controlled by a lock that's distinct from the lock for any instance of the class.
 */
public class StaticSynchronized {

    private static class Counter {
        static int c = 0;
        
        //Acquires lock for the entire
        public static  void incrementC(){
            for(int i=0;i<10000;i++){
                c++;
            }
        }
        
        public static void printC(){
            System.out.println("Value of C "+c);
        }
        
    }
    
    public static void main(String[] args) throws InterruptedException {
        Counter sc = new Counter();
        Counter sc2 = new Counter();
        
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(" From " + Thread.currentThread().getName() +"\n");
                sc.incrementC();
            }
        },"Thread 1");
        
        
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                   System.out.println(" From " + Thread.currentThread().getName() +"\n");
                sc2.printC();
            }
        },"Thread 2");
        
        t1.run();
        t2.run();
        
        t1.join();
        t2.join();
    }
}
