package juniors.server.core.data.users;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

import juniors.server.core.data.bets.Bet;

/**
 * Хранит данные о пользователе, его ставки, даёт возможность
 * задать и получить эти данные 
 * 
 * @author kovalev
 *
 */
public class User{
	protected String login;
	protected String name;
	protected String surname;
	protected String password; // научиться правильно хранить пароль
	protected String bankAccount; // номер банковского счёта
	protected Set<Bet> bets; // контейнер с ссылками на ставки, которые делал пользователь
	protected boolean isAuthorized;	// если авторизован - true
	long lastTimeActive;	// время последней активности пользователя. 
	
	/**
	 * 
	 * @param newLogin
	 * @param newName
	 * @param newSurname
	 * @param newPassword
	 * @param newBankAccount
	 */
	public User(String newLogin, String newName, String newSurname, 
			String newPassword, String newBankAccount){
		login = newLogin;
		name = newName;
		surname = newSurname;
		bankAccount = newBankAccount;
		password = newPassword;
		
		bets = new ConcurrentSkipListSet<Bet>();
		lastTimeActive = System.currentTimeMillis();
	}
	
	/**
	 * 
	 * @return String login
	 */
	public String getLogin(){
		return login;
	}
	
	/**
	 * 
	 * @param String new_login
	 */
	public void setLogin(String newLogin){
		login = newLogin;
	}
	
	/**
	 * 
	 * @return String name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @param String new_name
	 */
	public void setName(String newName){
		name = newName;
	}
	
	/**
	 * 
	 * @return String surname
	 */
	public String getSurname(){
		return surname;
	}
	
	/**
	 * 
	 * @param newSurname
	 */
	public void setSurname(String newSurname){
		surname = newSurname;
	}
	
	/**
	 * 
	 * @return String bank_account
	 */
	public String getBankAccount(){
		return bankAccount;
	}
	
	/**
	 * 
	 * @param newBankAccount
	 */
	public void setBankAccount(String newBankAccount){
		bankAccount = newBankAccount;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String newPassword) {
		password = newPassword;
	}

	
	public boolean addBet(Bet newBet) {
		return bets.add(newBet);
	}

	
	public Set<Bet> getBets() {
		return bets;
	}
	
	
	
	
	
	
	
}
