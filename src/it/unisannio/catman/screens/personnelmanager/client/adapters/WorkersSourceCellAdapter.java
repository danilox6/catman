package it.unisannio.catman.screens.personnelmanager.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.humanresources.client.WorkersSource.ListType;

public class WorkersSourceCellAdapter extends AbstractCellAdapter<WorkersSource>{

	@Override
	public SafeHtml getNorth(WorkersSource object) {
		return new SafeHtmlBuilder().appendEscaped(object.getName()).toSafeHtml();
	}

	@Override
	public SafeHtml getSouth(WorkersSource object) {
		return new SafeHtmlBuilder().appendEscaped(object.getCount()!=-1?object.getCount() +" people":"").toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(WorkersSource object) {
		//FIXME Icone
		String icon = Icon.CONTACT.toString();
		if(object.getListType() == ListType.CANDIDATES)
			icon = Icon.FAVORITE.toString();
		if(object.getListType() == ListType.JOB_BOARD)
			icon = Icon.BRIEFCASE.toString();
		return new SafeHtmlBuilder().appendHtmlConstant("<span class='"+DATA_LIST_ICON_CLASS+"'>"+ icon +"</span>").toSafeHtml();
	}

}
