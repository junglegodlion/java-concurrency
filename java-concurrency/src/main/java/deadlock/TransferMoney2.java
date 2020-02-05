package deadlock;

/**
 * 描述：     转账时候遇到死锁，一旦打开注释，便会发生死锁
 */
public class TransferMoney2 implements Runnable {

    int flag = 1;
    static Account a = new Account(500);
    static Account b = new Account(500);
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        TransferMoney2 r1 = new TransferMoney2();
        TransferMoney2 r2 = new TransferMoney2();
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

        //对自己进行加锁
        synchronized (from) {


            // 取消注释，就会进入死锁
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


            // 对收款人进行加锁
            synchronized (to) {

                if (from.balance - amount < 0) {
                    System.out.println("余额不足，转账失败");
                }

                from.balance -= amount;
                to.balance = to.balance + amount;
                System.out.println("成功转账" + amount + "元");
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
