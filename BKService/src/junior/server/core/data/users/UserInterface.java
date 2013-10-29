package junior.server.core.data.users;

import java.util.List;

import junior.server.core.data.bets.Bet;

/**
 * Предоставляет интерфейс взаимодействия с данными пользователя
 * @author kovalev
 *
 */
public interface UserInterface {
	
	public String getLogin();
	
	public void setLogin(String new_login);
	
	public String getName();
	
	public void setName(String new_name);
	
	public String getSurname();
	
	public void setSurname(String new_surname);
	
	public String getPassword();
	
	public void setPassword(String new_password);
	
	public String getBankAccount();
	
	public void setBankAccount(String new_bank_account);
	
	public List<Bet> getBets();
	
	public void addBet(Bet new_bet);
}
