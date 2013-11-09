package juniors.server.core.logic.services;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import juniors.server.core.data.DataManager;
import juniors.server.core.logic.RunnableService;
import juniors.server.core.logic.ServerFacade;

public class StatisticService implements RunnableService {

    private static Logger log = Logger.getLogger(StatisticService.class
	    .getSimpleName());

    public static final int DELAY = 1;
    public static final TimeUnit TIME_UNIT_DELAY = TimeUnit.SECONDS;

    private ScheduledExecutorService executor;

    private boolean started = false;

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

    public long getCountLoginsPerHour() {
	return DataManager.getInstance().getCountLoginPerHour();
    }

    public long getCountLoginsPerDay() {
	return DataManager.getInstance().getCountLoginPerDay();
    }

    public long getCountLoginsPerMonth() {
	return DataManager.getInstance().getCountLoginPerMonth();
    }

    public long getCountRequestsPerSecond() {
	return DataManager.getInstance().getCountRequestPerSecond();
    }

    public long getCountRequestsPerMinute() {
	return DataManager.getInstance().getCountRequestPerMinute();
    }

    public long getCountRequestsPerHour() {
	return DataManager.getInstance().getCountRequestPerHour();
    }

    public long getCountRequestsPerDay() {
	return DataManager.getInstance().getCountRequestPerDay();
    }

    public long getCountBetsPerSeconds() {
	return DataManager.getInstance().getCountBetPerSecond();
    }

    public int getCountsUsers() {
	return DataManager.getInstance().getCountUsers();
    }

    private class Task implements Runnable {
	@Override
	public void run() {
	    saveStaticInf();
	}
    }

    @Override
    public void start() {
	if (!started) {
	    executor.scheduleWithFixedDelay(new Task(), 0, DELAY,
		    TIME_UNIT_DELAY);
	    started = true;
	}
    }

    @Override
    public void stop() {
	if (started) {
	    executor.shutdown();
	    started = false;
	}
    }

    @Override
    public boolean isStarted() {
	return started;
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
