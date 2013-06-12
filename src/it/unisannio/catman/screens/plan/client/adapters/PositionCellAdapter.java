package it.unisannio.catman.screens.plan.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.planning.client.PositionProxy;


public class PositionCellAdapter extends AbstractCellAdapter<PositionProxy> {
	@Override
	public SafeHtml getNorth(PositionProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getQualification().getName()).toSafeHtml();
	}

	@Override
	public SafeHtml getEast(PositionProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getQuantityFilled() + "/" + object.getQuantity()).toSafeHtml();
	}
}
