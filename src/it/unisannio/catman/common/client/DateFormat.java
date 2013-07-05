package it.unisannio.catman.common.client;

import com.google.gwt.i18n.shared.DateTimeFormat;

public class DateFormat extends DateTimeFormat{

	public DateFormat(String pattern) {
		super(pattern);
	}
	
	public DateFormat() {
		super("dd/MM/yyyy");
	}
	
}