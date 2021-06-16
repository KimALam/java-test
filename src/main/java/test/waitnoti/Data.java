package test.waitnoti;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Data {
    private String packet;
    private boolean transfer = true;

    public synchronized void send(String packet) {
        log.info("Data| send() start...");
        while (!transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("send| Thread interrupted", e);
            }
        }
        transfer = false;

        this.packet = packet;
        log.info("Data| send() end...");
        notifyAll();
    }

    public synchronized String receive() {
        log.info("Data| receive() start...");
        while (transfer) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("receive| Thread interrupted", e);
            }
        }
        transfer = true;

        log.info("Data| receive() end...");
        notifyAll();
        return packet;
    }
}
