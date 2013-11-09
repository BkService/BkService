package juniors.server.core.logic.services;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import juniors.server.core.data.DataManager;
import juniors.server.core.logic.RunnableService;
import juniors.server.core.logic.ServerFacade;
import juniors.server.ext.web.listeners.StatisticInfListener;

public class StatisticService implements RunnableService {

    private static Logger log = Logger.getLogger(StatisticService.class
	    .getSimpleName());
    
    private ScheduledExecutorService executor;

    public static final int DELAY = 30;
    public static final TimeUnit TIME_UNIT_DELAY = TimeUnit.MINUTES;
    

    public StatisticService() {
	executor = Executors.newSingleThreadScheduledExecutor();
    }

    private void saveStaticInf() {
	DataManager.getInstance().setCountRequestPerSecond(
		ServerFacade.getCountRequest());
	ServerFacade.resetCountRequest();

	DataManager.getInstance().setCountBetPerSecond(
		BetsService.getCountBets());
	BetsService.resetCountBets();
    }

    public int getCountsUsers() {
	return DataManager.getInstance().getCountUsers();
    }

    public int getCountsAuthUsers() {
	return StatisticInfListener.getCountAuthUsers();
    }

    private class Task implements Runnable {
	@Override
	public void run() {
		saveStaticInf();
	}
    }

    @Override
    public void start() {
	executor.schedule(new Task(), DELAY, TIME_UNIT_DELAY);
    }

    @Override
    public void stop() {
	executor.shutdown();
    }

    @Override
    public boolean isStarted() {
	return !executor.isShutdown();	
    }

    @Override
    public long getDelay() {
	return DELAY;
    }

    @Override
    public TimeUnit getTimeUnitDelay() {
	return TIME_UNIT_DELAY;
    }
}
