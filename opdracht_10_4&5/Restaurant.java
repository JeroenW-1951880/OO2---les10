//: concurrency/Restaurant.java
// The producer-consumer approach to task cooperation
package com.jetbrains;
import java.util.concurrent.*;

class Meal {
    private final int orderNum;
    public Meal(int orderNum) { this.orderNum = orderNum; }
    public String toString() { return "Meal " + orderNum; }
}

class WaitPerson implements Runnable {
    private Restaurant restaurant;
    public WaitPerson(Restaurant r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal == null)
                        wait(); // ... for the chef to produce a meal
                }
                System.out.println("Waitperson got " + restaurant.meal);
                synchronized(restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another
                }
                synchronized(restaurant.boy) {
                    restaurant.boy.notifyAll(); // Ready to clean up
                }
            }
        } catch(InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }
    }
}
class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;
    public Chef(Restaurant r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    while(restaurant.meal != null)
                        wait(); // ... for the meal to be taken
                }
                if(++count == 10) {
                    System.out.println("Out of food, closing");
                    restaurant.exec.shutdownNow();
                    return;
                }
                System.out.println("Order up! ");
                synchronized(restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch(InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}
class BusBoy implements Runnable {
    private Restaurant restaurant;
    public BusBoy(Restaurant r) { restaurant = r; }
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized(this) {
                    wait();
                }
                System.out.println("busboy cleaning up");
            }
        } catch(InterruptedException e) {
            System.out.println("busboy interrupted");
        }
    }
}

public class Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    BusBoy boy = new BusBoy(this);
    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(boy);
    }
    public static void main(String[] args) {
        new Restaurant();
    }
}
