package it.unisannio.catman.common.client;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public enum Icon {

	
	TIE('1'), OFFICE_CHAIR('2'), FAVORITE('3'), BRIEFCASE('4'), CONTACT('5'), CALENDAR('6'),
	NOTES('7'), CHECKLIST('8'), CHEF('9'), BASKET('a'), PACKAGE('b'), FORKLIFT('c'), SHOPPING_CART('d'),
	BREAKABLE('e'), DARK_SIDE_OF_THE_MOON('f'), MOUSTACHE('g');
	
	private final char character;
	
	public static final String DATA_LIST_ICON_CLASS = "data-list-icon";
	
	Icon(char c) {
		this.character = c;
	}
	
	public char getCharacter() {
		return character;
	}
	
	@Override
	public String toString() {
		return String.valueOf(character);
	}
	
	public SafeHtml toSafeHtml() {
		return new SafeHtmlBuilder()
			.appendHtmlConstant("<span class=\"" + DATA_LIST_ICON_CLASS + "\">")
			.append(character)
			.appendHtmlConstant("</span>")
			.toSafeHtml();
	}
}