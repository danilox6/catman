package it.unisannio.catman.screens.worker.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

public class WorkerCellAdapter extends AbstractCellAdapter<WorkerProxy>{

	
	@Override
	public SafeHtml getNorth(WorkerProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object!=null?object.getName():"").toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(WorkerProxy object) {
		//FIXME Icona
		return new SafeHtmlBuilder().appendHtmlConstant("<span class='"+DATA_LIST_ICON_CLASS+"'>"+ Icon.CONTACT +"</span>").toSafeHtml();
	}

}
