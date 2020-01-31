package Models;

public class LeagueTableTuple {

	private Team team;
	private int goalsFor;
	private int goalsAgaint;
	private int points;
	private int goalScore;
	
	public LeagueTableTuple() {
		// TODO Auto-generated constructor stub
	}
	
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public int getGoalsFor() {
		return goalsFor;
	}
	
	public void addGoalsFor(int goals) {
		this.goalsFor += goals;
	}
	
	public void setGoalsFor(int goalsFor) {
		this.goalsFor = goalsFor;
	}
	public int getGoalsAgaint() {
		return goalsAgaint;
	}
	
	public void addGoalsAgainst(int goals) {
		this.goalsAgaint += goals;
	}
	
	public void setGoalsAgainst(int goalsAgaint) {
		this.goalsAgaint = goalsAgaint;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public int getGoalsScore() {
		return goalsFor - goalsAgaint;
	}
	
	public String toString() {
		String seperator = "\t";
		if(team.getName().length() < 16) {
			seperator = "\t\t";
			
		}
//		else if(team.getName().length() < 20) {
//		}
		return team.getName() + seperator + " \t|\t" + goalsFor + " \t|\t" + goalsAgaint + "\t|\t" + Integer.toString(getGoalsScore()) + "\t|\t" + points +" \t|";
	}

}
