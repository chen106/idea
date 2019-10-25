import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class saleTicket {
    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        new Thread(new Runnable() {
            @Override
            public void run() {
               for (int i = 0;i < 30;i ++){
                   ticket.saleTicket();
               }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 30;i ++){
                    ticket.saleTicket();
                }
            }
        }, "C").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 30;i ++){
                    ticket.saleTicket();
                }
            }
        }, "A").start();
    }
}
class Ticket{
    private int ticket = 30;
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
