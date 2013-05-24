package it.unisannio.catman.screens.materialmanager.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.equipment.client.SupplierProxy;

@SuppressWarnings("rawtypes")
public class SupplierCellAdapter extends AbstractCellAdapter<SupplierProxy>{
	
	@Override
	public SafeHtml getWest(SupplierProxy d) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />");
		return sb.toSafeHtml();
	}

	@Override
	public SafeHtml getNorth(SupplierProxy object) {
		return new SafeHtmlBuilder().appendEscaped("Mock").toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(SupplierProxy d) {
		return new SafeHtmlBuilder().appendEscaped("Lorem ipsum dolor sit amet").toSafeHtml();
	}

}
