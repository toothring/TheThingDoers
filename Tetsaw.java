import java.util.Scanner;

public class Tetsaw {
	private String difficulty;
	private int players;
	
	public Tetsaw(String difficulty, int players) {
		this.difficulty=difficulty;
		this.players=players;
		
		String userSelection;
		Scanner sc=new Scanner(System.in);
		if(players == 1)
			System.out.println("Welcome to Tetsaw!\nQ to exit");
		else
			System.out.println("Welcome to Tetsaw for two!\nQ to exit");
		userSelection=sc.nextLine();
			
	}
	
	public String setDifficulty() {
		return this.difficulty;
	}
	
	public int getPlayers() {
		return this.players;
	}
	
	public void setDifficulty(String difficulty) {
		this.difficulty=difficulty;
	}
	
	public void setPlayers(byte players) {
		this.players=players;
	}
}
