package tui;

import java.util.Scanner;

import Models.FootiePlayer;
import Repository.Repository;

public class MainMenu {

	public static void main(String[] args) {

		new MainMenu().startGame();

	}

	private Scanner scanner;

	public MainMenu() {
		scanner = new Scanner(System.in);

	}

	public void startGame() {
		
		//Load data in repository
		Repository.getIsntance().Setup();
		
		boolean running = true;

		while (running) {

			System.out.println("******* Footie Simimulator 2020 ********");
			System.out.println();
			System.out.println("1) New Game");
			System.out.println("2) Quit");
			System.out.println();

			String answer = scanner.nextLine();

			switch (answer) {
			case "1":
				System.out.println();
				System.out.print("Starting new game");
				try {
					for (int i = 0; i <= 14; i++) {
						Thread.sleep(50);
						System.out.print(".");
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();

				new TeamUI().SelecTeam();
				break;
				
			case "2":
				System.out.println("Bye bye");
				running = false;
				break;
			default:
				System.out.println("Invalid input, please try again");

			}

		}

	}

}
