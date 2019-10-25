import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class saleTicketByPool {
    public static void main(String[] args) {
        Ticket2 ticket2 = new Ticket2();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        try {
            for (int i = 0; i < 100; i++) {
                executorService.submit(() ->{
                    ticket2.saleTicket();
                });
            }
        }finally {
            executorService.shutdown();
        }


       /* new Thread(() -> {for (int i = 0;i < 100;i++) ticket2.saleTicket();},"A").start();
        new Thread(() -> {for (int i = 0;i < 100;i++) ticket2.saleTicket();},"B").start();
        new Thread(() -> {for (int i = 0;i < 100;i++) ticket2.saleTicket();},"C").start();*/
    }
}


class Ticket2{
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