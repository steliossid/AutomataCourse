import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		File f = new File ("task1.txt");
		ArrayList <String> alphabet = new ArrayList<String>();
		ArrayList<Move> moves = new ArrayList<Move>();
		ArrayList<State> states = new ArrayList<State>();
		ArrayList<State> optionA = new ArrayList<State>();
		ArrayList<State> optionB = new ArrayList<State>();
		ArrayList<State> dfaStates = new ArrayList<State>();

		try{

			Scanner sc = new Scanner(f);

			int numberofChar = sc.nextInt();
			for(int i=0; i<numberofChar; i++)
			{
				alphabet.add(sc.next());
			}
			int K = sc.nextInt();
			int initial =sc.nextInt();
			int final1 = sc.nextInt();
			int final2 = sc.nextInt();
			int numberOfMoves=sc.nextInt();
			for(int i=0; i<numberOfMoves; i++)
			{
				moves.add(new Move(sc.nextInt(), sc.next(), sc.nextInt()));
			}

			State state0 = new State(0);
			State state1 = new State(1);
			State state2 = new State(2);
			State state3 = new State(3);
			State state12 = new State(1,2);
			State state13 = new State(1,3);
			State state23 = new State(2,3);
			State state123 = new State(1,2,3);

			states.add(state0);
			states.add(state1);
			states.add(state2);
			states.add(state3);
			states.add(state12);
			states.add(state13);
			states.add(state23);
			states.add(state123);

			for(State s: states){
				for(Move m: moves){
					if(m.getC()=="a"){
						//an to 1o psifio einai mhden -- katastash 0
						if (s.getFirst() == 0){
							optionA.add(state0);
						}
						//an to 2o psifio einai mhden -- Monopsifios
						else if (s.getSecond()== 0){

							int optionAfirst=0;
							int optionAsecond=0;
							if(s.getFirst() == m.getSource()){
								optionAfirst = m.getTarget();
								for(Move m2: moves){
									if(m2.getC()=="a"){
										if(s.getSecond() == m2.getSource()){
											optionAsecond = m2.getTarget();
										}
									}	
								}
								optionA.add(new State(optionAfirst,optionAsecond));
							}
							else
								optionA.add(new State(0,0));
						}
						//an to 3o psifio einai mhden -- Dipsifios
						else if (s.getThird()== 0){
							int optionAfirst=0;
							int optionAsecond=0;
							if(s.getFirst() == m.getSource()){
								optionAfirst = m.getTarget();
								for(Move m2: moves){
									if(m2.getC()=="a"){
										if(s.getSecond() == m2.getSource()){
											optionAsecond = m2.getTarget();
										}
									}	
								}
								optionA.add(new State(optionAfirst,optionAsecond));
							}
							else
								optionA.add(new State(0,0));
						}
						// An kanena den einai mhden -- Tripsifios
						else {

						}
					}
					//gia to m.get(C) ==="b"
					else{
						//an to 1o psifio einai mhden -- katastash 0
						if(s.getFirst()==0){
							optionA.add(state0);
						}
						//an to 2o psifio einai mhden -- Monopsifios
						else if(s.getSecond()== 0){
							optionB.add(new State(m.getTarget()));
						}

						//an to 3o psifio einai mhden -- Dipsifios
						else if (s.getThird()== 0){
							int optionBfirst = 0;
							int optionBsecond = 0;
							if(s.getFirst() == m.getSource()){
								optionBfirst = m.getTarget();
								for(Move m2: moves){
									if(m2.getC()=="b"){
										if(s.getSecond() == m2.getSource()){
											optionBsecond = m2.getTarget();
										}
									}	
								}
							}
							optionA.add(new State(optionBfirst,optionBsecond));
						}
						// An kanena den einai mhden -- Tripsifios
						else{

						}
					}
				}
			}
			
			int numberOfDfaMoves = 0;

			boolean flag = true;
			while(flag){
				int current1 = initial;
				int current2 = 0;
				dfaStates.add(new State(initial, 0));
				for(int i=initial; i<states.size();i++){
					if(states.get(i).getFirst()!= optionA.get(i).getFirst() &&
							optionA.get(i).getFirst()!=0){
						current1 = optionA.get(i).getFirst();
						current2 = optionA.get(i).getSecond();
						if(current1==states.get(i+1).getFirst() && current2==states.get(i+1).getSecond()){
							dfaStates.add(new State(current1, current2));
							numberOfDfaMoves++;
						}
					}
					/*stin periptwsi pou den yparxei metavasi me telesti a, dhl. exv kataxwrhsh 0
					 h' me a phgainw sthn idia katastash, phgainw sthn arrayList optionB */
					if(states.get(i).getFirst()!= optionB.get(i).getFirst() &&
							optionB.get(i).getFirst()!=0){
						current1 = optionB.get(i).getFirst();
						current2 = optionB.get(i).getSecond();
						if(current1==states.get(i+1).getFirst() && current2==states.get(i+1).getSecond()){
							dfaStates.add(new State(current1, current2));
							numberOfDfaMoves++;
						} 
					}
					//Evresi tou stoixeiou(current1,current2) sthn arrayList dfaStates
					for(int q=0; q<dfaStates.size(); q++)
						if(current1== dfaStates.get(q).getFirst()&&
						current2==dfaStates.get(q).getSecond())
							flag = false;
				}
			}
			
			PrintWriter w = new PrintWriter("textout.txt", "UTF-8");
			
		    w.println(numberofChar);
		    for(int i=0; i<numberofChar; i++)
			{
				w.print(alphabet.get(i)+" ");
				
			}
		    w.println();
		    w.println(dfaStates.size());
		    w.println(initial);
		    
		    for(int i=0; i<dfaStates.size(); i++){
		    	if(dfaStates.get(i).getFirst()==2 || dfaStates.get(i).getFirst()==3 || dfaStates.get(i).getSecond()==3){
		    		if(dfaStates.get(i).getSecond()==0)
		    			w.print(""+dfaStates.get(i).getFirst());
		    		
		    		else
		    			w.print(dfaStates.get(i).getFirst()+""+dfaStates.get(i).getSecond());
		    	}
		    }
		    
		    w.println(numberOfDfaMoves);
		    
		    for(int i=0; i<states.size(); i++){
		    	for(int j=0; j<dfaStates.size(); j++){
		    		if(dfaStates.get(j).equals(states.get(i))){
		    			if(optionA.get(i) != state0){
		    				w.print(states.get(i));
		    				w.print("a ");
		    				w.print(optionA.get(i));
		    			}
		    			if(optionB.get(i) != state0){
		    				w.print(states.get(i));
		    				w.print("b ");
		    				w.println(optionB.get(i));
		    			}
		    				
		    		}
		    	}
		    }

		    w.close();
			sc.close();
		}

		catch (IOException e) {
			System.out.println("Problem with file!");
		}
	}
}
