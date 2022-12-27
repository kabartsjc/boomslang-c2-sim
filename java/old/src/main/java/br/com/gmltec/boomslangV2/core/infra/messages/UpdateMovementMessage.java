package br.com.gmltec.boomslangV2.core.infra.messages;

import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.gmltec.boomslangV2.entities.IEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateMovementMessage extends Message {
	private long simu_time;
	private long real_time;
	private List<IEntity> entityL;
	

	public UpdateMovementMessage(long simu_time, long real_time,List<IEntity> entityL) {
		this.real_time = real_time;
		this.simu_time = simu_time;
		this.entityL=entityL;
	}

	@Override
	@SuppressWarnings("unchecked")
	public JSONObject getMessage() {
		JSONObject jsonObj;
		jsonObj = new JSONObject();
		jsonObj.put("name", "clock-message");
		jsonObj.put("real_time", real_time);
		jsonObj.put("simu_time", simu_time);
		return jsonObj;
	}

	public long getSimu_time() {
		return simu_time;
	}

	public long getReal_time() {
		return real_time;
	}

	public List<IEntity> getEntities() {
		return entityL;
	}
	
	
	

}
