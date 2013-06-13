package it.unisannio.catman.screens.personnelmanager.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.humanresources.client.WorkersSource.Source;

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
		if(object.getSource() == Source.CANDIDATES)
			return Icon.FAVORITE.toSafeHtml();
		if(object.getSource() == Source.JOB_BOARD)
			return Icon.BRIEFCASE.toSafeHtml();
		
		return Icon.OFFICE_CHAIR.toSafeHtml();
	}

}
