package it.unisannio.catman.screens.plan.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;

public class ProcurementCellAdapter extends AbstractCellAdapter<ProcurementProxy> {

	public ProcurementCellAdapter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public SafeHtml getNorth(ProcurementProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getMateriel().getName()).toSafeHtml();
	}

	@Override
	public SafeHtml getEast(ProcurementProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getQuantityFilled() + "/" + object.getQuantity() ).toSafeHtml();
	}

}
