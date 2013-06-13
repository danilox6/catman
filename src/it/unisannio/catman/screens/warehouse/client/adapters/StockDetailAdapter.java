package it.unisannio.catman.screens.warehouse.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.equipment.client.StockProxy;

public class StockDetailAdapter extends AbstractCellAdapter<StockProxy>{

	@Override
	public SafeHtml getNorth(StockProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getMateriel().getName()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(StockProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getMateriel().getDescription()).toSafeHtml();
	}
	
	
	@Override
	public SafeHtml getEast(StockProxy object) {
		return new SafeHtmlBuilder().append(object.getQuantity()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(StockProxy object) {
		return Icon.BREAKABLE.toSafeHtml();
	}
	

}
