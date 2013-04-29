package it.unisannio.catman.screens.materialpicker.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.equipment.client.EmployeeProxy;

public class SupplierCellAdapter extends AbstractCellAdapter<EmployeeProxy>{
	
	@Override
	public SafeHtml getWest(EmployeeProxy d) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />");
		return sb.toSafeHtml();
	}

	@Override
	public SafeHtml getNorth(EmployeeProxy object) {
		return new SafeHtmlBuilder().appendEscaped("Mock").toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(EmployeeProxy d) {
		return new SafeHtmlBuilder().appendEscaped("Lorem ipsum dolor sit amet").toSafeHtml();
	}

	@Override
	public SafeHtml getEast(EmployeeProxy object) {
		return new SafeHtmlBuilder().appendHtmlConstant("<input style=\"width:20px;\"/>").toSafeHtml();
	}
}
