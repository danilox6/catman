package it.unisannio.catman.screens.worker.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.InteractiveCellAdapter;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.EmploymentContractProxy;

public class ContractCellAdapter extends InteractiveCellAdapter<ContractProxy>{

	@Override
	public SafeHtml getNorth(ContractProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object instanceof EmploymentContractProxy?"Open Ended Contract":"Freelance Contract").toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(ContractProxy object) {
		return Icon.CHEF.toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(ContractProxy object) {

		return new SafeHtmlBuilder()
			.appendEscaped(object.getPiecework().getQualification().getName())
			.appendHtmlConstant(" &bull; ")
			.append(object.getPiecework().getPay())
			.appendHtmlConstant(object instanceof EmploymentContractProxy? "&euro;/month" : "&euro;/day")
			.toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(ContractProxy object) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder(); 
		sb.append(getSimpleSelectionCheckBox(object)); //FIXME Styling
		return sb.toSafeHtml();
	}
	
	@Override
	public SafeHtml getOverlay(ContractProxy object) {
		//TODO aggiungere bottone
		return super.getOverlay(object);
	}

}
