package it.polito.tdp.seriea.model;

public class TeamConNumero implements Comparable <TeamConNumero>{
	private Team team;
	private int numero;
	
	
	public TeamConNumero(Team team, int numero) {
		super();
		this.team = team;
		this.numero = numero;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	//viene prima team con numero maggiore 
	public int compareTo(TeamConNumero arg0) {
		return arg0.getNumero()-this.getNumero();
	}

}
