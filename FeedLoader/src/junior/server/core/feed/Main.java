package junior.server.core.feed;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class Main {
	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		FeedLoader fl = new FeedLoader();
		Thread t = new Thread(fl);
		//t.setDaemon(true); Now, FeedLoader thread printing log to the console
		t.start();
	}
}
