package it.unisannio.catman.screens.worker.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;

public class ContractCellAdapter extends AbstractCellAdapter<ContractProxy>{

	@Override
	public SafeHtml getNorth(ContractProxy object) {
		//FIXME la condizione � sull'instanceof adesso
		return new SafeHtmlBuilder().appendEscaped("FIXME" /*object.isOpenEnded()?"Open Ended Contract":"Freelance"*/).toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(ContractProxy object) {
		//FIXME Icone
		return new SafeHtmlBuilder().appendHtmlConstant("<span class='"+DATA_LIST_ICON_CLASS+"'>"+ Icon.TIE +"</span>").toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(ContractProxy object) {
		// FIXME Qualifica esatta
		return new SafeHtmlBuilder().appendEscaped("FIXME!"/*object.getQualification().getName()*/).toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(ContractProxy object) {
		// FIXME Wage esatto
		return new SafeHtmlBuilder().appendEscaped("FIXME" /*object.getWage() 0+(object.isOpenEnded()?"€/mo.":"€")*/).toSafeHtml(); //TODO controllare €/&euro;
	}
	
	@Override
	public SafeHtml getOverlay(ContractProxy object) {
		//TODO aggiungere bottone
		return super.getOverlay(object);
	}

}
