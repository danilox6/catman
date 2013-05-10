package it.unisannio.catman.screens.event.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.documents.client.DocumentProxy;

public class DocumentCellAdapter extends AbstractCellAdapter<DocumentProxy>{
	
	@Override
	public SafeHtml getWest(DocumentProxy d) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />");
		return sb.toSafeHtml();
	}

	@Override
	public SafeHtml getNorth(DocumentProxy object) {
		return new SafeHtmlBuilder().appendEscaped("Mock").toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(DocumentProxy d) {
		return new SafeHtmlBuilder().appendEscaped("Lorem ipsum dolor sit amet").toSafeHtml();
	}

}
