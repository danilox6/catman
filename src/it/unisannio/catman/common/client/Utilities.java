package it.unisannio.catman.common.client;

import it.unisannio.catman.domain.contacts.client.IsAddressable;
import it.unisannio.catman.domain.contacts.client.IsContactable;

public class Utilities {

	public static String buildContactInfoHTML(IsAddressable a){
		StringBuilder sb = new StringBuilder();
		sb.append("<ul>");
		if(a instanceof IsContactable){
			IsContactable c = (IsContactable) a;
			appendListItemHTML(sb, "E-mail", c.getEmail());
			appendListItemHTML(sb, "Phone", c.getPhone());
		}
		appendListItemHTML(sb, "Address", a.getAddress());
		appendListItemHTML(sb, "City", a.getCity());
		appendListItemHTML(sb, "State", a.getState());
		appendListItemHTML(sb, "Country", a.getCountry());
		appendListItemHTML(sb, "Postal Code", a.getPostalCode());
		sb.append("</ul>");
		return sb.toString().contains("<li>")?sb.toString():null;
	}
	private static void appendListItemHTML(StringBuilder sb, String propertyName, String value){
		if(value != null && !value.trim().equals(""))
			sb.append("<li><b>"+propertyName+":</b> "+value+"</li>");
	}

}
