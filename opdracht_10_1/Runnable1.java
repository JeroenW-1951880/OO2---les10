package com.jetbrains;

/***
 * class that extends runnable and waits for a notify
 */
public class Runnable1 implements Runnable {


    @Override
    public synchronized void run() {
        System.out.println("class 1 before wait");
        try{wait();}catch (InterruptedException e){}
        System.out.println("class 1 after wait");
    }
}
