package br.com.gmltec.boomslangV2.sim;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import br.com.gmltec.boomslangV2.core.infra.Channel;
import br.com.gmltec.boomslangV2.core.infra.Subscriber;
import br.com.gmltec.boomslangV2.core.infra.messages.UpdateScenarioMessage;
import br.com.gmltec.boomslangV2.exe.Exercise;

public class SimulationManager extends Channel implements Runnable {
	private static SimulationManager serverInstance;
	
	private boolean running = false;
	
	private Exercise exercise;
	
	private long simu_time;
	private long real_time;
	
	private MovementManager moveManB;
	private MovementManager moveManR;
	private MovementManager moveManG;
	
	public static SimulationManager getInstance(Exercise exe) {
		if (serverInstance == null) {
			serverInstance = new SimulationManager(exe);
		}
		return serverInstance;
	}


	private SimulationManager(Exercise exe) {
		exercise = exe;
		super.subscriberLists = new ConcurrentHashMap<>();
	}
	
	
	public void init() {
		TOPIC topics[] = {TOPIC.SCENARIO};
		moveManB = MovementManager.getInstance(serverInstance, topics);
		moveManR = MovementManager.getInstance(serverInstance, topics);
		moveManG = MovementManager.getInstance(serverInstance, topics);
		
		List<Subscriber>subsL = new ArrayList<>();
		subsL.add(moveManB);
		subsL.add(moveManR);
		subsL.add(moveManG);
		super.subscriberLists.put(TOPIC.SCENARIO, subsL);
		
		Thread thMvB = new Thread(moveManB,"movement Blue");
		Thread thMvR = new Thread(moveManR,"movement Red");
		Thread thMvG = new Thread(moveManG,"movement Green");
		
		thMvB.start();
		thMvR.start();
		thMvG.start();
		
		
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
		
		simu_time=1;
		real_time=System.currentTimeMillis();
		
		while (running) {
			UpdateScenarioMessage scnMsgB = new UpdateScenarioMessage(simu_time, real_time, exercise.getBlueMissions());
			UpdateScenarioMessage scnMsgR = new UpdateScenarioMessage(simu_time, real_time, exercise.getRedMissions());
			UpdateScenarioMessage scnMsgG = new UpdateScenarioMessage(simu_time, real_time, exercise.getGreenMissions());
			
			moveManB.receivedMessage(TOPIC.SCENARIO, scnMsgB);
			moveManR.receivedMessage(TOPIC.SCENARIO, scnMsgR);
			moveManG.receivedMessage(TOPIC.SCENARIO, scnMsgG);
			
			
			try {
				System.out.println("timeout ...");
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
		
		
		
	}
	
	
}
