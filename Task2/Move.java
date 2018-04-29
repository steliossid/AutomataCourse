
public class Move{
	
	private int source;
	private String c;
	private int target;
	
	public Move(int source, String c, int target){
		this.source=source;
		this.c=c;
		this.target=target;
	}
	
	public int getSource() {
		return source;
	}

	public String getC() {
		return c;
	}

	public int getTarget() {
		return target;
	}

	public void printMove(){
		System.out.println("source: "+source+" c: "+c+" target: "+target);
	}

}
