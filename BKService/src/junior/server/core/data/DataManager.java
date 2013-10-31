package junior.server.core.data;

public class DataManager {
	private static volatile Data instance;
	
    static {
    	instance = new Data();
    }
   
    public DataManager(){
    	
    }
    
    
    
    public static Data getInstance() {
        Data localInstance = instance;
        if(localInstance == null) {
              synchronized(Data.class) {
                  instance = localInstance = new Data();                       
              }
        }
        return localInstance;
   }
}
