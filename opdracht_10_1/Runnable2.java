package com.jetbrains;

/***
 * class that notifies Runnable1
 */
public class Runnable2 implements Runnable{
    Runnable1 r;

    public Runnable2(Runnable1 run){
        r = run;
    }

    @Override
    public void run() {
        System.out.println("class 2 before notify");
        synchronized (r){
            try {
                r.wait(2000);
                r.notifyAll();
            }catch (InterruptedException e){}
        }
        System.out.println("class 2 after notify");
    }
}
