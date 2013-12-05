package juniors.server.core.log;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.FileHandler;

/**
 * Буфферизированный логгер
 * 
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

	private AbstractMap<String, Logger> mapLoggers;
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
			java.util.logging.Logger javaLogger = java.util.logging.Logger
					.getLogger(simpleClassName);
			javaLogger.addHandler(buffer);
			try {
				FileHandler fileHandler = getFileHandler();
				javaLogger.addHandler(fileHandler);
			} catch (SecurityException | IOException e) {
				e.printStackTrace();
			}
			logger = Logger.valueOf(javaLogger);
			mapLoggers.put(simpleClassName, logger);
		}
		return logger;
	}

	public BufferLogs getBuffer() {
		return buffer;
	}

	public static void main(String[] args) {
		// org.apache.log4j.Logger.getLogger(Logs.class).info("hello log4j");
/*		for(int i = 0; i < 1000; i++) {
			
			Logs.getInstance().getLogger("example").info("example1");
		}
		int i = 0;
		for (LogsRecord x : Logs.getInstance().getBuffer().getLastRecords(L)) {
			System.out.println("test " + x.getMessage() + i);
			i++;
		}*/
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(000);
		System.out.println(calendar.get(Calendar.MONTH));
		System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
		//final long deltaTime = System.currentTimeMillis() - measureTime;
	}

}
