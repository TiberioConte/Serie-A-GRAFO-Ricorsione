package it.polito.tdp.seriea.model;

import java.util.HashMap;
import java.util.Map;

public class TeamIdMap {
	private Map<String,Team> map;

	public TeamIdMap() {
		map = new HashMap<String,Team>();
	}
	
	public Team get(String  id){
		return map.get(id);
	}
	
	public void put(Team t){
		 map.put(t.getTeam(),t);
		 
	}
}
