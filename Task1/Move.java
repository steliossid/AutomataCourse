
public class Move{
	
	private int source;
	private String c;
	private int destination;
	
	public Move(int source, String c, int target){
		this.source=source;
		this.c=c;
		this.destination=destination;
	}
	
	public int getSource() {
		return source;
	}

	public String getC() {
		return c;
	}

	public int getDestination() {
		return destination;
	}
}
