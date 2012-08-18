package it.unisannio.catman.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class Main implements EntryPoint {

	@Override
	public void onModuleLoad() {
		RootPanel root = RootPanel.get("navigation");
		StringBuffer buf = new StringBuffer("<ul id=\"navigation\">");
		for(Unit u : Application.get().getUnits()) {
			buf
				.append("<li><a href=\"#\" title=\"")
				.append(u.getName())
				.append("\">")
				.append(u.getIcon().getCharacter())
				.append("</a></li>");
		}
		buf.append("</ul>");
		root.add(new HTML(buf.toString()));
	}

}
