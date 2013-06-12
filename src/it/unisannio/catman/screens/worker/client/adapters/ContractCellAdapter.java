package it.unisannio.catman.screens.worker.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.SelectableCellAdapter;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.EmploymentContractProxy;

public class ContractCellAdapter extends SelectableCellAdapter<ContractProxy>{

	@Override
	public SafeHtml getNorth(ContractProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object instanceof EmploymentContractProxy?"Open Ended Contract":"Freelance Contract").toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(ContractProxy object) {
		//FIXME Icone
		return new SafeHtmlBuilder().appendHtmlConstant("<span class='"+DATA_LIST_ICON_CLASS+"'>"+ Icon.TIE +"</span>").toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(ContractProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getPiecework().getQualification().getName()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(ContractProxy object) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendEscaped(object.getPiecework().getPay()+(object instanceof EmploymentContractProxy?"€/mo.":"€")); //TODO controllare €/&euro;
		sb.append(getSimpleSelectionCheckBox(object)); //FIXME Styling
		return sb.toSafeHtml();
	}
	
	@Override
	public SafeHtml getOverlay(ContractProxy object) {
		//TODO aggiungere bottone
		return super.getOverlay(object);
	}

}
