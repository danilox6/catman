package it.unisannio.catman.screens.personnelpicker.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.EmployeeProxy;

public class EmployeCellAdapter extends AbstractCellAdapter<EmployeeProxy>{
	
	@Override
	public SafeHtml getWest(EmployeeProxy d) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />");
		return sb.toSafeHtml();
	}

	@Override
	public SafeHtml getNorth(EmployeeProxy object) {
		return new SafeHtmlBuilder().appendEscaped("Nome, Cognome").toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(EmployeeProxy object) {
		return new SafeHtmlBuilder().appendEscaped("Mock Job").toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(EmployeeProxy object) {
		return new SafeHtmlBuilder().appendEscaped(">").toSafeHtml();
	}

}
