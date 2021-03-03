package io.radston12.configuration;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import io.radston12.util.ColorHelper;
import io.radston12.util.Logger;
import io.radston12.vars.GuiVariables;
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
			if(next.startsWith("#") || next.startsWith(System.getProperty("line.separator")))
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
		writer.write("#" + s);
		writer.write("# Customize Background color of Application (HEX Color key)" + s);
		writer.write("backgroundColor: " + s);
		writer.write("# Customize Text color of Chat (HEX Color key)" + s);
		writer.write("textColor: " + s);
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
			
		case "backgroundColor":
			if(!argument.startsWith("#")) {
				Logger.info("[CONIFG] Invalid BackgroundColor: " + args);
				Logger.info("[CONIFG] Error: Doesn´t start with a \"#\"");
				break;
			}
			if(argument.length() != 7) { // args[0] = "#" ---- args[6] = "F"
				Logger.info("[CONIFG] Invalid BackgroundColor: " + args);
				Logger.info("[CONIFG] Error: cant load an alpha value!");
				break;
			}
			try {
				ColorHelper.getColor(argument, 1);
			} catch(Exception e) {
				Logger.info("[CONIFG] Error: Cant load background Color!");
				break;
			}
			
			GuiVariables.color = argument;
			break;
		case "textColor":
			if(!argument.startsWith("#")) {
				Logger.info("[CONIFG] Invalid BackgroundColor: " + args);
				Logger.info("[CONIFG] Error: Doesn´t start with a \"#\"");
				break;
			}
			if(argument.length() != 7) { // args[0] = "#" ---- args[6] = "F"
				Logger.info("[CONIFG] Invalid BackgroundColor: " + args);
				Logger.info("[CONIFG] Error: cant load an alpha value!");
				break;
			}
			Color c = null;
			try {
				c = ColorHelper.getColor(argument, 1);
			} catch(Exception e) {
				Logger.info("[CONIFG] Error: Cant load background Color!");
				break;
			}
			
			GuiVariables.fontColor = c;
			break;

		default:
			Logger.info("[CONIFG] Error at loading argument: " + args);
			break;
		}
	}
	
	
	
}
