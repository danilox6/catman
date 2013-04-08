package it.unisannio.catman.common.client;

import java.util.Iterator;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class Utilities {

	public static Widget getFirstChild(Widget parent) {
		if (parent instanceof HasWidgets) {
			Iterator<Widget> iter = ((HasWidgets) parent).iterator();
			return (iter != null && iter.hasNext()) ? iter.next() : null;
		}
		return null;
	}

}
