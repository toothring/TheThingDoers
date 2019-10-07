import java.util.Scanner;

public class Tetris {
	private int players;
	
	public Tetris(int players) {
		this.players=players;
		
		String userSelection;
		Scanner sc=new Scanner(System.in);
		if (players==1)
			System.out.println("Welcome to Tetris!\nQ to exit");
		else
			System.out.println("Welcome to Tetris for two!\nQ to exit");
		userSelection=sc.nextLine();
		
	}
	
	public int getPlayers() {
		return this.players;
	}
	
	public void setPlayers(byte players) {
		this.players=players;
	}
}
