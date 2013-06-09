package it.unisannio.catman.screens.warehouse.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.SelectableCellAdapter;
import it.unisannio.catman.domain.equipment.client.StockProxy;

public class StockMasterAdapter extends SelectableCellAdapter<StockProxy>{

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
		// FIXME Icona
		return new SafeHtmlBuilder().appendHtmlConstant("<span class='"+DATA_LIST_ICON_CLASS+"'>"+Icon.FORKLIFT+"</span>").toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(StockProxy object) {
		return getSimpleSelectionCheckBox(object);
	}
}
