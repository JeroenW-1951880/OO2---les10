package com.jetbrains;

import java.util.ArrayList;

/***
 * class die via een while loop op een verandering wacht
 *
 * @author Jeroen Weltens
 */
public class Busy_wait extends Thread {
    ArrayList<Boolean> value_set;

    public Busy_wait(ArrayList<Boolean> value){
        value_set = value;
        start();
    }

    @Override
    public void run() {
        while(!value_set.get(0)){
            try{sleep(100);}catch(InterruptedException e){}
        }
        System.out.println("value is set --> resetting");
        value_set.set(0, false);
    }
}
