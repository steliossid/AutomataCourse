
public class State{
	
	private int first;
	private int second;
	private int third;
	
	public State (int first){
		this.first=first;
	}
	
	public  State (int first, int second){
		this.first=first;
		this.second=second;
	}
	
	public  State (int first, int second, int third){
		this.first=first;
		this.second=second;
		this.third=third;
	}

	public int getFirst() {
		return first;
	}

	public int getSecond() {
		return second;
	}

	public int getThird() {
		return third;
	}

}
