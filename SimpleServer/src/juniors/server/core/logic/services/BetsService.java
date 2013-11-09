package juniors.server.core.logic.services;

import java.util.concurrent.atomic.AtomicInteger;

import juniors.server.core.data.events.Event;
import juniors.server.core.data.markets.Market;
import juniors.server.core.data.markets.Outcome;
import juniors.server.core.data.users.User;

/**
 * @author Dmitrii Shakshin (trueCoder)<d.shakshin@gmail.com>
 * 
 */
public class BetsService {
    private static AtomicInteger countBets;
    static {
	countBets = new AtomicInteger(0);
    }

    public boolean makeBet(User user, Event event, Market market, Outcome come) {	
/*	boolean result = DataManager.getInstance().makeBet(user, event, market,
		come, coefficient);*/
	boolean result = false;
	if(result)
	    countBets.incrementAndGet();
	return result;
    }

    public static int getCountBets() {
	return countBets.get();
    }
    
    public static void resetCountBets() {
	countBets.set(0);
    }
}
