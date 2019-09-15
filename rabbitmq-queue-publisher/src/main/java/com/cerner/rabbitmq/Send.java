package com.cerner.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Harshavardhana Vishwanathachar (HV056781)
 * @category Rabbit MQ
 * 
 * This is a stand-alone java project to publish messages to a queue in RabbitMQ.
 * Project created for cerner_2^5_2019
 */
public class Send {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri("amqp://redkawrg:sTz2tQrw69OaHUeFDBdiRvNg6Q_6KZDZ@albatross.rmq.cloudamqp.com/redkawrg");
		
		// Alternatively MQ host details could be mentioned as below
		/*
		 factory.setHost("albatross-01.rmq.cloudamqp.com"); //Mention localhost if the RabbitMQ host is running locally.
		 factory.setUsername("redkawrg");
		 factory.setPassword("sTz2tQrw69OaHUeFDBdiRvNg6Q_6KZDZ");
		 */
		
		// Establish the connection and open the channel
		try (Connection connection = factory.newConnection();
				Channel channel = connection.createChannel()) {
			// Declare the queue to which message needs to be published
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "{\"firstName\":\"Harsha\", \"lastName\":\"V\"}";
			// Publish the message
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
			System.out.println(" [x] Sent '" + message + "'");
		}
	}
}
