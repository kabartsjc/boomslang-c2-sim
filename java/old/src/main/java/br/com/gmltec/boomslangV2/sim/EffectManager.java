package br.com.gmltec.boomslangV2.sim;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.gmltec.boomslangV2.core.engines.EffectEngine;
import br.com.gmltec.boomslangV2.entities.IEntity;
import br.com.gmltec.boomslangV2.entities.types.StaticEntity;

public class EffectManager implements Runnable {

	private boolean running = false;
	private static EffectManager instance;

	private EffectManager() {
		running = true;
	}

	public static EffectManager getInstance() {
		if (instance == null) {
			instance = new EffectManager();
		}
		return instance;
	}

	@Override
	public void run() {
		while (running) {

		}

	}

	public void close() {
		this.running = false;
	}

	public void effectProcess(long time_diff, long real_time,
			Map<String, IEntity>blueTeam, Map<String, IEntity>redTeam, 
			Map<String, IEntity>greenTeam) {
		
		List<IEntity> blueL = new ArrayList<>(blueTeam.values());
		for (IEntity ent : blueL) {
			if (ent.getEntType() instanceof StaticEntity) {
				 EffectEngine.static_effect(ent, redTeam);
			}
			
			else {
				EffectEngine.mobile_effect(ent, redTeam, time_diff);
			}
		}
		
		List<IEntity> redL = new ArrayList<>(redTeam.values());
		for (IEntity ent : redL) {
			if (ent.getEntType() instanceof StaticEntity) {
				 EffectEngine.static_effect(ent, blueTeam);
			}
			
			else {
				EffectEngine.mobile_effect(ent, blueTeam, time_diff);
			}
		}
		
	}

}
