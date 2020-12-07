package com.jetbrains;

import java.util.ArrayList;

/***
 * classe die de value alleen maar verandert
 *
 * @author Jeroen Weltens
 */
public class Setter_thread extends Thread {
    ArrayList<Boolean> value_set;

    public Setter_thread(ArrayList<Boolean> value){
        value_set = value;
        start();
    }

    @Override
    public void run() {
        try{sleep(2000);}catch(InterruptedException e){}
        value_set.set(0, true);
    }
}
