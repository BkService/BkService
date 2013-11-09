package juniors.server.core.log;

import java.util.AbstractMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Буфферизированный логгер. 
 * @author Dmitrii Shakshin (trueCoder)<d.shakshin@gmail.com>
 *
 */
public class Logs {

    private static final Logs instance;
    
    static {
	instance = new Logs();
    }

    private AbstractMap<String,Logger> mapLoggers;
    

    private Logs() {
	mapLoggers = new ConcurrentHashMap<String, Logger>(5);
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
