package Models;

import java.util.ArrayList;

public class Team {

	private String name;
	private ArrayList<FootiePlayer> players;
	
	public Team(String name) {
		setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<FootiePlayer> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<FootiePlayer> players) {
		this.players = players;
	}
	
	public void addPlayer(FootiePlayer newPlayer) {
		players.add(newPlayer);
	}
	
	public void removePlayer(FootiePlayer playerToRemove) {
		players.remove(playerToRemove);
	}

}
