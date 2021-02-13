package io.radston12;

import java.io.IOException;
import java.net.URISyntaxException;

import io.radston12.configuration.Config;
import io.radston12.gui.Gui;
import io.radston12.twitch.Chat;
import io.radston12.twitch.util.Fetcher;

public class Main {

	public String messageString = "";
	public Chat c;
	public static Main instance;
	public Fetcher f; 
	public Main() {
		try {
			Config.setup();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		c = new Chat();
		f = new Fetcher();
		try {
			f.startWebsocket();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		new Gui();
	}
	
	public void update() {
		messageString  = c.update();
	}
	
	public static void main(String[] args) {
		Thread.currentThread().setName("MAIN");
		instance = new Main();
	}
	
}
