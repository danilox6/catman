package it.unisannio.catman.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.EntryPoint;

public abstract class Screen implements EntryPoint, Comparable<Screen> {
	public static interface HasMaster {
		Activity getMaster(Intent i);
	}
	
	public static interface HasDetail {
		Activity getDetail(Intent i);
	}
	
	private static final Map<String, Screen> screens = new HashMap<String, Screen>();
	
	protected static void register(Screen s) {
		screens.put(s.getSlug(), s);
	}
	
	public static Screen get(String s) {
		if(!screens.containsKey(s))
			throw new IllegalArgumentException("Screen '" + s + "' doesn't exist");
		
		return screens.get(s);
	}
	
	private final Icon icon;
	private final String title;
	private final String slug;
	private final int weight;
	
	protected Screen(String title, String slug, Icon icon, int weight) {
		this.title = title;
		this.slug = slug;
		this.icon = icon;
		this.weight = weight;
	}
	
	protected Screen(String name, String slug, Icon icon) {
		this(name, slug, icon, 0);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getSlug() {
		return slug;
	}
	
	public Icon getIcon() {
		return icon;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public Screen[] getChildren() {
		return new Screen[0];
	}
	
	@Override
	public void onModuleLoad() {
		register(this);
	}
	
	@Override
	public int compareTo(Screen o) {
		return getWeight() - o.getWeight();
	}
	
	protected void goTo(Intent in) {
		
	}
	
	protected void goTo(Screen s, String... args) {
		
	}
}
