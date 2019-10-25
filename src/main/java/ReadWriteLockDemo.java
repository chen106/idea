import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.put(finalI + "",finalI + "");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.get(finalI + "");
            },String.valueOf(i)).start();
        }
    }
}

class MyCache{
    private volatile Map<String,String> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    public void put(String key,String value){
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 写入开始~~~");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "\t 写入结束~~~");
        }finally {

        }
        reentrantReadWriteLock.writeLock().unlock();
    }
    public void get(String key){
        reentrantReadWriteLock.readLock().lock();
        try {
            String result = null;
            System.out.println(Thread.currentThread().getName() + "\t 读取开始~~~");
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取结束~~~ result :" + result);
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }
}

/*
class MyCache{
    private volatile Map<String,String> map = new HashMap<>();
    private Lock lock = new ReentrantLock();
    public void put(String key,String value){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 写入开始~~~");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + "\t 写入结束~~~");
        }finally {

        }
        lock.unlock();
    }
    public void get(String key){
        //lock.lock();
        try {
            String result = null;
            System.out.println(Thread.currentThread().getName() + "\t 读取开始~~~");
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取结束~~~ result :" + result);
        }finally {
            //lock.unlock();
        }

    }
}
*/