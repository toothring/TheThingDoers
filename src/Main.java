import java.util.Scanner;

public class Main{
	private Tetsaw ts;
	private Tetris tr;
	
	public Main() {
		Scanner sc=new Scanner(System.in);
		char menuSelect;
		int players=1;
		
		do {
			System.out.print("Welcome to Tetsaw\n"
					+ "Please make a selection\n\n"
					+ "A - One Player\n"
					+ "B - Two Players\n"
					+ "C - Settings\n"
					+ "Q - Quit Game\n\n"
					+ "Enter your choice: ");
			menuSelect=sc.nextLine().toUpperCase().strip().charAt(0);
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
					menuSelect=sc.nextLine().toUpperCase().strip().charAt(0);
					if (menuSelect== 'A') {
						do {
							System.out.print("Select a mode:\n\n"
									+ "A - Easy\n"
									+ "B - Normal\n"
									+ "C - Hard\n"
									+ "Q - Go back\n\n"
									+ "Enter your choice: ");
							menuSelect=sc.nextLine().toUpperCase().strip().charAt(0);
							if (menuSelect == 'A')
								this.ts=new Tetsaw("easy",players);
							else if(menuSelect =='B')
								this.ts=new Tetsaw("normal",players);
							else if(menuSelect == 'C')
								this.ts=new Tetsaw("hard",players);
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
							+ "A - Graphics\n"
							+ "B - Sound\n"
							+ "C - Accessability\n"
							+ "Q - Quit\n\n"
							+ "Enter your choice: ");
					menuSelect=sc.nextLine().toUpperCase().strip().charAt(0);
					if(menuSelect == 'A')
						graphics();
					else if(menuSelect == 'B')
						sound();
					else if(menuSelect == 'C')
						accessibility();
					else {
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
	
	public char graphics() {
		System.out.println("Let's change those graphics settings!\nQ to exit");
		return getString().toUpperCase().strip().charAt(0);
	}
	
	public char sound() {
		System.out.println("Let's change those sound settings!\nQ to exit");
		return getString().toUpperCase().strip().charAt(0);
	}
	
	public char accessibility() {
		System.out.println("We love that you're colour blind!\nQ to exit");
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