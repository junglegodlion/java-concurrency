package threadcoreknowledge.createthreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 描述：     定时器创建线程
 */
public class DemoTimmerTask {

    public static void main(String[] args) {
        Timer timer = new Timer();
        //每隔一秒打印线程名字
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
