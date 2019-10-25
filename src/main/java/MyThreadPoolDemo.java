import java.util.Random;
import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {

        ExecutorService executorService = new ThreadPoolExecutor(2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 0; i < 9; i++) {
                executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务" + new Random().nextInt(20));
                });
            }
        }finally {
            executorService.shutdown();
        }
/*        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        //ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newCachedThreadPool();


            try {
                for (int i = 0; i < 20; i++) {
                executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "办理业务" + new Random().nextInt(20));
                });
                }
            }finally {
                executorService.shutdown();
            }*/
    }
}
