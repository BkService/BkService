package juniors.server.ext.web.stubs;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Event {

	String eventName;
	Date dateStart;
	Date dateFinish;
	
	ArrayList<Market> markets = new ArrayList<Market>();
	
	public ArrayList<Market> getMarkets() {
		return this.markets;
	}
	
	public Event(Date start, Date finish){
		this.dateFinish = finish;
		this.dateStart = start;
	}
	
	public void generateTestData(String desc) {
		for(int i = 0; i < 5; ++i) {
			Market m = new Market();
			for(int j = 0; j < 4; ++j) {
				Result r = new Result((new Random().nextInt(10)), "some decs");
				m.results.add(r);
			}
			markets.add(m);
		}
		this.eventName = desc;
	}
	
	public String toString() {
		return this.eventName;
	}
}
