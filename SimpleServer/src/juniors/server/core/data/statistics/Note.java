package juniors.server.core.data.statistics;

/**
 * Запись хранит значение и время создания.
 * 
 * @author kovalev
 */
public class Note {
    protected long value; // величина
    protected long measurTime; // время получения данных
    
    /**
     * Создание статистической записи
     * @param value - статистическая величина
     * @param measurTime - время, когда она была получина
     */
    public Note(long value){
        this.value = value;
        this.measurTime = System.currentTimeMillis();
    }
    
    /**
     * Величина поумолчанию равна 0. Время - время создания
     */
    public Note(){
        value = 0;
        measurTime = System.currentTimeMillis();
    }
    
    /**
     * 
     * @return величина записи
     */
    public long getValue(){
        return value;
    }
    
    /**
     * 
     * @return время, когда была снята статистика
     */
    public long getMeasureTime(){
        return measurTime;
    }
    
    /**
     * Задаёт новую величину и автоматически меняет время.
     * @param newValue
     * @return - новое время записи данных
     */
    public long setValue(long newValue){
        value = newValue;
        measurTime = System.currentTimeMillis();
        return measurTime;
    }
}
