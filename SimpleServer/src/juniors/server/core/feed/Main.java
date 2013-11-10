package juniors.server.core.feed;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class Main {
	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		FeedLoader fl = new FeedLoader();
		fl.start();
	}
}
