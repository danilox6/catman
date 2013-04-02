package it.unisannio.catman.client;

import java.util.TreeMap;

@SuppressWarnings("serial")
public class Intent extends TreeMap<Integer, String> {
	private final Screen screen;
	
	public Intent(Screen screen) {
		this.screen = screen;
	}
	
	public Intent(String screen) {
		this.screen = Screen.get(screen);
	}
	
	public Intent withParams(String... params) {
		int offset = lastKey() + 1;
		for(int i = 0; i < params.length; ++i) {
			put(i + offset, params[i]);
		}
		
		return this;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public String get(int key, String defaultValue) {
		return containsKey(key) ? get(key) : defaultValue;
	}
	
	public int get(int key, int defaultValue) {
		return containsKey(key) ? Integer.parseInt(get(key)) : defaultValue;
	}
	
	public double get(int key, double defaultValue) {
		return containsKey(key) ? Double.parseDouble(get(key)) : defaultValue;
	}
	
	public boolean get(int key, boolean defaultValue) {
		return containsKey(key) ? Boolean.parseBoolean(get(key)) : defaultValue;
	}
	
	public void put(int key, int val) {
		put(key, String.valueOf(val));
	}
	
	public void put(int key, double val) {
		put(key, String.valueOf(val));
	}
	
	public void put(int key, boolean val) {
		put(key, String.valueOf(val));
	}
}
