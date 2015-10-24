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
class PrintThread extends Thread {

    int count;

    public PrintThread(int cnt) {
        this.count = cnt;
    }

    @Override
    public void run() {
        System.out.println("Thread id : " + this.getId());
        for(int i=0;i<count;++i){
            System.out.print(count + " ");
        }
        
    }
}

class PrintRunnable implements Runnable{

    int count;

    public PrintRunnable(int cnt) {
        this.count = cnt;
    }
    
    @Override
    public void run() {
        System.out.println("Thread id : " + Thread.currentThread().getId());
        for(int i=0;i<count;++i){
            System.out.print(count + " ");
        }
    }
    
}

public class ThreadRunnable {
    public static void main(String[] args) {
        new PrintThread(10).start();
        new Thread(new PrintRunnable(10)).start();
    }
}
