package test.waitnoti;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Receiver implements Runnable {
    private Data load;

    public Receiver(Data load) {
        this.load = load;
    }

    @Override
    public void run() {
        for (String receivedMessage = load.receive();
             !"End".equals(receivedMessage);
             receivedMessage = load.receive()) {
            log.info("Receiver| received message : {}", receivedMessage);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Receiver| Thread interrupted", e);
            }
        }
    }
}
