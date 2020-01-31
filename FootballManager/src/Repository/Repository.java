package Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import Models.*;

public class Repository {

	private static Repository Instance;
	
	private ArrayList<FootiePlayer> footiePlayers;
	private ArrayList<Team> teams;
	private ArrayList<FootieLeague> footieLeagues;
	private ArrayList<Match> matches;
	private HashMap<Integer, ArrayList<Match>> matchRounds;
	
	
	private WebClient webClient;
	
	private Repository() {
		footiePlayers = new ArrayList<FootiePlayer>();
		teams = new ArrayList<Team>();
		footieLeagues = new ArrayList<FootieLeague>();
		matches = new ArrayList<Match>();
		matchRounds = new HashMap<Integer, ArrayList<Match>>();
		webClient = new WebClient();
		
		
	}
	
	public static Repository getIsntance() {
		if(Instance == null) {
			Instance = new Repository();
		}
		return Instance;
		}
	
	
	public void Setup() {
		FootieLeague newLeague = new FootieLeague("Footie League");
		footieLeagues.add(newLeague);
		
//		FootieLeague otherLeague = new FootieLeague("Other Footie League");
//		footieLeagues.add(otherLeague);
		
		ArrayList<Team> teamsList1 = GenerateTeams(new String[] {"Weekend Warriors", "Dirty Dusters", "Pinhead Phanters", "Faulty Fuses", "Magnetic Marbles", "Pale Pretenders", "Green Groomers", "Jumpy Jacks", "Licensed Loopers", "Blind Ballers"});
		teams.addAll(teamsList1);		
		newLeague.setTeams(teamsList1);
		
//		ArrayList<Team> teamsList2 = GenerateTeams(new String[] {"name1", "name2", "name3", "name4"});
//		teams.addAll(teamsList2);		
//		otherLeague.setTeams(teamsList2);
		
		for (Team team : teams) {
			try {
				Thread.sleep(250);
				ArrayList<FootiePlayer> players = GetSquad();
				team.setPlayers(players);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		matchRounds = GenerateRounds();
		
	}
	
	public HashMap<Integer, ArrayList<Match>> GenerateRounds() {

        // Generate the fixtures using the cyclic algorithm.
        int totalRounds = teams.size() - 1;
        int matchesPerRound = teams.size() / 2;
//        String[][] rounds = new String[totalRounds][matchesPerRound];
        HashMap<Integer, ArrayList<Match>> rounds = new HashMap<Integer,ArrayList<Match>>();
        
        for (int round = 0; round < totalRounds; round++) {
        	
        	LocalDate startTime = LocalDate.now().plusDays(round);
            for (int match = 0; match < matchesPerRound; match++) {
                Team home = teams.get((round + match) % (teams.size() - 1));
                
                Team away = teams.get((teams.size() - 1 - match + round) % (teams.size() - 1));
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams.get(teams.size() - 1);
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
//                rounds[round][match] = (home + 1) + " v " + (away + 1);
                if(rounds.containsKey(round) == false) {
                	Match matchToAdd = new Match(startTime, home, away);
                	ArrayList<Match> matchList = new ArrayList<Match>();
                	matchList.add(matchToAdd);
                	matches.add(matchToAdd);
                	rounds.put(round, matchList);
                }
                else {
                	ArrayList<Match> matchList = rounds.get(round);
                	Match matchToAdd= new Match(startTime, home, away);
                	matches.add(matchToAdd);
                	matchList.add(matchToAdd);
                }
            }
        }
        
        // Interleave so that home and away games are fairly evenly dispersed.
//        String[][] interleaved = new String[totalRounds][matchesPerRound];
        HashMap<Integer, ArrayList<Match>> interleaved = new HashMap<Integer,ArrayList<Match>>();

        int evn = 0;
        int odd = (teams.size() / 2);
        for (int i = 0; i < rounds.keySet().size(); i++) {
            if (i % 2 == 0) {
//                interleaved[i] = rounds[evn++];
                interleaved.put(i, rounds.get(evn++));

                
            } else {
//                interleaved[i] = rounds[odd++];
                interleaved.put(i, rounds.get(odd++));
            }
        }
        
        rounds = interleaved;

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int round = 0; round < rounds.size(); round++) {
            if (round % 2 == 1) {
//                rounds[round][0] = flip(rounds[round][0]);
            	ArrayList<Match> matches = rounds.get(round);
            }
        }
        
        // Display the fixtures        
//        for (int i = 0; i < rounds.size(); i++) {
//            System.out.println("Round " + (i +1));
//            var matches = rounds.get(i);
//            for (Match match : matches) {
//				System.out.println(match);
//			}
//        }
                    
        return rounds;

    }

//    private static String flip(String match) {
////        String[] components = match.split(" v ");
////        return components[1] + " v " + components[0];
//	}
	
//	private ArrayList<Match> GenerateMatchesRounds(){
//		
//		ArrayList<Match> result = new ArrayList<Match>();
//		
//		for (int i = 0; i < teams.size() -1; i++) {
//			Team currentTeam = teams.get(i);
//			
//			ArrayList<Match> matchesForRound = new ArrayList<Match>();
//			for (int j = i+1; j < teams.size(); j++) {
//				Team nextTeam = teams.get(j);
//				Match newMatch = new Match(LocalDate.now(),currentTeam, nextTeam);
//				matchesForRound.add(newMatch);
//				result.add(newMatch);
//				matchRounds.put(j, matchesForRound);
//			}
//
//		}
//		
//		return result;
//	}
	
	public ArrayList<FootiePlayer> GetSquad() {
		 ArrayList<FootiePlayer> players = webClient.getRandomPersonsFromAPI(11);
		 
		 for (int i = 0; i < players.size(); i++) {
			 
			 FootiePlayer player = (FootiePlayer)players.get(i);
		
			if(i == 0) {
				player.setPosition(Position.GoalKeeper);
			}
			else if(i < 5) {
				player.setPosition(Position.Defender);
			}
			else if(i < 9) {
				player.setPosition(Position.Midfielder);
			}
			else {
				player.setPosition(Position.Striker);
			}
			
		}
		 
		 return players;
	}
	
	private ArrayList<Team> GenerateTeams(String[] teamNames) {
		
		ArrayList<Team> result = new ArrayList<Team>();
		
		
		for (String name : teamNames) {
			Team newTeam = new Team(name);
			result.add(newTeam);
		}
		
		return result;
	}
	

	public ArrayList<FootiePlayer> getFootiePlayers() {
		return footiePlayers;
	}

	public void setFootiePlayers(ArrayList<FootiePlayer> footiePlayers) {
		this.footiePlayers = footiePlayers;
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public ArrayList<FootieLeague> getFootieLeagues() {
		return footieLeagues;
	}

	public void setFootieLeagues(ArrayList<FootieLeague> footieLeagues) {
		this.footieLeagues = footieLeagues;
	}

	public ArrayList<Match> getMatches() {
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) {
		this.matches = matches;
	}
	
	public HashMap<Integer, ArrayList<Match>> getMatchRounds(){
		return this.matchRounds;
	}
	
	public void setMatchRounds(HashMap<Integer, ArrayList<Match>> matchRounds) {
		this.matchRounds = matchRounds;
	}
	
	
	

}
