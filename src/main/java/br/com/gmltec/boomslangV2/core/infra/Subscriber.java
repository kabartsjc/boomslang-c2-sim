package br.com.gmltec.boomslangV2.core.infra;

import br.com.gmltec.boomslangV2.core.infra.Channel.TOPIC;
import br.com.gmltec.boomslangV2.core.infra.messages.Message;

public abstract class Subscriber {

	public Subscriber(Channel channel, TOPIC...topics) {
        
    }
    
    public abstract void receivedMessage(TOPIC t, Message m);

}
