package br.com.gmltec.boomslangV2.sim;

import br.com.gmltec.boomslangV2.core.infra.Channel;
import br.com.gmltec.boomslangV2.core.infra.Channel.TOPIC;
import br.com.gmltec.boomslangV2.core.infra.Subscriber;
import br.com.gmltec.boomslangV2.core.infra.messages.Message;
import br.com.gmltec.boomslangV2.core.infra.messages.UpdateScenarioMessage;
import br.com.gmltec.boomslangV2.core.movemment.KinectEngine;
import br.com.gmltec.boomslangV2.entities.IEntity;
import br.com.gmltec.boomslangV2.plan.IMission;

public class MovementManager extends Subscriber implements Runnable{
	
	private long last_time_sim;
	
	private boolean running = false;
	
	private static MovementManager instance;
	
	private MovementManager(Channel channel, TOPIC[] topics) {
		super(channel, topics);
		running=true;
		last_time_sim=0;
	}
	
	public static MovementManager getInstance(Channel channel, TOPIC[] topics) {
		if (instance==null) {
			instance = new MovementManager(channel, topics);
		}
		
		return instance;
		
	}

	@Override
	public void receivedMessage(TOPIC topic, Message message) {
		if (topic == TOPIC.SCENARIO && message instanceof UpdateScenarioMessage) {
			UpdateScenarioMessage updateMsg = (UpdateScenarioMessage)message;
			
			long time_diff = updateMsg.getSimu_time()-last_time_sim;
			last_time_sim=updateMsg.getSimu_time();
			
			
			for (IMission mission: updateMsg.getMissions().values()) {
				for (IEntity entity : mission.getPerformers()) {
					KinectEngine.update(entity, time_diff);
				}
			}
		}
	}

	@Override
	public void run() {
		while (running) {
			
		}
		
	}
	
	
	public void close() {
		this.running=false;
	}

}
