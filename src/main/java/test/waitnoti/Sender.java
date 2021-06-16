package test.waitnoti;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Sender implements Runnable {
    private Data data;

    public Sender(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        String packets[] = {
                "First packet",
                "Second packet",
                "Third packet",
                "Fourth packet",
                "End"
        };

        for (String packet : packets) {
            log.info("Sender| send packet : {}", packet);
            data.send(packet);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Sender| Thread interrupted", e);
            }
        }
    }
}
