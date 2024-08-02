import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
// import java.util.logging.SimpleFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyLogger {
    private static final Logger LOGGER = Logger.getLogger(MyLogger.class.getName());

    static {
        try {
            // Console handler
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.ALL);
            consoleHandler.setFormatter(new CustomFormatter());
            LOGGER.addHandler(consoleHandler);

            // File handler
            FileHandler fileHandler = new FileHandler("app.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new CustomFormatter());
            LOGGER.addHandler(fileHandler);

            // Set the default logging level
            LOGGER.setLevel(Level.ALL);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error occurred in setting up logger", e);
        }
    }

    public static void main(String[] args) {
        LOGGER.info("Logger is configured and ready to log messages");
    }
}

class CustomFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return String.format("[%1$tF %1$tT] [%2$-7s] %3$s %n",
                new java.util.Date(record.getMillis()),
                record.getLevel().getName(),
                formatMessage(record));
    }
}
