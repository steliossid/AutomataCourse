import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		File f = new File ("task1.txt");
		ArrayList<String> ab = new ArrayList<String>();
		ArrayList<Move> allMoves = new ArrayList<Move>();
		
		try{
			Scanner sc = new Scanner(f);
			int numberofChar = sc.nextInt();
			for(int i=0; i<numberofChar; i++)
			{
				ab.add(sc.next());
			}
			int N = sc.nextInt(); //arithmos katastasewn
			int start =sc.nextInt();
			int f1 = sc.nextInt();
			int f2 = sc.nextInt();
			int noMoves=sc.nextInt();
			for(int i=0; i<noMoves; i++)
			{
				allMoves.add(new Move(sc.nextInt(), sc.next(), sc.nextInt()));
			}
			Scanner scanner = new Scanner(System.in);
			System.out.println("Give a word: ");
			String w = scanner.nextLine();
			
			int present = start;
			for(Move m: allMoves){
				for(int j=0; j < w.length(); j++) {
					
					if(m.getSource() == present 
						&& m.getC().charAt(0) == w.charAt(j)){
						
						present = m.getDestination();
					}
					else
						present=-1;
				}
			}
			
			if(present==f1 || present==f2)
				System.out.println("The word is accepted.");
			else
				System.out.println("The word is not accepted.");
			
			scanner.close();
			sc.close();
		}
		
		catch (FileNotFoundException e) {
	        System.out.println("Problem with file!");
	    }
	}
}
