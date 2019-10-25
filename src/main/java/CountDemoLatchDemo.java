import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDemoLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室~~~");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        //countDownLatch.await(2l,TimeUnit.SECONDS);
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t关门了~~~~");
    }
}
