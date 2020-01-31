package Models;

import java.util.ArrayList;

public class FootieLeague {

	private String name;
	private ArrayList<Team> teams;
	private ArrayList<Match> matches;
	
	
	public FootieLeague() {
	}
	
	public FootieLeague(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}

	public void addTeam(Team newTeam) {
		teams.add(newTeam);
	}
	
	public void removeTeam(Team removeTeam) {
		teams.remove(removeTeam);
	}
	
	public void addMatch(Match newMatch) {
		matches.add(newMatch);
	}
	
	public void removeMatch(Match matchToRemove) {
		matches.remove(matchToRemove);
	}
}
