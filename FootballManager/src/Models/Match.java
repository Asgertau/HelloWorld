package Models;

import java.time.LocalDate;
import java.util.Date;

public class Match {

	private LocalDate startTime;
	private Team homeTeam;
	private Team awayTeam;
	private int homeTeamgoals;
	private int awayTeamgoals;
	
	public Match(LocalDate startTime, Team homeTeam, Team awayTeam) {
		setStartTime(startTime);
		setHomeTeam(homeTeam);
		setAwayTeam(awayTeam);
	}

	public LocalDate getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getHomeTeamgoals() {
		return homeTeamgoals;
	}

	public void setHomeTeamgoals(int homeTeamgoals) {
		this.homeTeamgoals = homeTeamgoals;
	}

	public int getAwayTeamgoals() {
		return awayTeamgoals;
	}

	public void setAwayTeamgoals(int awayTeamgoals) {
		this.awayTeamgoals = awayTeamgoals;
	}
	
	public String toString() {
		String vsString = homeTeam.getName() + " vs " + awayTeam.getName();
		
		String seperator ="\t";
		
		if(vsString.length() < 32) {
			seperator = "\t\t";
		}
		return homeTeam.getName() + " vs " + awayTeam.getName() +seperator +Integer.toString(homeTeamgoals) + "  -  " + Integer.toString(awayTeamgoals);
	}

}
