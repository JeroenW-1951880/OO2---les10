package com.jetbrains;

import java.util.ArrayList;

/**
 * class die op een notify van de setter wacht
 *
 * @author Jeroen Weltens
 */
public class Efficient_wait extends Thread {
    ArrayList<Boolean> is_set;

    public Efficient_wait(ArrayList<Boolean> isset){
        is_set = isset;
        start();
    }

    @Override
    public synchronized void run() {
        if(!is_set.get(0)){
            try{wait();}catch (InterruptedException e){}
            is_set.set(0, false);
            System.out.println("value is set --> resetting");
        }
    }
}
