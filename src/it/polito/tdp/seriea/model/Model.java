package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;

public class Model {
	private ArrayList<Season> stagioni;
	private SerieADAO dao;
	private SimpleDirectedWeightedGraph<Team,DefaultWeightedEdge>  grafo;
	private TeamIdMap mappaTeam;
	
	//ricorsione
	private ArrayList<Team> camminoSoluzioneOttima;
	
	
	public Model() {
		this.dao = new SerieADAO();
		this.stagioni = dao.listSeasons();
		mappaTeam=new TeamIdMap();
	}
	
	public ArrayList<Season> getStagioni() {
		return stagioni;
	}
	
	public Set<Team> getTeam(){
		return grafo.vertexSet();
	}

	public void CreaGrafo(Season s){
		grafo=new SimpleDirectedWeightedGraph<Team,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		//carico i vertici 
		Graphs.addAllVertices(grafo, dao.getAllTeamsInThisSeason(s,mappaTeam));
		System.out.format("Ho aggiunto %d nodi",grafo.vertexSet().size());
		//carico gli archi
		for(Match m:dao.getAllMacthInThisSeason(s,mappaTeam)){
			int peso=0;
			switch(m.getFtr()) {
		    case "H":
		        peso=1;
		        break;
		    case "D":
		        peso=0;
		        break;
		    case "A": 
		    	peso=-1;
		        break;
		    default:
		    	throw new IllegalArgumentException("ERRORE! valore non ammesso!");
			}
			DefaultWeightedEdge arco=grafo.addEdge(m.getHomeTeam(), m.getAwayTeam());
			grafo.setEdgeWeight(arco, peso);
		}
		System.out.format("Ho aggiunto %d archi",grafo.edgeSet().size());
	}
	public String getClassifica(){
		//esplorando arco arco
		Mappa_Team_TeamConNumero mappa=new Mappa_Team_TeamConNumero();
		for(DefaultWeightedEdge arco:grafo.edgeSet()){
			Team casa=grafo.getEdgeSource(arco);
			Team ospite=grafo.getEdgeTarget(arco);
			int peso=(int)grafo.getEdgeWeight(arco);
			switch(peso) {
		    case 1:
		        mappa.SommaNumeroPerQuestoTeam(casa, 3);
		        break;
		    case 0:
		    	mappa.SommaNumeroPerQuestoTeam(casa, 1);
		    	mappa.SommaNumeroPerQuestoTeam(ospite, 1);
		        break;
		    case -1: 
		    	mappa.SommaNumeroPerQuestoTeam(ospite, 3);
		        break;
		    default:
		    	throw new IllegalArgumentException("ERRORE! valore non ammesso!");
			}
		}
		return mappa.classificaOrdinata();
		
		////esplorando vertice vertice 
		//
		//List<TeamConNumero> lista = new ArrayList<>();	
		//
		//for(Team t: grafo.vertexSet()){
		//	//metto il punteggio a 0 ogni volta!
		//	int punteggio=0;
		//	
		//	for(DefaultWeightedEdge edge: grafo.outgoingEdgesOf(t)){
		//		if(grafo.getEdgeWeight(edge)==1)
		//			punteggio +=3;
		//		if(grafo.getEdgeWeight(edge)==0){
		//			punteggio +=1;
		//		}
		//	}
		//	for(DefaultWeightedEdge ed: graph.incomingEdgesOf(t)){
		//		if(grafo.getEdgeWeight(ed)==-1)
		//			punteggio +=3;
		//		if(grafo.getEdgeWeight(ed)==0){
		//			punteggio +=1;
		//		}
		//	}
		//	
		//	lista.add(new TeamConNumero(t, punteggio));
		//}
		//
		//Collections.sort(lista);
		//return lista;
	}
	
	public ArrayList<Team> interfacciaRicorsione(){
		//ri-inizializzo struttura dati della soluzione ottima
		camminoSoluzioneOttima= new ArrayList<Team>();
		//creo struttura dati della soluzione parziale
		HashSet<DefaultWeightedEdge> archiUsatiSoluzioneParziale=new HashSet<DefaultWeightedEdge>();
		ArrayList<Team> camminoSoluzioneParziale=new ArrayList<Team>();
		//variabile int memorizza il livello della soluzione parziale 
		int livello=0;
		
		//avvio il metodo ricorsivo sciegliendo uno alla volta tutti i nodi come nodo di partenza
		for(Team t:grafo.vertexSet()){
			camminoSoluzioneParziale.add(t);
			Ricorsivo(archiUsatiSoluzioneParziale,camminoSoluzioneParziale,livello);
			camminoSoluzioneParziale.clear();	
			archiUsatiSoluzioneParziale.clear();
		}
		return camminoSoluzioneOttima; 
	}
	
	public ArrayList<Team> interfacciaRicorsione(Team t){
		//ri-inizializzo struttura dati della soluzione ottima
		camminoSoluzioneOttima= new ArrayList<Team>();
		//creo struttura dati della soluzione parziale
		HashSet<DefaultWeightedEdge> archiUsatiSoluzioneParziale=new HashSet<DefaultWeightedEdge>();
		ArrayList<Team> camminoSoluzioneParziale=new ArrayList<Team>();
		//variabile int memorizza il livello della soluzione parziale 
		int livello=0;
		
		camminoSoluzioneParziale.add(t);
		Ricorsivo(archiUsatiSoluzioneParziale,camminoSoluzioneParziale,livello);
		
		return camminoSoluzioneOttima; 
	}
	//attento hai 2 strutture dati per salvare la soluzione parziale 
	public void Ricorsivo(HashSet<DefaultWeightedEdge> archiUsatiSoluzioneParziale,ArrayList<Team> camminoSoluzioneParziale,int livello){
		//condizione di terminazione implicita quando hai già considerato tutti gli archi con peso -1 uscenti dall'ultimo nodo considerato 
		//ti basta verificare se la soluzione è ottima e nel caso salvarla
		if(camminoSoluzioneParziale.size()>camminoSoluzioneOttima.size()){
			camminoSoluzioneOttima.clear();
			camminoSoluzioneOttima.addAll(camminoSoluzioneParziale);
		}
		
		Team ultimoAggiunto=camminoSoluzioneParziale.get(camminoSoluzioneParziale.size()-1);
		//ciclo for 
		for(DefaultWeightedEdge arco:grafo.outgoingEdgesOf(ultimoAggiunto)){
			//controllo
			if(grafo.getEdgeWeight(arco)==(+1)&&!archiUsatiSoluzioneParziale.contains(arco)){
					//metto
					Team t=grafo.getEdgeTarget(arco);
					camminoSoluzioneParziale.add(t);
					archiUsatiSoluzioneParziale.add(arco);
					//provo
					this.Ricorsivo(archiUsatiSoluzioneParziale, camminoSoluzioneParziale,livello+1);
					//tolgo
					camminoSoluzioneParziale.remove(camminoSoluzioneParziale.size()-1) ; // togli l'ultimo aggiunto
					// Attenzione: soluzioneParziale.remove(t) ; non funziona perché t può comparire più di una volta
					archiUsatiSoluzioneParziale.remove(arco);
			}
		}
	}
	
	public void RiduciGrafo(){
		//visto che la ricorsione è troppo lunga, per la stagione 2005/2006 riduco il grafo eliminando alcuni vertici
		//riduzione coincidente con quella fatta dal prof nella sua versione
				grafo.removeVertex(new Team("Fiorentina"));
				grafo.removeVertex(new Team("Livorno"));
				grafo.removeVertex(new Team("Ascoli"));
				grafo.removeVertex(new Team("Inter"));
				grafo.removeVertex(new Team("Juventus"));
				grafo.removeVertex(new Team("Lazio"));
				//grafo.removeVertex(new Team("Parma"));
				//grafo.removeVertex(new Team("Reggina"));
				//grafo.removeVertex(new Team("Siena"));
				//grafo.removeVertex(new Team("Udinese"));
				grafo.removeVertex(new Team("Milan"));
				//grafo.removeVertex(new Team("Palermo"));
				//grafo.removeVertex(new Team("Cagliari"));
				grafo.removeVertex(new Team("Chievo"));
				//grafo.removeVertex(new Team("Empoli"));
				grafo.removeVertex(new Team("Lecce"));
				grafo.removeVertex(new Team("Messina"));
				//grafo.removeVertex(new Team("Roma"));
				grafo.removeVertex(new Team("Sampdoria"));
				grafo.removeVertex(new Team("Treviso"));
				System.err.println("Vertici rimasti: "+grafo.vertexSet().size());
				System.err.println(grafo.vertexSet().toString());
				System.err.println("Archi rimasti: "+grafo.edgeSet().size());
	}

}
