package Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Models.LeagueTableTuple;
import Models.*;
import Repository.Repository;

public class LeagueController {

	private Repository repository;

	public LeagueController() {
		repository = Repository.getIsntance();
	}

	public HashMap<Integer, ArrayList<Match>> playSeason() {

		HashMap<Integer, ArrayList<Match>> matchRounds = repository.getMatchRounds();

		for (int round : matchRounds.keySet()) {

			for (Match match : matchRounds.get(round)) {
				match.setHomeTeamgoals((int) (Math.round(Math.random() * 10)));
				match.setAwayTeamgoals((int) (Math.round(Math.random() * 10)));
			}
		}

		return matchRounds;
	}
	
	public ArrayList<LeagueTableTuple> GetStandings() {

		ArrayList<LeagueTableTuple> standings = GetUnsortedStandings();
		standings = SortStandingsAfterPoints(standings);
		standings = SortStandingsAfterGoalScore(standings);
		return standings;
	}

	public ArrayList<LeagueTableTuple> SortStandingsAfterPoints(ArrayList<LeagueTableTuple> standings) {

		int n = standings.size();
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (standings.get(j).getPoints() < standings.get(j + 1).getPoints()) {
					// swap arr[j+1] and arr[i]
					LeagueTableTuple temp = standings.remove(j);
					standings.add(j+1,temp);

				}
		
		return standings;

	}
	
	public ArrayList<LeagueTableTuple> SortStandingsAfterGoalScore(ArrayList<LeagueTableTuple> standings) {
		
		int n = standings.size();
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (standings.get(j).getPoints() == standings.get(j + 1).getPoints() && standings.get(j).getGoalsScore() < standings.get(j + 1).getGoalsScore() ) {
					// swap arr[j+1] and arr[i]
					LeagueTableTuple temp = standings.remove(j);
					standings.add(j+1,temp);

				}		
		return standings;
	}
	
	public ArrayList<LeagueTableTuple> GetUnsortedStandings(){
		ArrayList<LeagueTableTuple> result = new ArrayList<LeagueTableTuple>();
		HashMap<Team, LeagueTableTuple> tableTuples = new HashMap<Team, LeagueTableTuple>();

		ArrayList<Match> matches = repository.getMatches();
		ArrayList<Team> teams = repository.getTeams();

		for (Team team : teams) {
			LeagueTableTuple newTuple = new LeagueTableTuple();
			newTuple.setTeam(team);
			tableTuples.put(team, newTuple);
			result.add(newTuple);
		}

		for (Match match : matches) {

			LeagueTableTuple homeTuple = tableTuples.get(match.getHomeTeam());
			LeagueTableTuple awayTuple = tableTuples.get(match.getAwayTeam());

			homeTuple.addGoalsFor(match.getHomeTeamgoals());
			homeTuple.addGoalsAgainst(match.getAwayTeamgoals());

			awayTuple.addGoalsFor(match.getAwayTeamgoals());
			awayTuple.addGoalsAgainst(match.getHomeTeamgoals());

			if (match.getHomeTeamgoals() > match.getAwayTeamgoals()) {
				homeTuple.addPoints(3);
			}

			else if (match.getHomeTeamgoals() < match.getAwayTeamgoals()) {
				awayTuple.addPoints(3);
			}

			else {
				homeTuple.addPoints(1);
				awayTuple.addPoints(1);
			}

		}
		return result;
	}



}
