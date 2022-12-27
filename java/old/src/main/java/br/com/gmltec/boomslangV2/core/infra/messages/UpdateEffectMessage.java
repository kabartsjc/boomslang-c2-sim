package br.com.gmltec.boomslangV2.core.infra.messages;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import br.com.gmltec.boomslangV2.entities.IEntity;

public class UpdateEffectMessage extends Message {
	private long simu_time;
	private long real_time;
	private List<IEntity>entL;
	private Map<String, IEntity>entDB;
	
	public UpdateEffectMessage (long simu_time, long real_time,
			List<IEntity>entL, Map<String, IEntity>entDB) {
		this.simu_time=simu_time;
		this.real_time = real_time;
		this.entL = entL;
		this.entDB=entDB;
		
	}

	@Override
	public JSONObject getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public long getSimuTime() {
		return simu_time;
	}

	public long getRealTime() {
		return real_time;
	}

	public List<IEntity> getEntities() {
		return entL;
	}

	public Map<String, IEntity> getEntityDB() {
		return entDB;
	}
	
	

}
