package juniors.server.core.log;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Logs {

    private static final Logs instance;

    public static final String PATH_LOGFILE = "%t/simpleServerLogs/java%u%g.log";
    static {
	instance = new Logs();
    }

    private HashMap<String, Logger> mapLoggers;
    
    private FileHandler fh;

    private Logs() {
	mapLoggers = new HashMap<String, Logger>(5);
	try {
	    fh = new FileHandler(PATH_LOGFILE);
	    fh.setFormatter(new HtmlFormatter());
	} catch (SecurityException | IOException e) {
	    e.printStackTrace();
	}
    }

    public static Logs getInstance() {
	return instance;
    }

    public Logger getLogger(String simpleClassName) {
	Logger logger = mapLoggers.get(simpleClassName);
	if (logger == null) {
	    logger = Logger.getLogger(simpleClassName);
		logger.addHandler(BufferLogs.getInstance());
	    mapLoggers.put(simpleClassName, logger);
	}
	return logger;
    }
    
}
