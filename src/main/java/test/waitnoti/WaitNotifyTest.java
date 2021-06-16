package test.waitnoti;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WaitNotifyTest {
    public static void main(String[] args) {
        log.info("main thread");
//        Data data = new Data();
//        Thread sender = new Thread(new Sender(data));
//        Thread receiver = new Thread(new Receiver(data));
//
//        sender.start();
//        receiver.start();

        SyncClass sc = new SyncClass();
        Thread a = new Thread(() -> {
            log.info("Thread a");
            sc.syncMethod1();
        });
        Thread b = new Thread(() -> {
            log.info("Thread b");
            sc.syncMethod2();
        });
        Thread c = new Thread(() -> {
            log.info("Thread c");
            sc.syncMethod2();
        });
        Thread d = new Thread(() -> {
            log.info("Thread d");
            sc.syncMethod2();
        });

        b.start();
        a.start();
        c.start();
        d.start();
    }

    private static class SyncClass {
        synchronized void syncMethod1() {
            log.info("syncMethod1 start...");
            try {
                log.info("syncMethod1 sleep...");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("syncMethod1 end...");
        }

        synchronized void syncMethod2() {
            log.info("syncMethod2 start...");
            log.info("syncMethod2 end...");
        }
    }
}
