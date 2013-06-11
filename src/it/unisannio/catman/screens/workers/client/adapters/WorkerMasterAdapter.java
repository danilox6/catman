package it.unisannio.catman.screens.workers.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

public class WorkerMasterAdapter extends AbstractCellAdapter<WorkerProxy>{

	@Override
	public SafeHtml getNorth(WorkerProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getName()).toSafeHtml();
	}
	
}
