public class LoggerTest {

    public static void main(String[] args) throws InterruptedException {

        
        System.out.println("  Singleton Pattern - Logger Test Suite ");
        

        System.out.println(" Test 1: Reference Equality ");
        Logger loggerA = Logger.getInstance();
        Logger loggerB = Logger.getInstance();
        Logger loggerC = Logger.getInstance();

        boolean allSame = (loggerA == loggerB) && (loggerB == loggerC);
        System.out.println("loggerA hashCode : " + System.identityHashCode(loggerA));
        System.out.println("loggerB hashCode : " + System.identityHashCode(loggerB));
        System.out.println("loggerC hashCode : " + System.identityHashCode(loggerC));
        System.out.println("All references point to the same object: " + allSame);
        System.out.println(allSame ? "PASS\n" : "FAIL\n");

        System.out.println(" Test 2: Shared State ");
        loggerA.setLogLevel("DEBUG");
        System.out.println("Set logLevel via loggerA -> 'DEBUG'");
        System.out.println("logLevel read via loggerB -> '" + loggerB.getLogLevel() + "'");
        boolean stateShared = "DEBUG".equals(loggerB.getLogLevel());
        System.out.println(stateShared ? "PASS\n" : "FAIL\n");

        System.out.println(" Test 3: Logging Methods ");
        Logger logger = Logger.getInstance();
        logger.setLogLevel("INFO");
        logger.log("Application has started successfully.");
        logger.warn("Low memory warning detected.");
        logger.error("Unable to connect to database.");
        System.out.println("PASS\n");

        System.out.println(" Test 4: Thread-Safety (10 threads) ");
        final int THREAD_COUNT = 10;
        final int[] instanceHashCodes = new int[THREAD_COUNT];

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                Logger l = Logger.getInstance();
                instanceHashCodes[index] = System.identityHashCode(l);
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        boolean threadSafe = true;
        for (int code : instanceHashCodes) {
            if (code != instanceHashCodes[0]) {
                threadSafe = false;
                break;
            }
        }
        System.out.println("All threads returned the same instance: " + threadSafe);
        System.out.println(threadSafe ? "PASS\n" : "FAIL\n");

        
        System.out.println("  All tests completed.");
        
    }
}
