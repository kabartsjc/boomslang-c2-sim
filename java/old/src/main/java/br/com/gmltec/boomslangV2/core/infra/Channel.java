package br.com.gmltec.boomslangV2.core.infra;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import br.com.gmltec.boomslangV2.core.infra.messages.Message;

public abstract class Channel {
	
	public enum TOPIC {
		SCENARIO, EFFECT
	}
	
	protected ConcurrentHashMap<TOPIC, List<Subscriber>> subscriberLists;
    
    public void sendMessage(TOPIC t, Message m) {
        List<Subscriber> subs = subscriberLists.get(t);
        for (Subscriber s : subs) {
            s.receivedMessage(t, m);
        }
    }

    public void registerSubscriber(Subscriber s, TOPIC t) {
        subscriberLists.get(t).add(s);
    }

}
