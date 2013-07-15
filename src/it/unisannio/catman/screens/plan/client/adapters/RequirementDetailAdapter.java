package it.unisannio.catman.screens.plan.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.InteractiveCellAdapter;
import it.unisannio.catman.domain.planning.client.RequirementProxy;

public class RequirementDetailAdapter extends InteractiveCellAdapter<RequirementProxy>{
	
	@Override
	public SafeHtml getNorth(RequirementProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getDescription()).toSafeHtml();
	}

	@Override
	public SafeHtml getEast(RequirementProxy object) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendEscaped(object.getQuantityFilled() + "/" + object.getQuantity()).toSafeHtml();
		sb.append(getEditLink(" Edit"));
		return sb.toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(RequirementProxy object) {
		return getSimpleSelectionCheckBox(object);
	}
	
	
}
