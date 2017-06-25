package it.polito.tdp.seriea.db;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.seriea.model.BigMatch;
import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;
import it.polito.tdp.seriea.model.TeamIdMap;

public class TestSerieADAO {

	public static void main(String[] args) {
		SerieADAO dao = new SerieADAO() ;
		
		List<Season> seasons = dao.listSeasons() ;
		System.out.println(seasons);
		
		TeamIdMap mappaTeam =new TeamIdMap();
		
		List<Team> teams = dao.listTeams(mappaTeam) ;
		System.out.println(teams);

		
		
		dao.getAllTeamsInThisSeason(seasons.get(0), mappaTeam);
		
		List<BigMatch> bigMatches = dao.getAllBigMacthInThisSeason(seasons.get(0), mappaTeam);
		System.out.println(bigMatches);
		
		List<Match> listaa=dao.getAllMacthInThisPeriod(LocalDate.of(2015, 8, 29),LocalDate.of(2015, 12, 29), mappaTeam);
		System.out.println(listaa);
		
		List<Match> listaaa=dao.getAllMacthTraDueTeam(new Team("Inter"),new Team("Lecce"), mappaTeam);
		System.out.println(listaaa);
		
	}

}
