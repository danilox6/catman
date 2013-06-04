package it.unisannio.catman.screens.event.client.adapters;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.documents.client.DocumentProxy;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class DocumentDetailAdapter extends AbstractCellAdapter<DocumentProxy>{

	@Override
	public SafeHtml getNorth(DocumentProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.toString()).toSafeHtml();
	}

}
