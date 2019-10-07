import java.util.Scanner;

public class Main{
	private Tetsaw ts;
	private Tetris tr;
	
	public Main() {
		char menuSelect;
		int players=1;
		
		do {
			System.out.print("Welcome to Tetsaw\n"
					+ "Please make a selection\n\n"
					+ "A - One Player (MVF)\n"
					+ "B - Two Players (EF)\n"
					+ "C - Settings\n"
					+ "Q - Quit Game\n\n"
					+ "Enter your choice: ");
			menuSelect=getString().toUpperCase().strip().charAt(0);
			if(menuSelect=='B') {
				players=2;
				menuSelect='A';
			}
			if (menuSelect == 'A') {
				do {
					System.out.print("Select a game:\n\n"
							+ "A - Tetsaw\n"
							+ "B - Tetris\n"
							+ "Q - Go back\n\n"
							+ "Enter your choice: ");
					menuSelect=getString().toUpperCase().strip().charAt(0);
					if (menuSelect== 'A') {
						do {
							System.out.print("Select a mode:\n\n"
									+ "A - Easy\n"
									+ "B - Normal\n"
									+ "C - Hard\n"
									+ "D - Custom\n"
									+ "Q - Go back\n\n"
									+ "Enter your choice: ");
							menuSelect=getString().toUpperCase().strip().charAt(0);
							if (menuSelect == 'A')
								this.ts=new Tetsaw("easy",players);
							else if(menuSelect =='B')
								this.ts=new Tetsaw("normal",players);
							else if(menuSelect == 'C')
								this.ts=new Tetsaw("hard",players);
							else if(menuSelect == 'D')
								this.ts=new Tetsaw("custom",players);
							else
								if(menuSelect != 'Q')
									System.out.println("Invalid input. Please try again.\n\nEnter your choice: ");
						} while (menuSelect != 'Q');
						menuSelect='\u0000';
					} else if(menuSelect=='B') {
						this.tr = new Tetris(players);
					} else
						if(menuSelect != 'Q')
							System.out.println("Invalid input. Please try again.\n\nEnter your choice: ");
				} while (menuSelect != 'Q');
				menuSelect='\u0000';
			} else if(menuSelect == 'C') {
				do {
					System.out.print("Select what you want to change:\n\n"
							+ "A - Graphics (MVF)\n"
							+ "B - Sound (MVF)\n"
							+ "C - Accessability (EF)\n"
							+ "Q - Quit\n\n"
							+ "Enter your choice: ");
					menuSelect=getString().toUpperCase().strip().charAt(0);
					if(menuSelect == 'A') {
						
						do {
							System.out.print("Select a mode:\n\n"
									+ "A - Resolution (MVF)\n"
									+ "B - FPS (MVF)\n"
									+ "C - Full-Screen Mode (MVF)\n"
									+ "Q - Go back\n\n"
									+ "Enter your choice: ");
							menuSelect=getString().toUpperCase().strip().charAt(0);
							if (menuSelect == 'A')
								graphics("Resolution");
							else if(menuSelect =='B')
								graphics("FPS");
							else if(menuSelect == 'C')
								graphics("Full-Screen Mode");
							else if(menuSelect == 'D')
								this.ts=new Tetsaw("custom",players);
							else
								if(menuSelect != 'Q')
									System.out.println("Invalid input. Please try again.\n\nEnter your choice: ");
						} while (menuSelect != 'Q');
						menuSelect='\u0000';
					} else if(menuSelect == 'B')  {
						do {
							System.out.print("Select a mode:\n\n"
									+ "A - Background Music Volume (MVF)\n"
									+ "B - Sound Effects Volume (MVF)\n"
									+ "Q - Go back\n\n"
									+ "Enter your choice: ");
							menuSelect=getString().toUpperCase().strip().charAt(0);
							if (menuSelect == 'A')
								sound("Background Music");
							else if(menuSelect =='B')
								sound("Sound Effects");
							else
								if(menuSelect != 'Q')
									System.out.println("Invalid input. Please try again.\n\nEnter your choice: ");
						} while (menuSelect != 'Q');
						menuSelect='\u0000';
					} else if(menuSelect == 'C') {
						do {
							System.out.print("Select a mode:\n\n"
									+ "A - Game Controls (EF)\n"
									+ "B - Colour-blind Mode (EF)\n"
									+ "Q - Go back\n\n"
									+ "Enter your choice: ");
							menuSelect=getString().toUpperCase().strip().charAt(0);
							if (menuSelect == 'A')
								accessibility("Game Controls");
							else if(menuSelect =='B')
								accessibility("Colour-blind Mode");
							else
								if(menuSelect != 'Q')
									System.out.println("Invalid input. Please try again.\n\nEnter your choice: ");
						} while (menuSelect != 'Q');
						menuSelect='\u0000';
					} else {
						if(menuSelect != 'Q')
							System.out.println("Invalid input. Please try again.\n\nEnter your choice: ");
					}
				} while (menuSelect != 'Q');
				menuSelect='\u0000';
			} else
				if(menuSelect != 'Q')
					System.out.println("Invalid input. Please try again.\n\nEnter your choice: ");
			
			if(menuSelect == 'Q')
				System.out.print("Thanks for playing. Goodbye!");
		} while (menuSelect != 'Q');
	}
	
	public char graphics(String setting) {
		System.out.println(setting + " were WAY off! I'm glad you're changing them.\nQ to exit");
		return getString().toUpperCase().strip().charAt(0);
	}
	
	public char sound(String setting) {
		System.out.println("Don't change the " + setting + " too much.\nBrendan did that and how he can't hear high notes!\nQ to exit");
		return getString().toUpperCase().strip().charAt(0);
	}
	
	public char accessibility(String setting) {
		System.out.println(setting + " engaged!\nQ to disengage!");
		return getString().toUpperCase().strip().charAt(0);
	}
	
	public String getString() {
		Scanner sc=new Scanner(System.in);
		return sc.nextLine();
	}
	
	public static void main(String[] args) {
		Main m=new Main();
	}
}
