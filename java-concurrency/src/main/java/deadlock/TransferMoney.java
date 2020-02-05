package deadlock;

/**
 * 描述：     转账时候遇到死锁，一旦打开注释，便会发生死锁
 */
public class TransferMoney implements Runnable {

    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        TransferMoney r1 = new TransferMoney();
        TransferMoney r2 = new TransferMoney();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();

        // 让主线程对他们进行等待
        t1.join();
        t2.join();
        System.out.println("a的余额" + a.balance);
        System.out.println("b的余额" + b.balance);
    }

    @Override
    public void run() {
        if (flag == 1) {
            transferMoney(a, b, 200);
        }
        if (flag == 0) {
            transferMoney(b, a, 200);
        }
    }

    /**
     * 转账
     * @param from 汇款人
     * @param to 收款人
     * @param amount 金钱
     */
    public static void transferMoney(Account from, Account to, int amount) {

        /**
         * 帮助类
         */
        class Helper {

            public void transfer() {
                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败。");
                    return;
                }
                from.balance -= amount;
                to.balance = to.balance + amount;
                System.out.println("成功转账" + amount + "元");
            }
        }

        // 获取哈希值
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        // 保证获取锁的顺序是一致的
        if (fromHash < toHash) {
            synchronized (from) {

                //对本人进行加锁
                synchronized (to) {
                    // 对转给谁进行加锁
                    new Helper().transfer();
                }
            }
        }
        else if (fromHash > toHash) {
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        }

        // 二个人的哈希值一样了
        else  {

            // 设置加时赛
            // 谁抢的这把锁，谁就先执行
            synchronized (lock) {
                synchronized (to) {
                    synchronized (from) {
                        new Helper().transfer();
                    }
                }
            }
        }

    }


    /**
     * 账户类
     */
    static class Account {

        public Account(int balance) {
            this.balance = balance;
        }

        // 余额
        int balance;

    }
}