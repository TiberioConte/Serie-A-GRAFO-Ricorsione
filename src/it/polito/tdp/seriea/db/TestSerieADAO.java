package it.polito.tdp.seriea.db;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.seriea.model.BigMatch;
import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.MatchIdMap;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;
import it.polito.tdp.seriea.model.TeamIdMap;

public class TestSerieADAO {

	public static void main(String[] args) {
		SerieADAO dao = new SerieADAO() ;
		
		List<Season> seasons = dao.listSeasons() ;
		System.out.println("a"+seasons);
		
		TeamIdMap mappaTeam =new TeamIdMap();
		
		List<Team> teams = dao.listTeams(mappaTeam) ;
		System.out.println("b"+teams);

		
		
		dao.getAllTeamsInThisSeason(seasons.get(0), mappaTeam);
		
		List<BigMatch> bigMatches = dao.getAllBigMacthInThisSeason(seasons.get(0), mappaTeam);
		System.out.println("c"+bigMatches);
		
		List<Match> listaa=dao.getAllMacthInThisPeriod(LocalDate.of(2015, 8, 29),LocalDate.of(2015, 12, 29), mappaTeam);
		System.out.println("d"+listaa);
		
		List<Match> listaaa=dao.getAllMacthTraDueTeam(new Team("Inter"),new Team("Lecce"), mappaTeam);
		System.out.println("e"+listaaa);
		
		MatchIdMap mappaMatch =new MatchIdMap();
		
		List<Match> listaaaa=dao.getAllMacthTraDueTeam(new Team("Inter"),new Team("Lecce"), mappaTeam,mappaMatch);
		System.out.println("f"+listaaaa);
		
		List<Match> listaaaaa=dao.getAllMacthDiUnTeam(new Team("Inter"), mappaTeam);
		System.out.println("g"+listaaaaa);
		
		List<Match> lista1=dao.getAllMacthDiUnTeam(new Team("Inter"), mappaTeam,mappaMatch);
		System.out.println("h"+lista1);
		
		
	}

}
