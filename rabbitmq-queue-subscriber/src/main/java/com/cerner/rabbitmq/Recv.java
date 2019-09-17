package com.cerner.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * @author Harshavardhana Vishwanathachar (HV056781)
 * @category Rabbit MQ
 * 
 * This is a stand-alone java project to read messages from a queue in RabbitMQ.
 * Project created for cerner_2^5_2019
 */
public class Recv {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri("amqp://redkawrg:sTz2tQrw69OaHUeFDBdiRvNg6Q_6KZDZ@albatross.rmq.cloudamqp.com/redkawrg");

		// Establish the connection and open the channel
		try (Connection connection = factory.newConnection();
				Channel channel = connection.createChannel()) {
			// Declare the queue to which message needs to be published
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

			// Read the message
			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				String message = new String(delivery.getBody(), "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
			};
			channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
		}



	}
}
