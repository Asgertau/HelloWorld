package Tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Controllers.LeagueController;
import Models.*;

class UnitTets {

	@Test
	void testSortLeagueTableByPoints() {
		
		LeagueController leagueController = new LeagueController();
		
		Team t1 = new Team("team1");
		Team t2 = new Team("team2");
		
		LeagueTableTuple tuple1 = new LeagueTableTuple();
		LeagueTableTuple tuple2 = new LeagueTableTuple();
		
		tuple1.setTeam(t1);
		tuple1.setPoints(9);
		
		tuple2.setTeam(t2);
		tuple2.setPoints(10);
		
		ArrayList<LeagueTableTuple> tuples = new ArrayList<LeagueTableTuple>();
		
		tuples.add(tuple1);
		tuples.add(tuple2);
		
		ArrayList<LeagueTableTuple> result = leagueController.SortStandingsAfterPoints(tuples);
		
		assertNotNull(result);
		assertEquals(0, result.indexOf(tuple2));
		
	}

}
