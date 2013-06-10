package it.unisannio.catman.screens.seller.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.equipment.client.OfferProxy;

public class OfferDetailAdapter extends AbstractCellAdapter<OfferProxy>{

	@Override
	public SafeHtml getNorth(OfferProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getMateriel().getName()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(OfferProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getMateriel().getDescription()).toSafeHtml();
	}
	
	
	@Override
	public SafeHtml getEast(OfferProxy object) {
		//FIXME Icona freccia al posto di '>'?
		return new SafeHtmlBuilder().appendEscaped(object.getPrice()+"€  >").toSafeHtml(); //TODO controllare € /&euro;
	}
	
	@Override
	public SafeHtml getWest(OfferProxy object) {
		// FIXME Icona
		return new SafeHtmlBuilder().appendHtmlConstant("<span class='"+DATA_LIST_ICON_CLASS+"'>"+Icon.SHOPPING_CART+"</span>").toSafeHtml();
	}
	

}
