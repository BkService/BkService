package junior.server.core.data.users;

import java.util.HashMap;
import java.util.Map;

public class UserManager implements UserManagerInterface{
	
	private Map<String, User> userMap; // Контейнер с пользователями. Ключь для объекта - логин. 
	// возможно это лучше проверять javascript-ом прям на страничке, но добавлю
	private final int MAX_LENGTH_LOGIN = 20;
	private final int MIN_LENGTH_LOGIN = 5;
	
	public UserManager(){
		userMap = new HashMap<String, User>();
	}
	
	/**
	 * 
	 * @param login
	 * @return user с указанным логином 
	 */
	public UserInterface getUser(String login){
		return userMap.get(login);
	}
	
	/**
	 * Добавляет нового пользователя с заданными параметрами.
	 * Логин у каждого пользователя уникален.
	 * Так же создаёт для него новый банковский счёт (ещё не реализовано) 
	 * 
	 * @param new_login
	 * @param new_name
	 * @param new_surname
	 * @param new_password
	 * @param new_bank_account
	 * @return true - пользователь создан. False - произошла ошибка при создании.
	 */
	public boolean createUser(String new_login, String new_name, String new_surname, 
			String new_password, String new_bank_account) 
	{
		// такой логин существует
		if (userMap.containsKey(new_login)){
			return false;
		}
			
		// имя с фамилией - пустые строки (а эта проверка нужна?)
		if (new_name.length() == 0 || new_surname.length() == 0){
			return false;
		}
		
		userMap.put(new_login, 
				new User(new_login, new_name, new_surname, new_bank_account));
		
		return true;
	}
	
	/**
	 * Заменить login на new_login
	 * 
	 * @param login - старый логин
	 * @param new_login - новый логин
	 * @return true - замена выполнена. false - произошла ошибка
	 * @throws LoginIsBusyException
	 * @throws LengthLoginException 
	 */
	public boolean changeLogin(String login, String new_login)
		{
		// проверка данных
		// новый логин уже занят
		if (userMap.containsKey(new_login)){ 
			return false;
		}
		
		// не существует пользователя с указанным старым логином
		if (userMap.containsKey(login)){
			return false;
		}
						
		// замена логина. Надо доделать
		User user = userMap.get(login);
		user.setLogin(new_login);
		userMap.remove(login);
		userMap.put(new_login, user);
		
		return true;
	}
	
	/**
	 * @param login
	 * @return true - пользователь существует. false - пользователь не существует.
	 */
	public boolean containsUser(String login){
		return userMap.containsKey(login);
	}
	
	
	/*
	public boolean changePassword(String password, String new_password){
		
	}*/
	
	/**
	 * для проведения тестов
	 * @param args - не используется
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean conteinsUser(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changeUserData(String login, String new_name,
			String new_surname, String new_password, String new_bank_account) {
		// TODO Auto-generated method stub
		return false;
	}

}
