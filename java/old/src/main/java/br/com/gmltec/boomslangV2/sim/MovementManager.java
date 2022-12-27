package br.com.gmltec.boomslangV2.sim;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.gmltec.boomslangV2.core.engines.KinectEngine;
import br.com.gmltec.boomslangV2.entities.IEntity;
import br.com.gmltec.boomslangV2.entities.types.MobilityEntity;

public class MovementManager  implements Runnable {

	private boolean running = false;

	private static MovementManager instance;

	private MovementManager() {
		running = true;
	}

	public static MovementManager getInstance() {
		if (instance == null) {
			instance = new MovementManager();
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

	public void moveProcess(long simu_time, long real_time, Map<String, IEntity> blueTeam, Map<String, IEntity> redTeam,
			Map<String, IEntity> greenTeam) {
		
		List<IEntity> blueL = new ArrayList<>(blueTeam.values());
		for (IEntity ent : blueL) {
			if (ent.getEntType() instanceof MobilityEntity) {
				 KinectEngine.update(ent, simu_time);
			}
		}
		
		List<IEntity> redL = new ArrayList<>(redTeam.values());
		for (IEntity ent : redL) {
			if (ent.getEntType() instanceof MobilityEntity) {
				 KinectEngine.update(ent, simu_time);
			}
		}
	}

}

	
