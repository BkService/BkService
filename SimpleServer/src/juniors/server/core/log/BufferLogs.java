package juniors.server.core.log;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class BufferLogs extends Handler implements Runnable {

    private static final BufferLogs instance;

    public static final String ENCODING = "UTF-8";
    public static final String DEFAULT_PATH = "%t/simpleServerLogs/java%g.log";
    
    public static final TimeUnit TIME_UNIT= TimeUnit.MINUTES;
    public static final int DELAY = 30;

    static {
	instance = new BufferLogs();
    }

    private Deque<String> buffer;

    private String path;

    private BufferLogs() {
	buffer = new ConcurrentLinkedDeque<String>();
	path = DEFAULT_PATH;
    }

    public static BufferLogs getInstance() {
	return instance;
    }

    @Override
    public void publish(LogRecord record) {
	buffer.add(record.getMessage());
    }

    @Override
    public void flush() {
	buffer.clear();
    }

    @Override
    public void close() throws SecurityException {
	buffer.clear();
    }

    public String[] getLastRecords(int count) {
	int size = (buffer.size() < count) ? buffer.size() : count;
	String[] result = new String[size];
	if (size == 0)
	    return result;
	Iterator<String> iter = buffer.iterator();
	for (int i = 0; i < count; i++)
	    result[i] = iter.next();
	return result;
    }

    public int countsRecords() {
	return buffer.size();
    }

    public void setPath(String path) {
	this.path = path;
    }

    @Override
    public void run() {
	try {
	    PrintWriter writer = new PrintWriter(new BufferedWriter(
		    new OutputStreamWriter(new FileOutputStream(path, true),
			    ENCODING)));
	    for (String record : buffer) {
		writer.println(record);
		writer.flush();
	    }
	    writer.close();
	} catch (UnsupportedEncodingException | FileNotFoundException e) {
	    System.out.println(e.getMessage());
	}

    }
}
