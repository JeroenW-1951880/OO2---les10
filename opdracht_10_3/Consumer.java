package com.jetbrains;

import java.util.ArrayList;

/***
 * class die waarden uit de array leest en het aantal digits van iedere waarde uitprint
 */
public class Consumer extends Thread {
    ArrayList<Integer> Buffer;

    public Consumer(ArrayList<Integer> buf){
        Buffer = buf;
        start();
    }

    @Override
    public void run() {
        int counter = 0;
        while(counter++ != 100){
            if(Buffer.size() == 0){
                synchronized (Buffer) {
                    try {
                        System.out.println("consumer waits");
                        Buffer.wait();
                    } catch (InterruptedException e) {}
                }
            } else {
                synchronized (Buffer){
                    int digitcounter = 0, number = Buffer.remove(0), copy = number;
                    while(copy >= 1){
                        digitcounter++;
                        copy /= 10;
                    }
                    System.out.println(number + " has " + digitcounter + " digits");
                    Buffer.notify();
                }
            }
        }
    }
}
