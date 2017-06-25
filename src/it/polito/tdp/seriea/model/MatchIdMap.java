package it.polito.tdp.seriea.model;

import java.util.HashMap;
import java.util.Map;

public class MatchIdMap {
	private Map<Integer, Match> map;

	public MatchIdMap() {
		map = new HashMap<Integer, Match>();
	}
	
	public Match get(int  id){
		return map.get(id);
	}
	
	public void put(Match m){
		 map.put(m.getId(),m);
		 
	}

}
