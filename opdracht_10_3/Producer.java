package com.jetbrains;

import java.util.ArrayList;
import java.util.Random;

/***
 * class die willekeurige waarden aan een array toevoegd
 *
 * @author Jeroen Weltens
 */
public class Producer extends Thread {
    ArrayList<Integer> Buffer;

    public Producer(ArrayList<Integer> buf){
        Buffer = buf;
        start();
    }

    @Override
    public void run() {
        int counter = 0;
        Random rand = new Random();
        while(counter++ != 100){
            if(Buffer.size() >= 10){
                synchronized (Buffer){
                    try{
                        System.out.println("producer waits");
                        Buffer.wait();
                    }catch (InterruptedException e){}
                }
            } else{
                synchronized (Buffer){
                    Buffer.add(Math.abs(rand.nextInt()));
                    Buffer.notify();
                }
            }
        }
    }
}
