package br.com.gmltec.boomslang.core.comm;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;

import br.com.gmltec.boomslang.core.engines.IEngine;

public class ConsumerChannel implements IChannel, Runnable {
	private Session session;
	private Connection connection;
	private MessageConsumer consumer;
	private boolean isRunning = false;
	private IEngine engine;
	
	public ConsumerChannel(IEngine engine) {
		this.engine = engine;
	}

	@Override
	public boolean startConnection(String brokerAddr, int port, String username, String password) {
		// "amqp://localhost:5672"
		JmsConnectionFactory factory = new JmsConnectionFactory(brokerAddr + ":" + port);
		try {
			connection = factory.createConnection(username, password);
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			return true;
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean registerSubscriber(String topic) {
		try {
			Destination destination = session.createTopic(topic);
			consumer = session.createConsumer(destination);
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean endConnection() {
		try {
			connection.close();
			isRunning = false;
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public void run() {
		isRunning=true;
		
		while(isRunning) {
			try {
				Message msg = consumer.receive();
				String body  = ((TextMessage) msg).getText();
				engine.processMessage(body);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	

}
