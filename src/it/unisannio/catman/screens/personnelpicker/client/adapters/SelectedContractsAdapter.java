package it.unisannio.catman.screens.personnelpicker.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;

public class SelectedContractsAdapter extends AbstractCellAdapter<ContractProxy> {
	
	@Override
	public SafeHtml getNorth(ContractProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getPiecework().getWorker().getName()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(ContractProxy d) {
		return Icon.CONTACT.toSafeHtml();
	}

	@Override
	public SafeHtml getEast(ContractProxy object) {
		return new SafeHtmlBuilder().appendHtmlConstant("&#x2714;").toSafeHtml();
	}
	
	

}
