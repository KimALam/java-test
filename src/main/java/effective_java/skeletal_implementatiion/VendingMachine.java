package effective_java.skeletal_implementatiion;

// skeletal implementation example.
public class VendingMachine implements Ivending, VendingService {
    private AbstractVendingDelegator delegator = new AbstractVendingDelegator();

    @Override
    public void start() {
        delegator.start();
    }

    @Override
    public void process() {
        delegator.process();
    }

    @Override
    public void stop() {
        delegator.stop();
    }

    @Override
    public void doService() {
        System.out.println("doService...");
    }

    // inner class extending abstract class for delegation.
    private class AbstractVendingDelegator extends AbstractVending {
        @Override
        public void process() {
            System.out.println("My process...");
        }
    }
}

interface Ivending {
    void start();
    void process();
    void stop();
}

abstract class AbstractVending implements Ivending {
    @Override
    public void start() {
        System.out.println("start...");
    }

    @Override
    public void stop() {
        System.out.println("stop...");
    }
}

interface VendingService {
    void doService();
}

