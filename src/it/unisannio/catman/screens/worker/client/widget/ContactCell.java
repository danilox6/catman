package it.unisannio.catman.screens.worker.client.widget;

import it.unisannio.catman.domain.contacts.client.ContactProxy;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class ContactCell extends AbstractCell<ContactProxy>{

	@Override
	public void render(Context context, ContactProxy value, SafeHtmlBuilder sb) {
		sb.appendHtmlConstant("<ul>" +
				"<li><b>Mail:</b>&nbsp; john.phantom@examle.com</li>" +
				"<li><b>Phone:</b>&nbsp; +12 34567890</li>" +
				"</ul>");
	}

}
