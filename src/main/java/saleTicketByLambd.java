import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class saleTicketByLambd {
    public static void main(String[] args) {
        Ticket1 ticket1 = new Ticket1();

        new Thread(() -> {for (int i = 0;i < 100;i++) ticket1.saleTicket();},"A").start();
        new Thread(() -> {for (int i = 0;i < 100;i++) ticket1.saleTicket();},"B").start();
        new Thread(() -> {for (int i = 0;i < 100;i++) ticket1.saleTicket();},"C").start();
    }
}


class Ticket1{
    private int ticket = 100;

    Lock lock = new ReentrantLock();
    public void saleTicket() {
        lock.lock();
        try {
            if (ticket > 0){
                System.out.println(Thread.currentThread().getName() + "正在卖第" + ticket-- + "张票，还剩" + ticket);
            }
        }finally {
            lock.unlock();
        }

    }
}