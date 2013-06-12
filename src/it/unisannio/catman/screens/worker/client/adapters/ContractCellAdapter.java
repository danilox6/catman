package it.unisannio.catman.screens.worker.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;

public class ContractCellAdapter extends AbstractCellAdapter<ContractProxy>{

	@Override
	public SafeHtml getNorth(ContractProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.isOpenEnded()?"Open Ended Contract":"Freelance").toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(ContractProxy object) {
		//FIXME Icone
		return new SafeHtmlBuilder().appendHtmlConstant("<span class='"+DATA_LIST_ICON_CLASS+"'>"+ Icon.TIE +"</span>").toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(ContractProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getQualification().getName()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(ContractProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getWage()+(object.isOpenEnded()?"€/mo.":"€")).toSafeHtml(); //TODO controllare €/&euro;
	}
	
	@Override
	public SafeHtml getOverlay(ContractProxy object) {
		//TODO aggiungere bottone
		return super.getOverlay(object);
	}

}
