package com.clsa.codingtest.throttletool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.clsa.codingtest.throttletool.datamodel.MarketData;

public class MarketDataProcessor { 

	private Map<String, MarketData> marketDataInventory = new HashMap<>();
	private Map<String, Date> lastPublish = new HashMap<>();
	private Queue<Date> publishHistory = new ConcurrentLinkedQueue<>();
	private Queue<String> publishQueue = new ConcurrentLinkedQueue<>();
	private Set<String> waitingSet = new HashSet<>();

    // Receive incoming market data

	public void onMessage(MarketData data) {
	
		// do something
		if (!this.marketDataInventory.containsKey(data.getSymbol()) 
				// Ensure each symbol will always have the latest market data when it is published 
				|| !this.marketDataInventory.get(data.getSymbol()).getUpdateTime().after(data.getUpdateTime())) {
			this.marketDataInventory.put(data.getSymbol(), data);
			if (!waitingSet.contains(data.getSymbol())) {
				waitingSet.add(data.getSymbol());
				publishQueue.add(data.getSymbol());
			}
		}
	}
	
	//do something
	
	public void startPublish()  {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					publish();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();
	}
	
	private void publish() throws InterruptedException {
		while (true) {
			while (publishQueue.isEmpty()) {
			}
			// Ensure that the publishAggregatedMarketData method is not called any more than 100 times/sec where this period is a sliding window. 
			while (publishHistory.size() >= 100) {
				while (publishHistory.peek() != null && new Date().getTime() - publishHistory.peek().getTime() > 1000) {
					publishHistory.remove();
				}
			}
			String symbol = publishQueue.remove();
			// Ensure each symbol will not have more than one update per second
			if (lastPublish.containsKey(symbol) 
					&& new Date().getTime() - lastPublish.get(symbol).getTime() < 1000) {
				// add it back to the queue for later publish
				publishQueue.add(symbol);
				continue;
			}
			Date current = new Date();
			publishHistory.add(current);
			lastPublish.put(symbol, current);
			waitingSet.remove(symbol);
			// Ensure each symbol will always have the latest market data when it is published 
			publishAggregatedMarketData(marketDataInventory.get(symbol));
		}
	}

	
	//Publish aggregated and throttled market data
	
	public void publishAggregatedMarketData(MarketData data) {
	
	//Do Nothing, assume implemented.
	
	}
	
}