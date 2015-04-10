package com.moneylocker.consumer.consumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import com.moneylocker.consumer.handler.MessageHandler;

public class KafkaConsumer {

	private Properties properties;

	private ConsumerConnector consumer;

	private String topic;

	private MessageHandler messageHandler;

	private Map<String, Integer> topicMap;

	private ExecutorService executor;

	public Map<String, Integer> getTopicMap() {
		return topicMap;
	}

	public void setTopicMap(Map<String, Integer> topicMap) {
		this.topicMap = topicMap;
	}

	public KafkaConsumer() {

	}

	public void start() throws IOException {
		consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicMap);
		List<KafkaStream<byte[], byte[]>> streams = new ArrayList<KafkaStream<byte[], byte[]>>();

		for(String topic: consumerMap.keySet()) {
			streams.addAll(consumerMap.get(topic));
		}

		executor = Executors.newFixedThreadPool(streams.size());

		int threadNumber = 0;

		for (final KafkaStream<byte[], byte[]> stream : streams) {
			executor.submit(new ConsumerHandler(stream, threadNumber, messageHandler, topic));
			threadNumber++;
		}
	}

	public void close() {
		if (consumer != null)
			consumer.shutdown();
		if (executor != null)
			executor.shutdown();
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties prop) {
		this.properties = prop;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public MessageHandler getMessageHandler() {
		return messageHandler;
	}

	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}
}

class ConsumerHandler implements Runnable {

	private KafkaStream<byte[], byte[]> mStream;

	private int mThreadNumber;

	private MessageHandler messageHandler;

	private String topic;

	public ConsumerHandler(KafkaStream<byte[], byte[]> mStream, int mThreadNumber, MessageHandler messageHandler,
			String topic) {
		this.mThreadNumber = mThreadNumber;
		this.mStream = mStream;
		this.messageHandler = messageHandler;
		this.topic = topic;
	}

	@Override
	public void run() {
		ConsumerIterator<byte[], byte[]> it = mStream.iterator();

		while (it.hasNext()) {
			messageHandler.onMessage(it.next(), mThreadNumber, topic);
		}
	}
}
