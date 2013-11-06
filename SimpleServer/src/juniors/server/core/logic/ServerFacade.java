package juniors.server.core.logic;

import java.util.concurrent.atomic.AtomicInteger;

import juniors.server.core.feed.FeedLoader;
import juniors.server.core.logic.services.Services;
import juniors.server.core.logic.services.StatisticService;

/**
 * 
 * @author Dmitrii Shakshin (trueCoder)<d.shakshin@gmail.com>
 */
public class ServerFacade {

    private boolean started = false;

    private static volatile ServerFacade instance;

    private FeedLoader fl;
    private StatisticService stService;

    private static AtomicInteger countsGetServices;
    static {

    }

    private Thread feedLoaderThread;

    private Thread staticServiceThread;

    public static ServerFacade getInstance() {
	ServerFacade localInstance = instance;
	if (localInstance == null) {
	    synchronized (ServerFacade.class) {
		localInstance = instance;
		if (localInstance == null) {
		    instance = localInstance = new ServerFacade();
		}
	    }
	}
	return localInstance;
    }

    public ServerFacade() {
	fl = new FeedLoader();
	stService = new StatisticService();
    }

    private void runServices() {
	feedLoaderThread = new Thread(fl);
	feedLoaderThread.setDaemon(true);
	staticServiceThread = new Thread(stService);
	feedLoaderThread.start();
	staticServiceThread.start();
    }

    private void stopServices() {
	staticServiceThread.interrupt();
	fl.stop();
	feedLoaderThread.interrupt();
    }

    public synchronized void start() {
	if (!started) {
	    runServices();
	    started = true;
	}
    }

    public boolean getStatusFL() {
	System.out.println(feedLoaderThread.isAlive());
	System.out.println(feedLoaderThread.isInterrupted());
	System.out.println(feedLoaderThread.getState());
	return feedLoaderThread.isAlive();
    }

    public synchronized void stop() {
	stopServices();
	started = false;
    }

    public Services getServices() {
	Services services = null;
	if (started) {
	    services = Services.getInstance();
	}
	return services;
    }
}
