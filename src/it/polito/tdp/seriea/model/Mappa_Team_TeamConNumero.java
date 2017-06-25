package it.polito.tdp.seriea.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Mappa_Team_TeamConNumero {
	private HashMap<Team,TeamConNumero> mappa;

	public Mappa_Team_TeamConNumero() {
		mappa=new  HashMap<Team,TeamConNumero>();
	}
	
	public void SommaNumeroPerQuestoTeam(Team t, int numeroDaAggiungere) {
		if(mappa.get(t)==null){
			mappa.put(t, new TeamConNumero(t,numeroDaAggiungere));
			}else{
				TeamConNumero tcn= mappa.get(t);
				tcn.setNumero( numeroDaAggiungere+tcn.getNumero());
			}
	}

	public String classificaOrdinata(){
		LinkedList<TeamConNumero> lista= new LinkedList<TeamConNumero>(mappa.values());
		Collections.sort(lista);
		
		StringBuilder risultato = new StringBuilder();
		
		risultato.append("Classifica Team:\n");
				
		for (TeamConNumero c: lista) {
				risultato.append(c.getTeam().toString()+" Ha effettuato : "+c.getNumero()+"punti , ");
		}
		risultato.setLength(risultato.length() - 2);//elimina ultimo ", "
		risultato.append(".");


		return risultato.toString();
	}
}
