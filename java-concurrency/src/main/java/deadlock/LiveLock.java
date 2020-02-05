package deadlock;

import java.util.Random;
import jdk.management.resource.internal.inst.RandomAccessFileRMHooks;

/**
 * 描述：     演示活锁问题
 */
public class LiveLock {

    // 勺子
    static class Spoon {

        private Diner owner;


        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public Diner getOwner() {
            return owner;
        }

        public void setOwner(Diner owner) {
            this.owner = owner;
        }

        // 同步方法
        public synchronized void use() {
            System.out.printf("%s吃完了!", owner.name);


        }
    }

    // 就餐者
    static class Diner {

        private String name;
        private boolean isHungry;

        public Diner(String name) {
            this.name = name;
            isHungry = true;
        }

        /**
         * 就餐方法
         * @param spoon 勺子
         * @param spouse 一起就餐的人（不是自己）
         */
        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {

                // 勺子不是自己的
                if (spoon.owner != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                Random random = new Random();
                if (spouse.isHungry && random.nextInt(10) < 9) {
                    System.out.println(name + ": 亲爱的" + spouse.name + "你先吃吧");
                    spoon.setOwner(spouse);
                    continue;
                }

                spoon.use();
                isHungry = false;
                System.out.println(name + ": 我吃完了");
                spoon.setOwner(spouse);

            }
        }
    }


    public static void main(String[] args) {
        Diner husband = new Diner("牛郎");
        Diner wife = new Diner("织女");

        Spoon spoon = new Spoon(husband);

        new Thread(new Runnable() {
            @Override
            public void run() {
                husband.eatWith(spoon, wife);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                wife.eatWith(spoon, husband);
            }
        }).start();
    }
}
