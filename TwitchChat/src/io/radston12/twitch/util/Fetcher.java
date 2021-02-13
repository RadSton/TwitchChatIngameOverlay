package io.radston12.twitch.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import io.radston12.Main;
import io.radston12.util.Logger;
import io.radston12.vars.TwitchVariables;

public class Fetcher {
	private WebSocketClient ws = null;
	private boolean b = false; 
	public void startWebsocket() throws URISyntaxException {
		ws = new WebSocketClient(new URI("ws://irc-ws.chat.twitch.tv:80"), new Draft_6455()) {
			
			@Override
			public void onOpen(ServerHandshake arg0) {
				Logger.info("[TWITCH] Connected!");
				sendHi();
			}
			
			@Override
			public void onMessage(String msg) {
				if(msg.contains("PING :tmi.twitch.tv")) {
					Logger.debug("[TWITCH] Playing pong with server! LuL");
					ws.send("PONG :tmi.twitch.tv");
					return;
				}
				if(msg.substring(1, 4).contains("tmi") || msg.contains("JOIN")) {
					if(!b) {
						Logger.debug("[TWITCH] Send welcome message");
						b = true;
					} else {
						Logger.debug("[TWITCH] System message: " + msg);
					}
					return;
				}
				String args[] = msg.split(" ");
            	String author = args[0].substring(1, args[0].indexOf("!"));
            	String ma[] = msg.split(":");
            	String m = ma[ma.length - 1];
            	Logger.info("[TWITCH] Got chatmessage: " + author + ": " + m);
            	Main.instance.c.addMessage(m, author);
			}
			
			@Override
			public void onError(Exception arg0) {
			}
			
			@Override
			public void onClose(int arg0, String arg1, boolean arg2) {
				
			}
		};
		ws.connect();
	}
	
	public void close() {
		if(ws == null)
			return;
		ws.close();
		ws = null;
	}
	
	public void sendMessage(String msg) {
		if(ws == null)
			return;
		if(ws.isClosed()) 
			return;
		ws.send("PRIVMSG #" + TwitchVariables.NICK + " :" + msg);
	}
	
	public void sendHi() {
		ws.send("PASS " + TwitchVariables.OAUTH);
		ws.send("NICK " + TwitchVariables.NICK);
		ws.send("JOIN #" + TwitchVariables.CHANNEL);
	}
}
