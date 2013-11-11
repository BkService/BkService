package juniot.server.core.resultprovider;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import juniors.server.core.data.DataManager;
import juniors.server.core.data.events.Event;
import juniors.server.core.data.markets.Market;
import juniors.server.core.data.markets.Outcome;
import juniors.server.core.logic.RunnableService;
import juniors.server.core.logic.TimeChecker;

public class ResultProvider implements RunnableService {

    ScheduledExecutorService service;
    boolean isStarted = false;;
    int periodSec = 60;
    TimeChecker timeChecker;
    
    
    public ResultProvider() {
	timeChecker = new TimeChecker();
    }

    @Override
    public void start() {
	if (!isStarted) {
	    service = Executors.newSingleThreadScheduledExecutor();
	    isStarted = true;
	    service.scheduleAtFixedRate(new Runnable() {

		@Override
		public void run() {
		    update();
		}
	    }, 60, 60, TimeUnit.SECONDS);
	}
    }

    @Override
    public void stop() {
	isStarted = false;
	service.shutdown();
    }

    @Override
    public boolean isStarted() {
	return isStarted;
    }

    @Override
    public long getDelay() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public TimeUnit getTimeUnitDelay() {
	// TODO Auto-generated method stub
	return null;
    }
    private void update() {
	Map<Integer, Event> events = DataManager.getInstance().getEventsMap();
	for (Event event : events.values()) {
	    if (timeChecker.checkOccured(event)) {
		for (Market market : event.getMarketsMap().values()) {
		    Random r = new Random();
		    Map<Integer, Outcome> outcomes = market.getOutcomeMap();
		    int outcomeIndex = r.nextInt(outcomes.size());
		    Iterator<Outcome> iterator = outcomes.values().iterator();
		    for (int i = 0; i < outcomeIndex; i++) {
			iterator.next();
		    }
		    market.finish(iterator.next());
		}
	    }
	}
    }
}
