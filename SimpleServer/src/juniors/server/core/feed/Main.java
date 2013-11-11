package juniors.server.core.feed;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


public class Main {
	public static void main(String[] args) throws ParserConfigurationException, SAXException {
	        System.out.println("start");
		FeedLoader fl = new FeedLoader();
		fl.start();
		/*
	    	FeedSAXParser parser = new FeedSAXParser();
	    	System.out.println(new Date().getTime());
	    	System.out.println(parser.parseTime("2013-11-10T19:26"));
	    	*/
	}
}
