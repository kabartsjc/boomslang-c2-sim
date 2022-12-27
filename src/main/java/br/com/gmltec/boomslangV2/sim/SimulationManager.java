package br.com.gmltec.boomslangV2.sim;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.gmltec.boomslangV2.core.infra.Channel;
import br.com.gmltec.boomslangV2.core.infra.Subscriber;
import br.com.gmltec.boomslangV2.core.infra.messages.UpdateEffectMessage;
import br.com.gmltec.boomslangV2.core.infra.messages.UpdateMovementMessage;
import br.com.gmltec.boomslangV2.entities.IEntity;
import br.com.gmltec.boomslangV2.exe.Exercise;
import br.com.gmltec.boomslangV2.plan.ITask.STATUS;

public class SimulationManager extends Channel implements Runnable {
	private static SimulationManager serverInstance;

	private boolean running = false;

	private Exercise exercise;

	private long simu_time;
	private long real_time;

	private MovementManager moveManB;
	
	private EffectManager effManB;
	
	private List<IEntity>endEntList;

	public static SimulationManager getInstance(Exercise exe) {
		if (serverInstance == null) {
			serverInstance = new SimulationManager(exe);
		}
		return serverInstance;
	}

	private SimulationManager(Exercise exe) {
		exercise = exe;
		super.subscriberLists = new ConcurrentHashMap<>();
		endEntList = new ArrayList<>();
	}

	public void init() {
		moveManB = MovementManager.getInstance();
		Thread thMvB = new Thread(moveManB, "movement manager");
		thMvB.start();

		effManB = EffectManager.getInstance();
		Thread thEffB = new Thread(effManB, "Effect Manager");
		thEffB.start();
		
		try {
			System.out.println("waiting for the other componnents...");
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.running = true;

		simu_time = 10;
		real_time = System.currentTimeMillis();

		while (running) {

			Map<String, IEntity> blueTeam = exercise.getBlueTeam();
			Map<String, IEntity> redTeam = exercise.getRedTeam();
			Map<String, IEntity> greenTeam = exercise.getGreenTeam();
			
			moveManB.moveProcess(simu_time, real_time, blueTeam, redTeam, greenTeam);

			effManB.effectProcess(simu_time, real_time, blueTeam, redTeam, greenTeam);
			
			adjustSim();
			
			if (exercise.end())
				running =false;
			
			
			try {
				System.out.println("time  "+ simu_time +"...");
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			simu_time = simu_time + 10;
			real_time = System.currentTimeMillis();

		} 

	}
	
	private void adjustSim() {
		List<IEntity>blueL = new ArrayList<IEntity>(exercise.getBlueTeam().values());
		for (IEntity ent : blueL) {
			IEntity.STATUS status = ent.getStatus();
			if (status == IEntity.STATUS.DESTROYED || 
					status == IEntity.STATUS.END || status == IEntity.STATUS.FAILED){
				this.endEntList.add(ent);
				exercise.removeNode(ent, "B");
				
			}
		}
		List<IEntity>redL = new ArrayList<IEntity>(exercise.getRedTeam().values());
		
		for (IEntity ent : redL) {
			IEntity.STATUS status = ent.getStatus();
			if (status == IEntity.STATUS.DESTROYED || 
					status == IEntity.STATUS.END || status == IEntity.STATUS.FAILED){
				this.endEntList.add(ent);
				exercise.removeNode(ent, "R");
			}
		}
	}
		


}
