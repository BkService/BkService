package juniors.server.core.log;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class BufferLogs extends Handler{

    private static final BufferLogs instance;
    static{
	instance = new BufferLogs();
    }
    
    private Deque<String> buffer;
    private HtmlFormatter formatter;
    private AtomicInteger countNewRecords;
    
    private BufferLogs() {
	buffer = new ConcurrentLinkedDeque<String>();
    }
    
    public static BufferLogs getInstance() {
	return instance; 
    }
    
    @Override
    public void publish(LogRecord record) {
	buffer.add(formatter.format(record));
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
	for(int i = 0; i < count; i++)
	    result[i] = iter.next();
	return result;	
    }
    
    public int countsRecords() {
	return buffer.size();
    }
    
    public static void main(String[] args) {
	
    }

}
