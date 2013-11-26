package juniors.server.core.data.users;

import juniors.server.core.data.bets.Bet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Класс для хранения финансовой информации. 
 * @author kovalev
 *
 */
public class Balance {
    // сумма на счёте, доступная для операций
    private float available;
    // хранит ID ставки и ёё сумму
    private Map<Integer, Float> reserve;
    private volatile boolean lockBalance; // запрет на операции с балансом
    
    public Balance() {
    	reserve = new ConcurrentHashMap<Integer, Float>();
    	lockBalance = false;
    }
    
    /**
     * лочит доступ к балансу. ПОСЛЕ ВЫПОЛНЕНИЯ САМ ДОЛЖЕН РАЗЛОЧИТЬ!
     * @throws InterruptedException
     */
    public void lockBalance() throws InterruptedException{
	while (lockBalance){
	    Thread.sleep(100);
	}
	
	lockBalance = true;
    }
    
    /**
     * освободить баланс
     */
    public void unlockBalance() {
	lockBalance = false;
    }
    
    /**
     * Ждёт освобождения баланса
     * @throws InterruptedException
     */
    private void waitUnlockedBalance() throws InterruptedException{
	while (lockBalance){
	    Thread.sleep(100);
	}
    }
    
    /**
     * @return - доступный баланс
     * @throws InterruptedException 
     */
    public float getBalance() throws InterruptedException{
	waitUnlockedBalance();
	return available;
    }
    
    /**
     * Изменяет доступный баланс
     * @param sum - сумма операции
     * @return - новый баланс
     */
    public float changeBalance(float sum){
	return available += sum;
    }
    
    /**
     * Добавляет сумму в резерв, вычитая её из доступного баланса
     * @param betId
     * @param sum
     * @return - false - ставка уже существует
     */
    public boolean addToReserve(int betId, float sum){
	if (reserve.containsKey(betId)){
	    return false;
	}
	
	available -= sum;
	reserve.put(betId, sum);
	
	return true;
    }
    
    /**
     * Удаляет ресерв, не проводя действий над доступным балансом
     * @param betId 
     * @return - false - если не существовало такой ставки
     */
    public boolean removeFromReserve(int betId){
	return !(reserve.remove(betId) == null);
    }
    
    
    public boolean containsReserve(int betId){
	return reserve.containsKey(betId);
    }
    
    /**
     * Возвращает сумму ставки.
     * @param betId
     * @return сумма ставки
     */
    public float getSumOfBet(int betId){
	return reserve.get(betId);
    }
    
    /**
     * @return - доступный баланс в формате int 
     */
    public int getBalanceValue() {
    	return (int)this.available;
    }
    
    
}
