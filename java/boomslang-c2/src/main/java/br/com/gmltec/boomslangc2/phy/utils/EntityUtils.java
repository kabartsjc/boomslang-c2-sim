package br.com.gmltec.boomslangc2.phy.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import br.com.gmltec.boomslangc2.phy.model.Entity;
import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;
import br.com.gmltec.boomslangc2.phy.model.types.IEntityType;

public class EntityUtils {
	public static HashMap<String,Entity> loadEntities(HashMap <String,IEntityType> entTypeDB) {
		HashMap <String,Entity> entDB= new HashMap<>();
		
		String path = null;
		for (int i=0; i<3;i++) {
			if (i==0)
				path = "exe/b_entities.json";
			else if (i==1)
				path = "exe/r_entities.json";
			else 
				path = "exe/g_entities.json";
			try {
				Object obj = new JSONParser().parse(new FileReader(path));

				JSONArray joArr = (JSONArray) obj;

				for (int j = 0; j < joArr.size(); j++) {
					Entity ent = null;
					JSONObject jo = (JSONObject) joArr.get(j);
					
					String id = (String) jo.get("id");
					String type = (String) jo.get("type");
					int behavior_mode = (int)(long)jo.get("behavior_mode");
					
					String team = (String) jo.get("team");
					String force = (String) jo.get("force");
					
					double latitude = (double) jo.get("latitude");
					double longitude = (double) jo.get("longitude");
					double altitude = (double) jo.get("altitude");
					Coordinate coord = new Coordinate(latitude, longitude, altitude);
					
					IEntityType entType = entTypeDB.get(type);
					
					ent = new Entity(id, team, force, behavior_mode, coord, entType);
					
					entDB.put(id, ent);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		return entDB;
	}

}
