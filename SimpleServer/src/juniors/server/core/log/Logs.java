package juniors.server.core.log;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


/**
 * Буфферизированный логгер, основннанный на JavaLogging 
 * @author Dmitrii Shakshin (trueCoder)<d.shakshin@gmail.com>
 *
 */
// TODO перейти на log4j
public class Logs {

    private static final Logs instance;
    static {
	instance = new Logs();
    }
    
    private String DEFAULT_PATH = "%t/simpleServer%u%g.html";

    private AbstractMap<String,Logger> mapLoggers;
    private String path = DEFAULT_PATH;

    private BufferLogs buffer;
    
    public final int DEFAULT_SIZE_BUFFER = 5;
    

    private Logs() {
	mapLoggers = new ConcurrentHashMap<String, Logger>();
	buffer = new BufferLogs(DEFAULT_SIZE_BUFFER);
    }
    
    private FileHandler getFileHandler() throws SecurityException, IOException {
	FileHandler handler = new FileHandler(path, false);
	handler.setFormatter(new HtmlFormatter());
	return handler;
    }

    public static Logs getInstance() {
	return instance;
    }

    public Logger getLogger(String simpleClassName) {
	Logger logger = mapLoggers.get(simpleClassName);
	if (logger == null) {
	    logger = Logger.getLogger(simpleClassName);
		logger.addHandler(buffer);
		try {
		    FileHandler fileHandler = getFileHandler();
		    logger.addHandler(fileHandler);
		} catch (SecurityException | IOException e) {
		    e.printStackTrace();
		}
	    mapLoggers.put(simpleClassName, logger);
	}
	return logger;
    }
    
    public BufferLogs getBuffer() {
	return buffer;
    }
    
    public static void main(String[] args) {
	org.apache.log4j.Logger.getLogger(Logs.class).info("hello log4j");
    }
    
}
