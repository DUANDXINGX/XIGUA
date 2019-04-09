package com.dd;

public class User {
	private int time;
	private String longurl;
	private String shorturl;
	
	public User() {

	}
	
	public User(int time, String longurl, String shorturl) {
		super();
		this.time = time;
		this.longurl = longurl;
		this.shorturl = shorturl;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getLongurl() {
		return longurl;
	}
	public void setLongurl(String longurl) {
		this.longurl = longurl;
	}
	public String getShorturl() {
		return shorturl;
	}
	public void setShorturl(String shorturl) {
		this.shorturl = shorturl;
	}
	@Override
	public String toString() {
		return "User [time=" + time + ", longurl=" + longurl + ", shorturl=" + shorturl + "]";
	}
	
	
}
