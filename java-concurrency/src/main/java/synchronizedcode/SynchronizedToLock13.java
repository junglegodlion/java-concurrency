package synchronizedcode;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedToLock13 {

    Lock lock = new ReentrantLock();



    // 下面两个方法是等价的
    public synchronized void method1() {
        System.out.println("我是Synchronized形式的锁");
    }

    public void method2() {
        lock.lock();

        try {
            System.out.println("我是lock形式的锁");
        } finally {

            lock.unlock();
        }
    }


    public static void main(String[] args) {
        SynchronizedToLock13 s = new SynchronizedToLock13();
        s.method1();
        s.method2();
    }
}
