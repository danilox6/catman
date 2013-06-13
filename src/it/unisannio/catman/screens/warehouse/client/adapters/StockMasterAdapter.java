package it.unisannio.catman.screens.warehouse.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.InteractiveCellAdapter;
import it.unisannio.catman.domain.equipment.client.StockProxy;

public class StockMasterAdapter extends InteractiveCellAdapter<StockProxy>{

	@Override
	public SafeHtml getNorth(StockProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getMateriel().getName()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(StockProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getQuantity()+" in stock").toSafeHtml();
	}

	@Override
	public SafeHtml getWest(StockProxy object) {
		return Icon.BREAKABLE.toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(StockProxy object) {
		return getSimpleSelectionCheckBox(object);
	}
}
