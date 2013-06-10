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
		//FIXME Ci vuole l'icona della casetta? - Icona freccia al posto di '>'?
		return new SafeHtmlBuilder().appendEscaped(object.getQuantity()+" >").toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(StockProxy object) {
		// FIXME Icona
		return new SafeHtmlBuilder().appendHtmlConstant("<span class='"+DATA_LIST_ICON_CLASS+"'>"+Icon.PACKAGE+"</span>").toSafeHtml();
	}
	

}
