package it.unisannio.catman.screens.personnelpicker.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

public class SelectedWorkerCellAdapter extends AbstractCellAdapter<WorkerProxy> {
	
	@Override
	public SafeHtml getWest(WorkerProxy d) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />");
		return sb.toSafeHtml();
	}

	@Override
	public SafeHtml getNorth(WorkerProxy object) {
		return new SafeHtmlBuilder().appendEscaped("Nome, Cognome").toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(WorkerProxy object) {
		return new SafeHtmlBuilder().appendEscaped("X").toSafeHtml();
	}
	
	

}
