public class SingletonTest {

    static void basicTest() {
        System.out.println("=== Basic Singleton Test ===");

        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        System.out.println("logger1 == logger2 : " + (logger1 == logger2));
        System.out.println("Same hashCode      : " + (logger1.hashCode() == logger2.hashCode()));

        logger1.log("Application started.");
        logger2.warn("Low memory warning.");
        logger1.error("Null pointer encountered.");

        System.out.println("\nChanging log level to DEBUG via logger1...");
        logger1.setLogLevel("DEBUG");
        System.out.println("Log level read from logger2: " + logger2.getLogLevel());

        System.out.println();
    }

    static void threadSafetyTest() throws InterruptedException {
        System.out.println("=== Thread-Safety Test ===");

        final int THREAD_COUNT = 5;
        Thread[] threads = new Thread[THREAD_COUNT];
        final Logger[] instances = new Logger[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int index = i;
            threads[index] = new Thread(() -> {
                instances[index] = Logger.getInstance();
                System.out.println("Thread-" + index
                        + " got instance @" + System.identityHashCode(instances[index]));
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        boolean allSame = true;
        for (Logger inst : instances) {
            if (inst != instances[0]) {
                allSame = false;
                break;
            }
        }
        System.out.println("All threads received same instance: " + allSame);
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        basicTest();
        threadSafetyTest();
        System.out.println("=== All Tests Passed ===");
    }
}
