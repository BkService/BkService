package juniors.server.core.logic;

import java.util.HashMap;
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

    private static final ServerFacade instance;
    static {
	instance = new ServerFacade();
    }

    private HashMap<Integer, RunnableService> services;

    private FeedLoader fl;

    // FIXME добавить интерфейс RunnableService
    // в службу и удалить поле
    private Thread feedLoaderThread;

    public static final int COUNT_SERVICES = 2;

    public static final Integer ID_SERVICE_FEEDLOADER = 1;
    public static final Integer ID_SERVICE_STATISTIC = 2;

    private static AtomicInteger countRequests;
    static {
	countRequests = new AtomicInteger(0);
    }

    public static ServerFacade getInstance() {
	return instance;
    }

    private ServerFacade() {
	fl = new FeedLoader();
	services = new HashMap<Integer, RunnableService>(COUNT_SERVICES);
    }

    private void initServices() {
	// services.put(ID_SERVICE_FEEDLOADER, value)
	services.put(ID_SERVICE_STATISTIC, (RunnableService) Services
		.getInstance().getStatisticService());
    }

    private void runAllServices() {
	feedLoaderThread = new Thread(fl);
	feedLoaderThread.setDaemon(true);
    }

    private void stopAllServices() {
	fl.stop();
    }

    public synchronized void start() {
	if (!started) {
	    runAllServices();
	    started = true;
	}
    }

    /**
     * @see getStatusService();
     * @return status thread feed loader
     */
    @Deprecated
    public boolean getStatusFL() {
	return feedLoaderThread.isAlive();
    }

    public boolean startService(Integer id) {
	if (started) {
	    RunnableService service = services.get(id);
	    if (!service.isStarted())
		return false;
	    service.start();
	}
	return false;
    }

    public boolean stopService(Integer id) {
	if (started) {
	    RunnableService service = services.get(id);
	    if (service.isStarted())
		return false;
	    service.start();
	}
	return false;
    }

    /**
     * Получение статуса службы
     * 
     * @param id
     * @return если службы запущена - возвращает true
     */
    public boolean getStatusService(Integer id) {
	RunnableService service = services.get(id);
	return (started == false) || (service == null) ? false : service
		.isStarted();
    }

    public boolean getStatusServer() {
	return started;
    }

    public void stop(Integer id) {
	RunnableService service = services.get(id);
	if (service != null)
	    service.stop();
    }

    public synchronized void stop() {
	stopAllServices();
	started = false;
    }

    public Services getServices() {
	Services services = null;
	if (started) {
	    services = Services.getInstance();
	    countRequests.incrementAndGet();
	}
	return services;
    }

    public static int getCountRequest() {
	return countRequests.get();
    }

    public static void resetCountRequest() {
	countRequests.set(0);
    }
}
