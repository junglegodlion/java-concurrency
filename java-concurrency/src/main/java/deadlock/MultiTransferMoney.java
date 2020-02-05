package deadlock;

import deadlock.TransferMoney.Account;
import java.util.Random;

/**
 * 描述：     多人同时转账，依然很危险
 */
public class MultiTransferMoney {

    // 账户数量
    private static final int NUM_ACCOUNTS = 500;

    // 账户初始余额
    private static final int NUM_MONEY = 1000;

    // 交易次数
    private static final int NUM_ITERATIONS = 1000000;

    // 同时转账的次数
    private static final int NUM_THREADS = 20;

    public static void main(String[] args) {

        // 随机转账
        Random rnd = new Random();
        Account[] accounts = new Account[NUM_ACCOUNTS];
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(NUM_MONEY);
        }

        /**
         * 转账线程类
         */
        class TransferThread extends Thread {

            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcct = rnd.nextInt(NUM_ACCOUNTS);
                    int amount = rnd.nextInt(NUM_MONEY);
                    TransferMoney.transferMoney(accounts[fromAcct], accounts[toAcct], amount);
                }
                System.out.println("运行结束");
            }
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}
