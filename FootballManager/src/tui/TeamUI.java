package tui;

import java.util.ArrayList;
import java.util.Scanner;

import Controllers.TeamController;
import Models.*;

public class TeamUI {

	private TeamController teamController;
	private Scanner scanner;
	private boolean isLoading;
	public TeamUI() {
		setLoading(true);
		teamController = new TeamController();
		scanner = new Scanner(System.in);
		setLoading(false);

	}

	public void SelecTeam() {
		ArrayList<Team> teams = teamController.getTeams();
		boolean isChoosing = true;

		while (isChoosing) {

			System.out.println("********* Please pick your dreamteam **********");
			for (int i = 0; i < teams.size(); i++) {

				Team currentTeam = teams.get(i);
				System.out.println(Integer.toString(i + 1) + ") " + currentTeam.getName());
			}


			String answer = scanner.nextLine();
			
			boolean isValidNumber = tryParseInt(answer);

			if (isValidNumber && Integer.parseInt(answer) > 0 && Integer.parseInt(answer) <= teams.size()) {

				Team selectedTeam = teams.get(Integer.parseInt(answer) - 1);

				System.out.println("******* Viewing " + selectedTeam.getName() + " *******");
				for (FootiePlayer player : selectedTeam.getPlayers()) {
					System.out.println(player);
				}

				System.out.println();
				boolean hasAnswered = false;
				
				while(hasAnswered == false) {
					System.out.println("Confirm selction? (y/n)");
					
					String confirmation = scanner.nextLine();
					
					switch (confirmation) {
					case "y":
						isChoosing = false;
						hasAnswered = true;
						teamController.setSelectedTeam(selectedTeam);
						new LeagueUI(teamController).ShowMenu();
						break;
					case "n":
						hasAnswered = true;
						break;
						
					default:
						System.out.println("Invalid input. Try again (y/n)");
						
					}
					
				}
			}
			else {
				System.out.println("Invalid input. Try again");
			}
		}

	}

	private boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public boolean isLoading() {
		return this.isLoading;
	}

	public void setLoading(boolean isLoading) {
		this.isLoading = isLoading;
	}

}
