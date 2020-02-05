package deadlock;

/**
 * 描述：     必定发生死锁的情况
 */
public class MustDeadLock implements Runnable {

    int flag = 1;

//    第一把锁
    static Object o1 = new Object();
//    第二把锁
    static Object o2 = new Object();

    public static void main(String[] args) {

//        创建实例
        MustDeadLock r1 = new MustDeadLock();
        MustDeadLock r2 = new MustDeadLock();

        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }

    // 不同的线程执行不同的策略
    @Override
    public void run() {
        System.out.println("flag = " + flag);

        // 执行不同的策略
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("线程1成功拿到两把锁");
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("线程2成功拿到两把锁");
                }
            }
        }
    }
}
