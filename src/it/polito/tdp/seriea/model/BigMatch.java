package it.polito.tdp.seriea.model;

import java.time.LocalDate;

public class BigMatch {
	private int id ;
	private Season season ;
	private String div ;
	private LocalDate date ;
	private Team homeTeam ;
	private Team awayTeam ;
	private int fthg ; // full time home goals
	private int ftag ; // full time away goals
	private String ftr ; // full time result (H, A, D)
	private int hthg;
	private int htag;
	private String htr;
	private int hs;
	private int as;
	private int hst;
	private int ast;
	private int hf;
	private int af;
	private int hc;
	private int ac;
	private int hy;
	private int ay;
	private int hr;
	private int ar;
	
	public BigMatch(int id, Season season, String div, LocalDate date, Team homeTeam, Team awayTeam, int fthg, int ftag,
			String ftr, int hthg, int htag, String htr, int hs, int as, int hst, int ast, int hf, int af, int hc,
			int ac, int hy, int ay, int hr, int ar) {
		super();
		this.id = id;
		this.season = season;
		this.div = div;
		this.date = date;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.fthg = fthg;
		this.ftag = ftag;
		this.ftr = ftr;
		this.hthg = hthg;
		this.htag = htag;
		this.htr = htr;
		this.hs = hs;
		this.as = as;
		this.hst = hst;
		this.ast = ast;
		this.hf = hf;
		this.af = af;
		this.hc = hc;
		this.ac = ac;
		this.hy = hy;
		this.ay = ay;
		this.hr = hr;
		this.ar = ar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BigMatch other = (BigMatch) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BigMatch [id=" + id + ", season=" + season + ", div=" + div + ", date=" + date + ", homeTeam="
				+ homeTeam + ", awayTeam=" + awayTeam + ", fthg=" + fthg + ", ftag=" + ftag + ", ftr=" + ftr + ", hthg="
				+ hthg + ", htag=" + htag + ", htr=" + htr + ", hs=" + hs + ", as=" + as + ", hst=" + hst + ", ast="
				+ ast + ", hf=" + hf + ", af=" + af + ", hc=" + hc + ", ac=" + ac + ", hy=" + hy + ", ay=" + ay
				+ ", hr=" + hr + ", ar=" + ar + "]";
	}
	
	
	
	
	
}
