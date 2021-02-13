package io.radston12.configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import io.radston12.util.Logger;
import io.radston12.vars.TwitchVariables;

public class Config {

	private static File file = new File("config.rtchat");
	
	public static void setup() throws IOException {
		if(file.exists()) {
			loadConfig();
		} else {
			writeConfig();
		}
	}
	
	public static void loadConfig() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String next = null;
		while ((next = reader.readLine()) != null) {
			if(next.startsWith("#"))
				continue;
			argument(next);
			
		}
		reader.close();
	}
	
	public static void writeConfig() throws IOException {
		Logger.info("[CONFIG] Could not found a config creating one!");
		String s = System.getProperty("line.separator");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write("# Please enter OAUTH-KEY you can generate it on https://twitchapps.com/tmi/" + s);
		writer.write("twitchOAuth: " + s);
		writer.write("# Please enter the name of the account that generatet oauth!" + s);
		writer.write("twitchUsername: " + s);
		writer.write("# Please enter Channelname from that you want see the chat" + s);
		writer.write("twitchChannel: " + s);
		writer.flush();
		writer.close();
		Logger.info("[CONFIG] Config createt!");
		Logger.info("[CONFIG] Please enter your information!");
		Logger.info("[MAIN] Stopping!");
		System.exit(201);
	}
	
	public static void argument(String args) {
		if(!args.contains(":")) {
			Logger.info("[CONIFG] Error at loading argument: " + args);
			return;
		}
		String splitted[] = args.split(": ");
		String key = splitted[0];
		String argument = splitted[1];
		switch (key) {
		case "twitchOAuth":
			if(argument.startsWith("oauth:"))
				TwitchVariables.OAUTH = argument;
			else
			{
				Logger.info("[CONIFG] Invald Oauth-Key cannot start application");
				System.exit(201);
			}
			break;
		case "twitchUsername":
			TwitchVariables.NICK = argument;
			break;
		case "twitchChannel":
			TwitchVariables.CHANNEL = argument;
			break;

		default:
			Logger.info("[CONIFG] Error at loading argument: " + args);
			break;
		}
	}
}
