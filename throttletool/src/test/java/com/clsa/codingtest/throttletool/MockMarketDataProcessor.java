package com.clsa.codingtest.throttletool;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.clsa.codingtest.throttletool.datamodel.MarketData;

public class MockMarketDataProcessor extends MarketDataProcessor {
	//Publish aggregated and throttled market data

	@Override
	public void publishAggregatedMarketData(MarketData data) {
	
	//Do Nothing, assume implemented.
		SimpleDateFormat sf = new SimpleDateFormat("mm.ss.SSS");
		System.out.println(sf.format(new Date()) + " - Publishing " + data.getSymbol() + ": " + data.getLast() + " at " + sf.format(data.getUpdateTime()));
	
	}

}
