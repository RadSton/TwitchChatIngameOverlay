package io.radston12.util;

public class Logger {

	public static void debug(String msg) {
		System.out.println("[DEBUG | "+ Thread.currentThread().getName() + "] " + msg);
	}
	
	public static void info(String msg) {
		System.out.println("[INFO | "+ Thread.currentThread().getName() + "] " + msg);
	}
}
