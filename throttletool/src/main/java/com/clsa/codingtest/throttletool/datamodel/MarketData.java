package com.clsa.codingtest.throttletool.datamodel;

import java.util.Date;

public class MarketData {
	private String symbol;
	private double bid;
	private double ask;
	private double last;
	private Date updateTime;
	
	public MarketData(String symbol, double bid, double ask, double last) {
		super();
		this.symbol = symbol;
		this.bid = bid;
		this.ask = ask;
		this.last = last;
		this.updateTime = new Date();
	}
	
	public String getSymbol() {
		return symbol;
	}
	public double getBid() {
		return bid;
	}
	public double getAsk() {
		return ask;
	}
	public double getLast() {
		return last;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
}
