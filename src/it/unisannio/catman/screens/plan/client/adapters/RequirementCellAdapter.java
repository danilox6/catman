package it.unisannio.catman.screens.plan.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.InteractiveCellAdapter;
import it.unisannio.catman.domain.planning.client.RequirementProxy;

public class RequirementCellAdapter extends InteractiveCellAdapter<RequirementProxy>{
	
	@Override
	public SafeHtml getNorth(RequirementProxy object) {
		return getEditLink(object.getDescription());
	}

	@Override
	public SafeHtml getEast(RequirementProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getQuantityFilled() + "/" + object.getQuantity()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(RequirementProxy object) {
		return getSimpleSelectionCheckBox(object);
	}
}
