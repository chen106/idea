import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {

        AirConditioner airConditioner = new AirConditioner();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airConditioner.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

class AirConditioner{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() throws Exception{
        lock.lock();
        try {
            while (number != 0) {
                condition.await();//this.wait();
            }
            number ++;
            System.out.println(Thread.currentThread().getName()+"\t" + number);
            condition.signalAll();//this.notifyAll();
        }finally {
            lock.unlock();
        }
    }
    public void decrement() throws Exception{
        lock.lock();
        try {
            while (number == 0) {
                condition.await();//this.wait();
            }
            number --;
            System.out.println(Thread.currentThread().getName()+"\t" + number);
            condition.signalAll();//this.notifyAll();
        }finally {
            lock.unlock();
        }
    }


/*
    public synchronized void increment() throws Exception{
        while (number != 0) {
            this.wait();
        }
        number ++;
        System.out.println(Thread.currentThread().getName()+"\t" + number);
        this.notifyAll();
    }
    public synchronized void decrement() throws Exception{
        while (number == 0) {
            this.wait();
        }
        number --;
        System.out.println(Thread.currentThread().getName()+"\t" + number);
        this.notifyAll();
    }

 */
}
