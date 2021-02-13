package io.radston12.twitch;

public class Message {

	private String msg; 
	private String author;
	private long send;
	
	public Message(String msg, String author) {
		this.msg = msg;
		this.author = author;
		this.send = System.currentTimeMillis();
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public long getSend() {
		return send;
	}
	public void setSend(long send) {
		this.send = send;
	}
	
	
}
