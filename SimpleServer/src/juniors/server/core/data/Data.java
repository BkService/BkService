package juniors.server.core.data;

import java.util.Collection;
import java.util.Map;
import java.util.Random;

import juniors.server.core.data.events.*;
import juniors.server.core.data.finance.TransactSaver;
import juniors.server.core.data.users.*;
import juniors.server.core.data.markets.*;
import juniors.server.core.data.bets.*;
import juniors.server.core.data.statistics.Note;
import juniors.server.core.data.statistics.StatisticsManager;
import juniors.server.core.data.statistics.StatisticsManagerInterface;

/**
 * Класс содержет методы, позволяющие управлять всеми данными, хранящимися на
 * сервере
 * 
 * @author kovalev
 * 
 */
public class Data implements UserManagerInterface, EventManagerInterface,
		StatisticsManagerInterface {

	private UserManagerInterface userManager;
	private EventManagerInterface eventManager;

    private StatisticsManagerInterface statistcsManager;
    // при сравнении double что бы убрать погрешность
    private final double DOUBLE_DELTA = 0.000001;
    private TransactSaver transactSaver;
	
	public Data(){
		userManager = new UserManager();
		eventManager = new EventManager();
        statistcsManager = new StatisticsManager();                
        transactSaver = new TransactSaver();                
	}
	
	/**
	 * @return - предыдущие событие c таким id
	 */
	@Override
	public Event addEvent(Event newEvent) {
		return eventManager.addEvent(newEvent);
	}

	/**
	 * Добавляет новый исход в маркет и в общий контейнер.
	 * 
	 * @param newOutcome
	 * @param eventId
	 * @param marketId
	 * @return - true - если всё корректно добавлено
	 */
	@Override
	public boolean addOutcome(Outcome newOutcome, int eventId, int marketId) {
		return eventManager.addOutcome(newOutcome, eventId, marketId);
	}

	@Override
	public boolean containsOutcome(int outcomeId) {
		return eventManager.containsOutcome(outcomeId);
	}

	@Override
	public Outcome getOutcome(int outcomeId) {
		return eventManager.getOutcome(outcomeId);
	}

	@Override
	public Event getEvent(int eventId) {
		return eventManager.getEvent(eventId);
	}

	@Override
	public Event removeEvent(int eventId) {
		return eventManager.removeEvent(eventId);
	}

	@Override
	public boolean containsUser(String login) {
		return userManager.containsUser(login);
	}

	@Override
	public boolean changeLogin(String login, String newLogin) {
		return userManager.changeLogin(login, newLogin);
	}

	@Override
	public User getUser(String login) {
		return userManager.getUser(login);
	}

	@Override
	public boolean authorizeUser(Integer userId) {
		return userManager.authorizeUser(userId);
	}

	@Override
	public int getCountUsers() {
		return userManager.getCountUsers();
	}

	@Override
	public int getCountAuthorizedUsers() {
		return userManager.getCountAuthorizedUsers();
	}

	@Override
	public boolean createUser(String newLogin, String newName,
			String newSurname, String newPassword, String newBankAccount) {
		return userManager.createUser(newLogin, newName, newSurname,
				newPassword, newBankAccount);
	}

	@Override
	public boolean changeUserData(String login, String newName,
			String newSurname, String newPassword, String newBankAccount) {
		return userManager.changeUserData(login, newName, newSurname,
				newPassword, newBankAccount);
	}

	@Override
	public Map<Integer, Event> getEventsMap() {
		return eventManager.getEventsMap();
	}

	@Override
	public Collection<Event> getEventsCollection() {
		return eventManager.getEventsCollection();
	}

	/**
	 * 
	 * @param eventId
	 *            - id события, маркеты которого нужны.
	 * @return
	 */
	public Map<Integer, Market> getMarketsMap(Integer eventId) {
		return eventManager.getEvent(eventId).getMarketsMap();
	}

	/**
	 * 
	 * @param eventId
	 * @param marketId
	 * @return Map со списком исходов для маркета marketId
	 */

	public Map<Integer, Outcome> getOutcomeMap(Integer eventId, Integer marketId) {
		return eventManager.getEvent(eventId).getMarket(marketId)
				.getOutcomeMap();
	}

	@Override
	public boolean containsEvent(int eventId) {
		return eventManager.containsEvent(eventId);
	}

	protected Outcome getOutcome(int eventId, int markedId, int outcomeId) {
		return eventManager.getEvent(eventId).getMarket(markedId)
				.getOutcome(outcomeId);
	}

	/**
	 * 
	 * @param userId
	 * @param eventId
	 * @param marketId
	 * @param outcomeId
	 * @return
	 */
	public Bet makeBet(String userLogin, int outcomeId, Float sum,
			double coefficient) {
		// корректны ли данные запрса
		if (!containsUser(userLogin) || !containsOutcome(outcomeId)) {
			System.out.println("fail");
			return null;
		}
		Outcome currentOutcome = getOutcome(outcomeId);

		// если коеффициенты не совпадают
		/*
		 * if (Math.abs(currentOutcome.getCoefficient() - coefficient) >
		 * DOUBLE_DELTA){ return false; }
		 */
		Bet newBet = new Bet(getUser(userLogin), currentOutcome,
				currentOutcome.getCoefficient(), sum);

		// Записываю везде ставку. Если не удалось - тоже ошибка
		if (!currentOutcome.addBet(newBet)) {
			return null;
		}
		if (!getUser(userLogin).addBet(newBet)) {
			currentOutcome.removeBet(newBet);
			return null;
		}
		getBookmaker().addBet(newBet);

		return newBet;
	}

	// / ALEX: I AM USING THIS STUB (DISPATCHER)
	public boolean makeBet(String login, int outcomeId, int sum) {
		return makeBet(login, outcomeId, (float) sum, 0.01) != null;
	}

	@Override
	public long setCountLoginPerHour(long newValue) {
		return statistcsManager.setCountLoginPerHour(newValue);
	}

	@Override
	public long getCountLoginPerHour() {
		return statistcsManager.getCountLoginPerHour();
	}

	@Override
	public Note getLoginPerHour() {
		return statistcsManager.getLoginPerHour();
	}

	@Override
	public long setCountLoginPerDay(long newValue) {
		return statistcsManager.setCountLoginPerDay(newValue);
	}

	@Override
	public long getCountLoginPerDay() {
		return statistcsManager.getCountLoginPerDay();
	}

	@Override
	public Note getLoginPerDay() {
		return statistcsManager.getLoginPerDay();
	}

	@Override
	public long setCountLoginPerMonth(long newValue) {
		return statistcsManager.setCountLoginPerMonth(newValue);
	}

	@Override
	public long getCountLoginPerMonth() {
		return statistcsManager.getCountLoginPerMonth();
	}

	@Override
	public Note getLoginPerMonth() {
		return statistcsManager.getLoginPerMonth();
	}

	@Override
	public long setCountRequestPerSecond(long newValue) {
		return statistcsManager.setCountRequestPerSecond(newValue);
	}

	@Override
	public long getCountRequestPerSecond() {
		return statistcsManager.getCountRequestPerSecond();
	}

	@Override
	public Note getRequestPerSecond() {
		return statistcsManager.getRequestPerSecond();
	}

	@Override
	public long setCountRequestPerMinute(long newValue) {
		return statistcsManager.setCountRequestPerMinute(newValue);
	}

	@Override
	public long getCountRequestPerMinute() {
		return statistcsManager.getCountRequestPerMinute();
	}

	@Override
	public Note getRequestPerMinute() {
		return statistcsManager.getRequestPerMinute();
	}

	@Override
	public long setCountRequestPerHour(long newValue) {
		return statistcsManager.setCountRequestPerHour(newValue);
	}

	@Override
	public long getCountRequestPerHour() {
		return statistcsManager.getCountRequestPerHour();
	}

	@Override
	public Note getRequestPerHour() {
		return statistcsManager.getRequestPerHour();
	}

	@Override
	public long setCountRequestPerDay(long newValue) {
		return statistcsManager.setCountRequestPerDay(newValue);
	}

	@Override
	public long getCountRequestPerDay() {
		return statistcsManager.getCountRequestPerDay();
	}

	@Override
	public Note getRequestPerDay() {
		return statistcsManager.getRequestPerDay();
	}

	@Override
	public long setCountBetPerSecond(long newValue) {
		return statistcsManager.setCountBetPerSecond(newValue);
	}

	@Override
	public long getCountBetPerSecond() {
		return statistcsManager.getCountBetPerSecond();
	}

	@Override
	public Note getBetPerSecond() {
		return statistcsManager.getBetPerSecond();
	}

	@Override
	public long setCountBetPerMinute(long newValue) {
		return statistcsManager.setCountBetPerMinute(newValue);
	}

	@Override
	public long getCountBetPerMinute() {
		return statistcsManager.getCountBetPerMinute();
	}

	@Override
	public Note getBetPerMinute() {
		return statistcsManager.getBetPerMinute();
	}

	/**
	 * Реализация транзакции самописным способом
	 * 
	 * @param login
	 *            - логин Игрока
	 * @param betId
	 *            - id ставки
	 * @param sum
	 *            - если 0, то ставка Игрока проиграна. Иначе счёт его
	 *            увеличивается на sum
	 * @return true - транзакция прошла успешно
	 * @throws InterruptedException
	 */
	public boolean makeTransact(String login, int betId, float sum)
			throws InterruptedException {
		// получаю User
		if (!containsUser(login)) {
			return false;
		}
		User user = getUser(login);
		Bet bet = user.getBet(betId);
		Bookmaker bookmaker = getBookmaker();

		// есть ли такая ставка и не рассчитывалась ли она
		if (bet == null || !transactSaver.addTransact(bet)) {
			return false;
		}

		user.getBalance().lockBalance();
		bookmaker.getBalance().lockBalance();

		// если ошибка у игрока, транзакция прерывается и сохраняется ошибка
		if (!user.calculateBet(bet, sum)) {
			transactSaver.addFailTransact(bet);

			user.getBalance().unlockBalance();
			bookmaker.getBalance().unlockBalance();

			return false;
		}

		// если ошибка у букмекера, игроку возвращается ставка
		// операция отменяется и сохраняется ошибка
		if (!bookmaker.calculateBet(bet, sum)) {
			transactSaver.addFailTransact(bet);
			user.addBetAgain(bet, sum);

			user.getBalance().unlockBalance();
			bookmaker.getBalance().unlockBalance();

			return false;
		}

		user.getBalance().unlockBalance();
		bookmaker.getBalance().unlockBalance();

		return true;
	}

	@Override
	public Bookmaker getBookmaker() {
		return userManager.getBookmaker();
	}

	/**
	 * Создание события, нескольких маркетов и несколько исходов к ним Далее
	 * создаётся пользователь, ставит несколько ставок и проводится рассчёт по
	 * ставкам Все операции проверяются. При ошибках выводятся сообщения с
	 * описанием ошибки. Запускать рекомендуется через main класса Data. Там и
	 * инфраструктура для этого реализована.
	 * 
	 * @param data
	 * @throws Exception
	 */
	public static void testUser(Data data) throws Exception {
		Random rand = new Random();
		int eventId = rand.nextInt();
		long startTime = (long) rand.nextInt();
		int marketId = rand.nextInt();
		int outcomeId = rand.nextInt();
		int marketId1 = rand.nextInt();
		int outcomeId1 = rand.nextInt();
		double coefficient = 1.5d;
		float sum = 10f;
		String userLogin = new String();
		userLogin += rand.nextInt();
		User user = null;
		Bookmaker bookmeker = data.getBookmaker();

		Event e = new Event(eventId, startTime);
		Market m = new Market(marketId);
		Outcome o = new Outcome(outcomeId, coefficient);

		Market m1 = new Market(marketId1);
		Outcome o1 = new Outcome(outcomeId1, coefficient);

		Bet b1 = null;
		Bet b2 = null;

		// добавляю пользователя
		if (!data.createUser(userLogin, userLogin, userLogin, userLogin,
				userLogin)) {
			throw new Exception(
					"Ошибка при добавлении пользователя userLogin = "
							+ userLogin);
		}
		user = data.getUser(userLogin);

		// добавление событий, маркетов и исходов
		if (null != data.addEvent(e)) {
			throw new Exception(
					"Ошибка при добавлении События data.addEvent, где user="
							+ userLogin);
		}

		if (e.addMarket(m) != null) {
			throw new Exception(
					"Ошибка при добавлении маркета Event.addMarket, где user="
							+ userLogin);
		}
		if (!data.addOutcome(o, e.getEventId(), m.getMarketId())) {
			throw new Exception(
					"Ошибка при добавлении исхода Market.addOutcome, где user="
							+ userLogin);
		}
		if (e.addMarket(m1) != null) {
			throw new Exception(
					"Ошибка при добавлении маркета Event.addMarket, где user="
							+ userLogin);
		}
		if (!data.addOutcome(o1, e.getEventId(), m1.getMarketId())) {
			throw new Exception(
					"Ошибка при добавлении исхода Market.addOutcome, где user="
							+ userLogin);
		}

		// делаю ставки
		if (null == data.makeBet(user.getLogin(), o.getOutcomeId(), sum, 
				o.getCoefficient())) {
			throw new Exception("Ставка не ставится user = " + user.getLogin()
					+ " Outcome " + o.getOutcomeId());
		}

		if (null == data.makeBet(user.getLogin(), o1.getOutcomeId(), sum, 
				o1.getCoefficient())) {
			throw new Exception("Ставка не ставится user = " + user.getLogin()
					+ " Outcome " + o1.getOutcomeId());
		}
		
		b1 = o.getBets().iterator().next();
		b2 = o1.getBets().iterator().next();

		// рассчёт по ставкам
		if (!data.makeTransact(userLogin, b1.getBetId(), 0)) {
			throw new Exception("Ошибка при транзакции");
		}
		if (!data.makeTransact(userLogin, b2.getBetId(), 15f)) {
			throw new Exception("Ошибка при транзакции");
		}

	}

	/**
	 * Метод для тестирования
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Data data = new Data();

		testUser(data);
	}

}
