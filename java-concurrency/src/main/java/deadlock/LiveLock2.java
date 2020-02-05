package deadlock;

import java.util.Random;

/**
 * 描述：     演示活锁问题
 */
public class LiveLock2 {

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
         * 就餐方法：不饿就不吃饭
         *          饿了： 1.勺子不在自己手里，不吃饭
         *                 2.勺子在自己手里，对方饿了，对方先吃
         *                 3.勺子在自己手里，对方不饿，自己吃
         *
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

                // 活锁的原因大多数是逻辑造成的
                // 这里就是造成活锁的原因
                // 夫妻二人都很饥饿的话，就会互相谦让，在这里传勺子玩
                if (spouse.isHungry ) {
                    System.out.println(name + ": 亲爱的" + spouse.name + "你先吃吧");

                    // 勺子交给对方
                    spoon.setOwner(spouse);
                    continue;
                }

                // 到了这里，说明对方不饿，自己吃饭
                spoon.use();
                isHungry = false;
                System.out.println(name + ": 我吃完了");

                // 勺子交给对方
                spoon.setOwner(spouse);

            }
        }
    }


    public static void main(String[] args) {

        // 创建一对夫妻实例
        Diner husband = new Diner("牛郎");
        Diner wife = new Diner("织女");

        // 只有一个勺子可以使用
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
