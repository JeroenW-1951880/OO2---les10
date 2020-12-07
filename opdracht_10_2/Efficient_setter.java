package com.jetbrains;

import java.util.ArrayList;

/***
 * class die de value verandert en dan een notify verstuurt
 *
 * @author Jeroen Weltens
 */
public class Efficient_setter extends Thread {
    ArrayList<Boolean> is_set;
    Efficient_wait w;

    public Efficient_setter(ArrayList<Boolean> isset, Efficient_wait wait){
        is_set = isset;
        w = wait;
        start();
    }

    @Override
    public void run() {
        try{sleep(2000);}catch (InterruptedException e){}
        is_set.set(0, true);
        synchronized (w){
            w.notifyAll();
        }
    }

}
