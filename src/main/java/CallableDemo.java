import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask(() -> {
            System.out.println(Thread.currentThread().getName() + "*******come in call");
            return "0615";
        });
        new Thread(futureTask,"AAA").start();
        String result = futureTask.get();
        System.out.println(result);
        System.out.println("********主线程：" + Thread.currentThread().getName());
    }
}
/*
class MyThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "*******come in call");
        return "0615";
    }
}
*/