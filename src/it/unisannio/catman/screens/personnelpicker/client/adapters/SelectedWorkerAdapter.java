package it.unisannio.catman.screens.personnelpicker.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

public class SelectedWorkerAdapter extends AbstractCellAdapter<WorkerProxy> {
	
	@Override
	public SafeHtml getNorth(WorkerProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getName()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(WorkerProxy d) {
		return new SafeHtmlBuilder().appendHtmlConstant("<span class='"+DATA_LIST_ICON_CLASS+"'>"+ Icon.CONTACT +"</span>").toSafeHtml();
	}

	@Override
	public SafeHtml getEast(WorkerProxy object) {
		return new SafeHtmlBuilder().appendEscaped("X").toSafeHtml(); //FIXME
	}
	
	

}
