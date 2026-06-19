public class Logger {

    private static volatile Logger instance = null;

    private String logLevel;

    private Logger() {
        this.logLevel = "INFO";
        System.out.println("[Logger] Instance created.");
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("[" + logLevel + "] " + message);
    }

    public void warn(String message) {
        System.out.println("[WARN]  " + message);
    }

    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }
}
