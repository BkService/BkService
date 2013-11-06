package juniors.server.core.logic.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import juniors.server.core.data.DataManager;
import juniors.server.ext.web.listeners.StatisticInfListener;

public class StatisticService implements Runnable {

    private static AtomicInteger countsAuthUsers, countsLogouts, countsUsers;
    static {
	countsAuthUsers = new AtomicInteger(0);
	countsLogouts = new AtomicInteger(0);
	countsUsers = new AtomicInteger(0);
    }

    private Logger log = Logger.getLogger(StatisticService.class
	    .getSimpleName());

    private static final int DELAY = 1000;

    private static long lastTime;
    private static SimpleDateFormat dateFormat;
    static {
	lastTime = System.currentTimeMillis();
    }

    public StatisticService() {
    }
    
    private void saveStaticInfPerSecond() {
	
    }
    
    private void saveStaticInfPerMinute() { 
	
    }
    
    
    private void saveStaticInfPerDay() {
	
    }
    
    private void saveStaticInfPerMonth() {
	
    }
    

    private void saveStaticInf() {
	long currentTime = System.currentTimeMillis();
	long deleayTime = lastTime - currentTime;
	
	lastTime = currentTime;
	Calendar formatter = Calendar.getInstance();
	formatter.setTimeInMillis(lastTime);
	formatter.get(Calendar.MONTH);
    }

    public int getCountsUsers() {
	return countsUsers.get();
    }

    public int getCountsAuthUsers() {
	return countsAuthUsers.get();
    }

    public int getCountsLogouts() {
	return countsLogouts.get();
    }

    @Override
    public void run() {
	while (!Thread.interrupted()) {
	    countsUsers.set(DataManager.getInstance().getCountUsers());
	    int lastCountsAuthUsers = countsAuthUsers.get();
	    int intCountsAuthUsers = countsAuthUsers
		    .addAndGet(StatisticInfListener.getCountAuthUsers());
	    countsLogouts.set(intCountsAuthUsers - lastCountsAuthUsers);
	    StatisticInfListener.resetStaticInf();

	    saveStaticInf();
	    try {
		Thread.sleep(DELAY);
		System.out.println(countsAuthUsers);
	    } catch (InterruptedException e) {
		log.info("Stop static service.");
	    }
	}
    }
}
