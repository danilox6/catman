package it.unisannio.catman.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public abstract class Unit implements EntryPoint, Comparable<Unit> {
	public static enum Icon {
		INBOX('w'), OUTBOX('v'), CIRCLES('L'), TAG('C'), BADGE('.'), BOLT('Q'), CALENDAR('P'),
		PEOPLE(','), CABINET('t'), GEAR('@');
		
		private final char character;
		Icon(char c) {
			this.character = c;
		}
		
		public char getCharacter() {
			return character;
		}
	}
	
	private final Icon icon;
	private final String name;
	private final int weight;
	
	protected Unit(String name, Icon icon, int weight) {
		this.name = name;
		this.icon = icon;
		this.weight = weight;
	}
	
	protected Unit(String name, Icon icon) {
		this(name, icon, 0);
	}
	
	public String getName() {
		return name;
	}
	
	public Icon getIcon() {
		return icon;
	}
	
	public int getWeight() {
		return weight;
	}
	
	@Override
	public void onModuleLoad() {
		GWT.log("Unit loaded: " + getName());
		Application.get().onUnitLoaded(this);
	}
	
	@Override
	public int compareTo(Unit o) {
		return getWeight() - o.getWeight();
	}
	
	public abstract Activity getDefaultMasterActivity();
	
	public Activity getMasterActivity(String name) {
		return getDefaultMasterActivity();
	}
	
	public abstract Activity getDetailActivity(String name, long id);
}
