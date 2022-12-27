package br.com.gmltec.boomslangc2.phy.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import br.com.gmltec.boomslangc2.phy.model.types.AirType;
import br.com.gmltec.boomslangc2.phy.model.types.CyberType;
import br.com.gmltec.boomslangc2.phy.model.types.IEntityType;
import br.com.gmltec.boomslangc2.phy.model.types.LandType;
import br.com.gmltec.boomslangc2.phy.model.types.SeaType;
import br.com.gmltec.boomslangc2.phy.model.types.SpaceType;

public class EntityTypeUtils {
	public static HashMap<String,IEntityType> loadEntityTypes() {
		HashMap <String,IEntityType> entTypes = new HashMap<>();
		
		try {
			Object obj = new JSONParser().parse(new FileReader("src/main/resources/model/entity_type.json"));

			JSONArray joArr = (JSONArray) obj;

			for (int i = 0; i < joArr.size(); i++) {
				IEntityType ent = null;
				JSONObject jo = (JSONObject) joArr.get(i);
				
				String id = (String) jo.get("id");
				boolean mobility_type = (boolean) jo.get("mobility_type");
				String class_type = (String) jo.get("class_type");
				double lethality_factor = (double) jo.get("lethality_factor");
				double resilience_factor = (double) jo.get("resilience_factor");
				double vulnerability_factor = (double) jo.get("vulnerability_factor");
				double cost= (double) jo.get("cost");
								
				if (mobility_type==true) {
					double speed= (double) jo.get("speed");
					double autonomy= (double) jo.get("autonomy");
					
					if (class_type.equals("AF")) {
						double climb_rate= (double) jo.get("climb_rate");
						ent = new AirType(id, lethality_factor, resilience_factor, vulnerability_factor, 
									speed, autonomy, climb_rate,cost);
					}
					
					if (class_type.equals("LF")) {
						ent = new LandType(id, lethality_factor, resilience_factor, 
								vulnerability_factor, speed, autonomy,cost);
					}
					
					if (class_type.equals("SS")) {
						double climb_rate= (double) jo.get("climb_rate");
						ent = new SeaType(id, lethality_factor, resilience_factor, vulnerability_factor,
									speed, autonomy, climb_rate,cost);
					}
					
					if (class_type.equals("SP")) {
						ent = new SpaceType(id, lethality_factor, resilience_factor, 
								vulnerability_factor, speed, autonomy, cost);
					}
					
				}
				
				else {
					if (class_type.equals("AF")) {
						ent = new AirType(id, lethality_factor, resilience_factor, 
								vulnerability_factor,cost);
					}
					
					if (class_type.equals("LF")) {
						ent = new LandType(id, lethality_factor, resilience_factor, 
								vulnerability_factor,cost);
					}
					
					if (class_type.equals("SS")) {
						ent = new SeaType(id, lethality_factor, resilience_factor,
								vulnerability_factor,cost);
					}
					
					if (class_type.equals("CB")) {
						double autonomy= (double) jo.get("autonomy");
						ent = new CyberType(id, lethality_factor, resilience_factor,
								vulnerability_factor,autonomy, cost);					
					}
				}
				
				entTypes.put(id, ent);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
		return entTypes;
	}

}
