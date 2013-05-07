package it.unisannio.catman.screens.personnelmanager.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.PersonnelSource;

public class PersonnelSourceCellAdapter extends AbstractCellAdapter<PersonnelSource> {
	
	@Override
	public SafeHtml getWest(PersonnelSource object) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />");
		return sb.toSafeHtml();
	}

	@Override
	public SafeHtml getNorth(PersonnelSource object) {
		return new SafeHtmlBuilder().appendEscaped(object.getName()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(PersonnelSource object) {
		return new SafeHtmlBuilder().appendEscaped("666 people").toSafeHtml();
	}

}
