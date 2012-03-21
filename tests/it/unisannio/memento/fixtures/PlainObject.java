package it.unisannio.memento.fixtures;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlainObject {
	public static enum SampleEnumeration { CHOICE_1, CHOICE_2, CHOICE_3 };
	
	@Id
	private int id;
	
	private String title;
	
	public PlainObject() {}
	
	public PlainObject(int id, String title) {
		this.id = id;
		this.title = title;
	}
	
	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public void setId(int i) {
		this.id = i;
	}
	
}
