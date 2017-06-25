package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.polito.tdp.seriea.model.BigMatch;
import it.polito.tdp.seriea.model.Match;
import it.polito.tdp.seriea.model.MatchIdMap;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;
import it.polito.tdp.seriea.model.TeamIdMap;

public class SerieADAO {
	
	public ArrayList<Season> listSeasons() {
		String sql = "SELECT season, description FROM seasons order by season" ;
		
		ArrayList<Season> result = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				result.add( new Season(res.getInt("season"), res.getString("description"))) ;
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public ArrayList<Team> listTeams(TeamIdMap mappaTeam) {
		String sql = "SELECT team FROM teams" ;
		
		ArrayList<Team> result = new ArrayList<Team>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Team t= mappaTeam.get(res.getString("team"));
				
				if(t==null){
					t=new Team(res.getString("team"));
					mappaTeam.put(t) ;
				}
				
				result.add(t) ;
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public ArrayList<Team> getAllTeamsInThisSeason(Season s, TeamIdMap mappaTeam) {
		String sql =  "select distinct HomeTeam "+
				"from matches "+
				"where season=?" ;
		
		ArrayList<Team> result = new ArrayList<Team>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, s.getSeason());
			
			ResultSet res = st.executeQuery() ;
			
			
			while(res.next()) {
				Team t= mappaTeam.get(res.getString("HomeTeam"));
				
				if(t==null){
					t=new Team(res.getString("HomeTeam"));
					mappaTeam.put(t) ;
				}
				
				result.add(t);
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public ArrayList<Match> getAllMacthInThisSeason(Season s, TeamIdMap mappaTeam) {
		String sql = "select matches.match_id,matches.Season,matches.`Div`,matches.Date,matches.HomeTeam,matches.AwayTeam,matches.FTHG,matches.FTAG,matches.FTR " + 
				"from matches " + 
				"where matches.Season=? ";
		
		ArrayList<Match> result = new ArrayList<Match>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, s.getSeason());
			
			ResultSet res = st.executeQuery() ;
			
			
			while(res.next()) {
				Team casa= mappaTeam.get(res.getString("matches.HomeTeam"));
				Team ospite= mappaTeam.get(res.getString("matches.AwayTeam"));
				
				if(casa==null||ospite==null){
					throw new IllegalArgumentException("ERRORE! Il team non è presente nella mappa!");
				}
				
				Match m=new Match(
						res.getInt("matches.match_id"),
						s,
						res.getString("matches.Div"),
						res.getDate("matches.Date").toLocalDate(),
						casa,
						ospite,
						res.getInt("matches.FTHG"),
						res.getInt("matches.FTAG"),
						res.getString("matches.FTR"));
				
				result.add(m);
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	public ArrayList<BigMatch> getAllBigMacthInThisSeason(Season s, TeamIdMap mappaTeam) {
		String sql = "select matches.match_id,matches.Season,matches.`Div`,matches.Date,matches.HomeTeam,matches.AwayTeam,matches.FTHG,matches.FTAG,matches.FTR,matches.HTHG,matches.HTAG,matches.HTR,matches.HS,matches.`AS`,matches.HST,matches.AST,matches.HF,matches.AF,matches.HC,matches.AC,matches.HY,matches.AY,matches.HR,matches.AR " + 
				"from matches " + 
				"where matches.Season=? ";
		
		ArrayList<BigMatch> result = new ArrayList<BigMatch>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, s.getSeason());
			
			ResultSet res = st.executeQuery() ;
			
			
			while(res.next()) {
				Team casa= mappaTeam.get(res.getString("matches.HomeTeam"));
				Team ospite= mappaTeam.get(res.getString("matches.AwayTeam"));
				
				if(casa==null||ospite==null){
					throw new IllegalArgumentException("ERRORE! Il team non è presente nella mappa!");
				}
				
				BigMatch m=new BigMatch(
						res.getInt("matches.match_id"),
						s,
						res.getString("matches.Div"),
						res.getDate("matches.Date").toLocalDate(),
						casa,
						ospite,
						res.getInt("matches.FTHG"),
						res.getInt("matches.FTAG"),
						res.getString("matches.FTR"),
						res.getInt("matches.HTHG"),
						res.getInt("matches.HTAG"),
						res.getString("matches.HTR"),
						res.getInt("matches.HS"),
						res.getInt("matches.AS"),
						res.getInt("matches.HST"),
						res.getInt("matches.AST"),
						res.getInt("matches.HF"),
						res.getInt("matches.AF"),
						res.getInt("matches.HC"),
						res.getInt("matches.AC"),
						res.getInt("matches.HY"),
						res.getInt("matches.AY"),
						res.getInt("matches.HR"),
						res.getInt("matches.AR"));
				
				result.add(m);
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public ArrayList<Match> getAllMacthInThisPeriod(LocalDate start,LocalDate end , TeamIdMap mappaTeam) {
		String sql = "select matches.match_id,matches.Season,matches.`Div`,matches.Date,matches.HomeTeam,matches.AwayTeam,matches.FTHG,matches.FTAG,matches.FTR " + 
				"from matches  " + 
				"where matches.Date>? and matches.Date<? ";
		
		ArrayList<Match> result = new ArrayList<Match>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setDate(1, Date.valueOf(start));
			
			st.setDate(2, Date.valueOf(end));
			
			ResultSet res = st.executeQuery() ;
			
			
			while(res.next()) {
				Team casa= mappaTeam.get(res.getString("matches.HomeTeam"));
				Team ospite= mappaTeam.get(res.getString("matches.AwayTeam"));
				
				if(casa==null||ospite==null){
					throw new IllegalArgumentException("ERRORE! Il team non è presente nella mappa!");
				}
				
				Match m=new Match(
						res.getInt("matches.match_id"),
						null,
					//	s,  nel caso trasformi season in match in un intero
						res.getString("matches.Div"),
						res.getDate("matches.Date").toLocalDate(),
						casa,
						ospite,
						res.getInt("matches.FTHG"),
						res.getInt("matches.FTAG"),
						res.getString("matches.FTR"));
				
				result.add(m);
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	
	
	public ArrayList<Match> getAllMacthTraDueTeam(Team t1 , Team t2, TeamIdMap mappaTeam) {
		String sql = "select matches.match_id,matches.Season,matches.`Div`,matches.Date,matches.HomeTeam,matches.AwayTeam,matches.FTHG,matches.FTAG,matches.FTR " + 
				"from matches  " + 
				"where (matches.HomeTeam=? and matches.AwayTeam=?) or (matches.AwayTeam=? and matches.HomeTeam=?)";
		
		ArrayList<Match> result = new ArrayList<Match>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setString(1, t1.getTeam());
			
			st.setString(2,t2.getTeam() );
			
			st.setString(3, t1.getTeam());
			
			st.setString(4,t2.getTeam() );
			
			ResultSet res = st.executeQuery() ;
			
			
			while(res.next()) {
				Team casa= mappaTeam.get(res.getString("matches.HomeTeam"));
				Team ospite= mappaTeam.get(res.getString("matches.AwayTeam"));
				
				if(casa==null||ospite==null){
					throw new IllegalArgumentException("ERRORE! Il team non è presente nella mappa!");
				}
				
				Match m=new Match(
						res.getInt("matches.match_id"),
						null,
					//	s,  nel caso trasformi season in match in un intero
						res.getString("matches.Div"),
						res.getDate("matches.Date").toLocalDate(),
						casa,
						ospite,
						res.getInt("matches.FTHG"),
						res.getInt("matches.FTAG"),
						res.getString("matches.FTR"));
				
				result.add(m);
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public ArrayList<Match> getAllMacthTraDueTeam(Team t1 , Team t2, TeamIdMap mappaTeam,MatchIdMap mappaMatch) {
		String sql = "select matches.match_id,matches.Season,matches.`Div`,matches.Date,matches.HomeTeam,matches.AwayTeam,matches.FTHG,matches.FTAG,matches.FTR " + 
				"from matches  " + 
				"where (matches.HomeTeam=? and matches.AwayTeam=?) or (matches.AwayTeam=? and matches.HomeTeam=?)";
		
		ArrayList<Match> result = new ArrayList<Match>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setString(1, t1.getTeam());
			
			st.setString(2,t2.getTeam() );
			
			st.setString(3, t1.getTeam());
			
			st.setString(4,t2.getTeam() );
			
			ResultSet res = st.executeQuery() ;
			
			
			while(res.next()) {
				Match m=mappaMatch.get(res.getInt("matches.match_id"));
				if(m==null){
							Team casa= mappaTeam.get(res.getString("matches.HomeTeam"));
							Team ospite= mappaTeam.get(res.getString("matches.AwayTeam"));
							
							if(casa==null||ospite==null){
								throw new IllegalArgumentException("ERRORE! Il team non è presente nella mappa!");
							}
							
							m=new Match(
									res.getInt("matches.match_id"),
									null,
								//	s,  nel caso trasformi season in match in un intero
									res.getString("matches.Div"),
									res.getDate("matches.Date").toLocalDate(),
									casa,
									ospite,
									res.getInt("matches.FTHG"),
									res.getInt("matches.FTAG"),
									res.getString("matches.FTR"));
							mappaMatch.put(m);
						}
				result.add(m);
			}
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	public ArrayList<Match> getAllMacthDiUnTeam(Team t1 ,TeamIdMap mappaTeam) {
		String sql = "select matches.match_id,matches.Season,matches.`Div`,matches.Date,matches.HomeTeam,matches.AwayTeam,matches.FTHG,matches.FTAG,matches.FTR   " + 
				"from matches   " + 
				"where matches.HomeTeam=? or matches.AwayTeam=?";
		
		ArrayList<Match> result = new ArrayList<Match>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setString(1, t1.getTeam());
			
			st.setString(2, t1.getTeam());
			
			ResultSet res = st.executeQuery() ;
			
			
			while(res.next()) {
				Team casa= mappaTeam.get(res.getString("matches.HomeTeam"));
				Team ospite= mappaTeam.get(res.getString("matches.AwayTeam"));
				
				if(casa==null||ospite==null){
					throw new IllegalArgumentException("ERRORE! Il team non è presente nella mappa!");
				}
				
				Match m=new Match(
						res.getInt("matches.match_id"),
						null,
					//	s,  nel caso trasformi season in match in un intero
						res.getString("matches.Div"),
						res.getDate("matches.Date").toLocalDate(),
						casa,
						ospite,
						res.getInt("matches.FTHG"),
						res.getInt("matches.FTAG"),
						res.getString("matches.FTR"));
				
				result.add(m);
			}
			
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public ArrayList<Match> getAllMacthDiUnTeam(Team t1 , TeamIdMap mappaTeam,MatchIdMap mappaMatch) {
		String sql = "select matches.match_id,matches.Season,matches.`Div`,matches.Date,matches.HomeTeam,matches.AwayTeam,matches.FTHG,matches.FTAG,matches.FTR  " + 
				"from matches  " + 
				"where matches.HomeTeam=? or matches.AwayTeam=?";
		
		ArrayList<Match> result = new ArrayList<Match>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setString(1, t1.getTeam());
			
			st.setString(2, t1.getTeam());
			
			ResultSet res = st.executeQuery() ;
			
			
			while(res.next()) {
				Match m=mappaMatch.get(res.getInt("matches.match_id"));
				if(m==null){
							Team casa= mappaTeam.get(res.getString("matches.HomeTeam"));
							Team ospite= mappaTeam.get(res.getString("matches.AwayTeam"));
							
							if(casa==null||ospite==null){
								throw new IllegalArgumentException("ERRORE! Il team non è presente nella mappa!");
							}
							
							m=new Match(
									res.getInt("matches.match_id"),
									null,
								//	s,  nel caso trasformi season in match in un intero
									res.getString("matches.Div"),
									res.getDate("matches.Date").toLocalDate(),
									casa,
									ospite,
									res.getInt("matches.FTHG"),
									res.getInt("matches.FTAG"),
									res.getString("matches.FTR"));
							
							mappaMatch.put(m);
						}
				result.add(m);
			}
			conn.close();
			return result ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	//TUTTI I MACH DOVE C'E' STATO IL MAGGIOR NUMERO DI GOL FUOI CASA
	//select matches.match_id,matches.Season,matches.`Div`,matches.Date,matches.HomeTeam,matches.AwayTeam,matches.FTHG,matches.FTAG,matches.FTR  
	//from matches 
	//where  matches.FTAG=(select max(matches.FTAG) from matches)
}
