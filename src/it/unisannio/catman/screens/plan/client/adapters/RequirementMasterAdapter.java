package it.unisannio.catman.screens.plan.client.adapters;

import it.unisannio.catman.common.client.cell.InteractiveCellAdapter;
import it.unisannio.catman.domain.planning.client.RequirementProxy;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class RequirementMasterAdapter extends InteractiveCellAdapter<RequirementProxy>{
	
	@Override
	public SafeHtml getNorth(RequirementProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getDescription()).toSafeHtml();
	}

	@Override
	public SafeHtml getWest(RequirementProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getQuantityFilled() + "/" + object.getQuantity()).toSafeHtml();
	}
	
}
