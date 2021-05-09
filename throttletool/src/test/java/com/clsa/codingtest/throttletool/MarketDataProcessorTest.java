package com.clsa.codingtest.throttletool;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.clsa.codingtest.throttletool.datamodel.MarketData;

public class MarketDataProcessorTest {

    public static void main( String[] args ) throws InterruptedException
    {
        MarketDataProcessor mdp = new MockMarketDataProcessor();
        mdp.startPublish();
        
        int i=0;
        while (i++<1000) {
        	MarketData md = new MarketData((int)(Math.random()*3) + ".HK", Math.random()*100, Math.random()*100, Math.random()*100);
    		SimpleDateFormat sf = new SimpleDateFormat("mm.ss.SSS");
    		System.out.println(sf.format(new Date()) + " - Sending " + md.getSymbol() + ": " + md.getLast() + " at " + sf.format(md.getUpdateTime()));
        	mdp.onMessage(md);
        	Thread.sleep((int)(Math.random()*2));
        }
    }
}
