package com.jetbrains;

import java.util.ArrayList;

/**
 * programma dat het producer-consumer principe synchronized behandelt
 *
 * @author Jeroen Weltens
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        Producer p = new Producer(arr);
        Consumer c = new Consumer(arr);
    }
}
