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
		return new SafeHtmlBuilder().append(object.getPrice()).appendHtmlConstant("&euro;").toSafeHtml(); 
	}
	
	@Override
	public SafeHtml getWest(OfferProxy object) {
		return Icon.BASKET.toSafeHtml();
	}
	

}
