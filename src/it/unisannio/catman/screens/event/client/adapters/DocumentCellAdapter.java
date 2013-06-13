package it.unisannio.catman.screens.event.client.adapters;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.workflow.client.EventDocumentProxy;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class DocumentCellAdapter extends AbstractCellAdapter<EventDocumentProxy>{

	@Override
	public SafeHtml getNorth(EventDocumentProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getTitle()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(EventDocumentProxy object) {
		return Icon.NOTES.toSafeHtml();
	}

}
