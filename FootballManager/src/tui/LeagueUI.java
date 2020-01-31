package tui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Controllers.*;

import Models.LeagueTableTuple;
import Models.Match;

public class LeagueUI {

	private LeagueController leagueController;
	private TeamController teamController;

	private Scanner scanner;

	public LeagueUI(TeamController teamController) {
		leagueController = new LeagueController();
		this.teamController = teamController;
		scanner = new Scanner(System.in);

	}

	public void ShowMenu() {
		boolean isPlaying = true;

		while (isPlaying) {
			System.out.println("********* Footie League **********");
			System.out.println("1) Play Season");
			System.out.println("2) Quit to main menu");

			String input = scanner.nextLine();
			switch (input) {
			case "1":
				playSeason();
				printStandings();
				printScore();
				isPlaying = false;
				System.out.println();
				break;

			case "2":
				isPlaying = false;
				break;

			default:
				System.out.println("Invalid input.. Try again");
			}

		}

	}

	private void printScore() {
		ArrayList<LeagueTableTuple> standings = leagueController.GetStandings();
		int indexOfMyTeam = 0;
		for (int i = 0; i < standings.size(); i++) {
			LeagueTableTuple tuple = standings.get(i);

			if (tuple.getTeam() == teamController.getSelectedTeam()) {
				indexOfMyTeam = i + 1;
			}
		}
		System.out.println("");
		System.out.println("******* THE SEASON HAS CONCLUDED! ********");
		System.out.println("******* YOU FINISHED AS NUMBER " + indexOfMyTeam + " ******* ");
		
		printFunnyByeByeMessage(indexOfMyTeam);
	}

	private void printFunnyByeByeMessage(int indexOfMyTeam) {
		
		if(indexOfMyTeam == 1) {
			System.out.println("******* IT SEEMS LIKE YOU PICKED A BLOODY WINNER *******");
		}
		
		else if(indexOfMyTeam == 2) {
			System.out.println("******* CLOSE, BUT NO CIGAR ********"); 
		}
		
		else if(indexOfMyTeam == 3) {
			System.out.println("******* BRONZE!!! ONLY YOUR MOM WILL CARE *******");
		}
		
		else if(indexOfMyTeam <= 8) {
			System.out.println("******* I GUESS THAT IS ALRIGHT *******");
		}
		
		else if(indexOfMyTeam > 8) {
			System.out.println("******* WHY ON EARTH WOULD YOU PICK THE " + teamController.getSelectedTeam().getName()+ "??? *******");
		}
	}

	private void playSeason() {
		HashMap<Integer, ArrayList<Match>> results = leagueController.playSeason();

		for (int i = 0; i < results.size(); i++) {
			System.out.println("Round " + (i + 1));
			var matches = results.get(i);

			for (Match match : matches) {
				System.out.println(match);
			}
			System.out.println();
		}
	}

	private void printStandings() {
		System.out.println("  \tTeam name \t\t\t     GoalsFor\t   GoalsAgainst\t   GoalScore         Points");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------");
		ArrayList<LeagueTableTuple> standings = leagueController.GetStandings();
		for (int i = 0; i < standings.size(); i++) {
			LeagueTableTuple leagueTableTuple = standings.get(i);
			System.out.println("#" + (i + 1) + " \t" + leagueTableTuple);

		}
		System.out.println(
				"---------------------------------------------------------------------------------------------------------");

	}

}
