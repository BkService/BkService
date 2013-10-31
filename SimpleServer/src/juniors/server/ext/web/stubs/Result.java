package juniors.server.ext.web.stubs;

public class Result {

	int coefficient;
	String description;
	
	public void setCoeff(int c) {
		this.coefficient = c;
	}
	
	public int getCoeff(){
		return this.coefficient;
	}
	
	public Result(int c, String desc){
		this.coefficient = c;
		this.description = (desc == null) ? "hz" : desc;
	}
	
	public String getDescription() {
		return this.description;
	}
}
