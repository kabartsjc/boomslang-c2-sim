package br.com.gmltec.boomslangV2.core.infra;

import br.com.gmltec.boomslangV2.core.infra.Channel.TOPIC;
import br.com.gmltec.boomslangV2.core.infra.messages.Message;

public abstract class Publisher {
	private TOPIC topic;

	public Publisher(TOPIC t) {
		this.topic = t;
	}

	public void publish(Message m, Channel channel) {
		channel.sendMessage(this.topic, m);
	}

}
