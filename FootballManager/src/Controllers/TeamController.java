package Controllers;

import java.util.ArrayList;

import Models.*;
import Repository.Repository;

public class TeamController {

	private Repository repository;
	private Team selectedTeam;
	private boolean isLoading;
	
	public TeamController() {
		repository = Repository.getIsntance();
	}
	
	public ArrayList<Team> getTeams(){
		return repository.getTeams();
	}

	public Team getSelectedTeam() {
		return selectedTeam;
	}

	public void setSelectedTeam(Team selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	public boolean isLoading() {
		return isLoading;
	}

	public void setLoading(boolean isLoading) {
		this.isLoading = isLoading;
	}

}
