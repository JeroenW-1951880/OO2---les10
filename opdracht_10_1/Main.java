package com.jetbrains;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * program to test Executor, wait(), notify()
 *
 * @author Jeroen Weltens
 */
public class Main {

    public static void main(String[] args) {
        Executor exec = Executors.newCachedThreadPool();
        Runnable1 r1 = new Runnable1();
        exec.execute(r1);
        exec.execute(new Runnable2(r1));
    }
}
