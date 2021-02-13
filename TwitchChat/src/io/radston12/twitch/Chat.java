package io.radston12.twitch;

import java.util.ArrayList;
import java.util.List;

import io.radston12.vars.TwitchVariables;

public class Chat {

	public List<Message> msgs = new ArrayList<Message>();
	public Message[] msgsarray = new Message[10];
	
	public String update() {
		while(msgs.size() > TwitchVariables.MAX_MESSAGES) {
			msgs.remove(0);
		}
		msgsarray = new Message[msgs.size()];
		for (int i = 0; i <= msgs.size() - 1; i++) {
			msgsarray[i] = msgs.get(i);
		}
		return getFormatetText();
	}
	
	public String addMessage(String msg, String author) {
		msgs.add(new Message(msg,author));
		return this.update();
	}
	
	public String getFormatetText() {
		String formtext = "";
		for (int i = 0; i < msgsarray.length ; i++) {
			formtext += format(buildChatMessage(msgsarray[i])); //+ System.getProperty("line.separator")
		}
		return formtext;
	}
	
	public String buildChatMessage(Message msg) {
		return msg.getAuthor() + ": " + msg.getMsg();
	}
	
	public String format(String str) {
		str = str.replaceAll("(.{" + (TwitchVariables.LINE_LENGTH - 2) + "}) ", "$0" + System.getProperty("line.separator"));
		String temp = "";
		for(String line : str.split(System.getProperty("line.separator"))){
			   if(line.length() >= 13 && line.charAt(line.length() - 1) != ' ') {
				   line = line.replaceAll(".{" + TwitchVariables.LINE_LENGTH + "}", "$0-" + System.getProperty("line.separator"));
			   } 
			   temp += line + System.getProperty("line.separator");
		}
		return temp;
	}
	
}
