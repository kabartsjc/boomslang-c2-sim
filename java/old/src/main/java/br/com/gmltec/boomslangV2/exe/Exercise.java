package br.com.gmltec.boomslangV2.exe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.gmltec.boomslangV2.core.geo.Coordinate;
import br.com.gmltec.boomslangV2.core.utils.JsonUtils;
import br.com.gmltec.boomslangV2.entities.IEntity;
import br.com.gmltec.boomslangV2.entities.sensors.ISensor;
import br.com.gmltec.boomslangV2.entities.types.IEntityType;
import br.com.gmltec.boomslangV2.entities.weapons.IWeapon;

public class Exercise {
	
	private String simu_addr;
	private int simu_port;
	
	private Coordinate map_center;
	
	private Map<String, IWeapon> weaponDB;
	private Map<String, ISensor> sensorDB;
	private Map<String, IEntityType> entityTypeDb;
	
	private Map<String, IEntity> blueTeam;
	private Map<String, IEntity> redTeam;
	private Map<String, IEntity> greenTeam;
	
	public Exercise() {
		
		Object simu_params[] = JsonUtils.loadServerParams();
		simu_addr = (String) simu_params[0];
		simu_port = (int) simu_params[1];
		map_center=(Coordinate) simu_params[2];
		
		weaponDB=JsonUtils.loadWeapons();
		sensorDB = JsonUtils.loadSensors();
		entityTypeDb=JsonUtils.loadEntityTypes();
		
		blueTeam=JsonUtils.loadEntities("B");
		redTeam=JsonUtils.loadEntities("R");
		greenTeam=JsonUtils.loadEntities("G");
		
		Map<String, IEntity> otherMap =  new ConcurrentHashMap<>();
		otherMap.putAll(redTeam);
		otherMap.putAll(greenTeam);
		JsonUtils.loadTasks("B", blueTeam, otherMap);
		
		otherMap =  new ConcurrentHashMap<>();
		otherMap.putAll(blueTeam);
		otherMap.putAll(greenTeam);
		JsonUtils.loadTasks("R", redTeam, otherMap);
		
		otherMap =  new ConcurrentHashMap<>();
		otherMap.putAll(blueTeam);
		otherMap.putAll(redTeam);
		JsonUtils.loadTasks("G", greenTeam, otherMap);
		
		
		
		for (IEntity ent:blueTeam.values()) {
			ent.init();
		}
		
		for (IEntity ent:redTeam.values()) {
			ent.init();
		}
		for (IEntity ent:greenTeam.values()) {
			ent.init();
		}
		
		
	}
	
	public String getSimuAddr() {
		return simu_addr;
	}

	public int getSimuPort() {
		return simu_port;
	}
	
	public Coordinate getMapcenter() {
		return map_center;
	}

	public Map<String, IWeapon> getWeaponDB() {
		return weaponDB;
	}

	public Map<String, ISensor> getSensorDB() {
		return sensorDB;
	}

	public Map<String, IEntityType> getEntityTypeDb() {
		return entityTypeDb;
	}

	public Map<String, IEntity> getBlueTeam() {
		return blueTeam;
	}

	public Map<String, IEntity> getRedTeam() {
		return redTeam;
	}

	public Map<String, IEntity> getGreenTeam() {
		return greenTeam;
	}
	
	
	public Map<String, IEntity> getEntityDB(){
		Map<String, IEntity> entDB =  new ConcurrentHashMap<>();
			entDB.putAll(redTeam);
			entDB.putAll(blueTeam);
			entDB.putAll(greenTeam);
		return entDB;
	}
	
	public void removeNode(IEntity ent, String force) {
		if (force.equals("B")) {
			this.blueTeam.remove(ent.getId());
		}
		else if (force.equals("R")) {
			this.redTeam.remove(ent.getId());
			
		}
	}

	public boolean end() {
		if (redTeam.size()==0 && blueTeam.size()==0 && greenTeam.size()==0)
			return true;
		else 
			return false;
	}


}
