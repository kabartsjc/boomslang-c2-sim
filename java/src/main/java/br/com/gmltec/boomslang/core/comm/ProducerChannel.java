package br.com.gmltec.boomslang.core.comm;

import java.util.Hashtable;
import java.util.List;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;

import br.com.gmltec.boomslang.core.messages.IMessage;

public class ProducerChannel implements IChannel {
	private Session session;
	private Connection connection;
	private Hashtable<String, MessageProducer> producerDb;

	public ProducerChannel() {
		producerDb = new Hashtable<>();
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

	@Override
	public boolean endConnection() {
		try {
			connection.close();
			return true;
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean registerProducers(List<String> topicList) {
		for (String topic : topicList) {
			try {
				Destination destination = session.createTopic(topic);
				MessageProducer producer = session.createProducer(destination);
				producerDb.put(topic, producer);
			} catch (JMSException e) {
				e.printStackTrace();
				return false;
			}
		}

		return true;
	}

	public boolean publish(String topic, IMessage message) {
		try {
			TextMessage msgTxt= session.createTextMessage(message.toString());
			MessageProducer producer = this.producerDb.get(topic);
			producer.send(msgTxt);
			
		} catch (JMSException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
