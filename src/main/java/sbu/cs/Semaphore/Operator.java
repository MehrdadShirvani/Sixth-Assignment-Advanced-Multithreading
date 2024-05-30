package sbu.cs.Semaphore;

import java.time.LocalDateTime;
import java.util.Date;

public class Operator extends Thread {

    public Operator(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++)
        {
            Resource.accessResource();         // critical section - a Maximum of 2 operators can access the resource concurrently
            System.out.println(getName() + " accessed at " + LocalDateTime.now().toString() + " - number of times: " + (i+1));
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
