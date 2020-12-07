package com.jetbrains;

import java.util.ArrayList;

/**
 * programma dat busy wait en een normale wait bevat
 *
 * @author Jeroen Weltens
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Boolean> value = new ArrayList<Boolean>(1);
        value.add(false);
        System.out.println("inefficient wait");
        Setter_thread st = new Setter_thread(value);
        Busy_wait wait = new Busy_wait(value);
        try{Thread.sleep(5000);}catch (InterruptedException e){}
        System.out.println("efficient wait");
        Efficient_wait ewait = new Efficient_wait(value);
        Efficient_setter eset = new Efficient_setter(value, ewait);
    }
}
