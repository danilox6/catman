package it.unisannio.catman.screens.worker.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

public class WorkerCellAdapter extends AbstractCellAdapter<WorkerProxy>{

	@Override
	public SafeHtml getNorth(WorkerProxy object) {
		return new SafeHtmlBuilder().appendEscaped("John Phantom").toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(WorkerProxy object) {
		return new SafeHtmlBuilder().appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px'  />").toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(WorkerProxy object) {
		return new SafeHtmlBuilder().appendEscaped("Professional test user").toSafeHtml();
	}
}
