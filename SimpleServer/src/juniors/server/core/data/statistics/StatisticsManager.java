package juniors.server.core.data.statistics;

import java.util.Calendar;
import java.util.Date;

/**
 * Инструменты для получения, хранения и редактирования статистических данных.
 * Все данные, которые обновляются реже, чем раз в минути вычисляются
 * автоматически. Но для каждого типа данных всё равно данные с минимальной
 * частотой надо обновлять вручную (если данные обновляются раз в час, день и
 * месяц, то раз в час обновлять надо руками)
 * 
 * @author kovalev
 */
public class StatisticsManager implements StatisticsManagerInterface {

	private static final long SECOND_IN_MILLISECONDS = 1000;
	private static final int MINUTE_IN_MILLISECONDS = 60000;
	private static final int HOUR_IN_MILLISECONDS = 3600000;
	private static final int DAY_IN_MILLISECONDS = 86400000;

	protected Note loginPerHour;
	protected Note loginPerDay;
	protected Note loginPerMonth;

	protected Note requestPerSecond;
	protected Note requestPerMinute;
	protected Note requestPerHour;
	protected Note requestPerDay;

	protected Note betPerSecond;
	protected Note betPerMinute;

	/**
	 * Все значения - нулевые
	 */
	public StatisticsManager() {
		loginPerHour = new Note();
		loginPerDay = new Note();
		loginPerMonth = new Note();

		requestPerSecond = new Note();
		requestPerMinute = new Note();
		requestPerHour = new Note();
		requestPerDay = new Note();

		betPerSecond = new Note();
		betPerMinute = new Note();
	}

	// fix: Dmitrii
	/**
	 * @param value
	 */
	public void addValues(long countLogins, long countRequests, long countBets) {
		final long measureTimePerHour = loginPerHour.getMeasureTime();
		if (measureTimePerHour == 0) // if first add - init current times
		{
			loginPerHour.setValue(countLogins);
			loginPerDay.setValue(countLogins);
			loginPerMonth.setValue(countLogins);

			requestPerSecond.setValue(countRequests);
			requestPerMinute.setValue(countRequests);
			requestPerHour.setValue(countRequests);
			requestPerDay.setValue(countRequests);

			betPerSecond.setValue(countBets);
			betPerMinute.setValue(countBets);

			return;
		}
		
		final long currentTime = System.currentTimeMillis();
		final long deltaTimePerSecond = currentTime
				- requestPerSecond.getMeasureTime();
		final long deltaTimePerMinute = currentTime
				- requestPerMinute.getMeasureTime();
		final long deltaTimePerHour = currentTime - measureTimePerHour;
		final long deltaTimePerDay = currentTime - loginPerDay.getMeasureTime();

		if (deltaTimePerSecond >= SECOND_IN_MILLISECONDS) {
			requestPerSecond.setToZero();
			betPerSecond.setToZero();
		}

		if (deltaTimePerMinute >= MINUTE_IN_MILLISECONDS) {
			requestPerMinute.setToZero();
			betPerMinute.setToZero();
		}

		if (deltaTimePerHour >= HOUR_IN_MILLISECONDS) {
			loginPerHour.setToZero();
			requestPerHour.setToZero();
		}

		if (deltaTimePerDay >= DAY_IN_MILLISECONDS) {
			loginPerDay.setToZero();
			requestPerDay.setToZero();
		}

		Calendar monthCalendar = Calendar.getInstance();
		monthCalendar.setTimeInMillis(loginPerMonth.getMeasureTime());
		Calendar currentCalendar = Calendar.getInstance();
		if (currentCalendar.get(Calendar.MONTH) > monthCalendar
				.get(Calendar.MONTH))
			loginPerMonth.setToZero();

		loginPerHour.addToValue(countLogins);
		loginPerDay.addToValue(countLogins);
		loginPerMonth.addToValue(countLogins);

		requestPerSecond.addToValue(countRequests);
		requestPerMinute.addToValue(countRequests);
		requestPerHour.addToValue(countRequests);
		requestPerDay.addToValue(countRequests);

		betPerSecond.addToValue(countBets);
		betPerMinute.addToValue(countBets);
	}

	public long addCountBets(long value) {
		return betPerSecond.addToValue(value);
	}

	// -----------------------------

	@Override
	public long setCountLoginPerHour(long newValue) {

		loginPerDay.addToValue(newValue);
		loginPerMonth.addToValue(newValue);

		return loginPerHour.setValue(newValue);
	}

	@Override
	public long getCountLoginPerHour() {
		return loginPerHour.getValue();
	}

	@Override
	public Note getLoginPerHour() {
		return loginPerHour;
	}

	@Override
	public long setCountLoginPerDay(long newValue) {
		return loginPerDay.setValue(newValue);
	}

	@Override
	public long getCountLoginPerDay() {
		return loginPerDay.getValue();
	}

	@Override
	public Note getLoginPerDay() {
		return loginPerDay;
	}

	@Override
	public long setCountLoginPerMonth(long newValue) {
		return loginPerMonth.setValue(newValue);
	}

	@Override
	public long getCountLoginPerMonth() {
		return loginPerMonth.getValue();
	}

	@Override
	public Note getLoginPerMonth() {
		return loginPerMonth;
	}

	@Override
	public long setCountRequestPerSecond(long newValue) {
		return requestPerSecond.setValue(newValue);
	}

	@Override
	public long getCountRequestPerSecond() {
		return requestPerSecond.getValue();
	}

	@Override
	public Note getRequestPerSecond() {
		return requestPerSecond;
	}

	@Override
	public long setCountRequestPerMinute(long newValue) {
		requestPerHour.addToValue(newValue);
		requestPerDay.addToValue(newValue);

		return requestPerMinute.setValue(newValue);
	}

	@Override
	public long getCountRequestPerMinute() {
		return requestPerMinute.getValue();
	}

	@Override
	public Note getRequestPerMinute() {
		return requestPerMinute;
	}

	@Override
	public long setCountRequestPerHour(long newValue) {
		return requestPerHour.setValue(newValue);
	}

	@Override
	public long getCountRequestPerHour() {
		return requestPerHour.getValue();
	}

	@Override
	public Note getRequestPerHour() {
		return requestPerHour;
	}

	@Override
	public long setCountRequestPerDay(long newValue) {
		return requestPerDay.setValue(newValue);
	}

	@Override
	public long getCountRequestPerDay() {
		return requestPerDay.getValue();
	}

	@Override
	public Note getRequestPerDay() {
		return requestPerDay;
	}

	@Override
	public long setCountBetPerSecond(long newValue) {
		return betPerSecond.setValue(newValue);
	}

	@Override
	public long getCountBetPerSecond() {
		return betPerSecond.getValue();
	}

	@Override
	public Note getBetPerSecond() {
		return betPerSecond;
	}

	@Override
	public long setCountBetPerMinute(long newValue) {
		return betPerMinute.setValue(newValue);
	}

	@Override
	public long getCountBetPerMinute() {
		return betPerMinute.getValue();
	}

	@Override
	public Note getBetPerMinute() {
		return betPerMinute;
	}

}
