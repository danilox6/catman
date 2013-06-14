package it.unisannio.catman.screens.worker.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.common.client.cell.InteractiveCellAdapter;
import it.unisannio.catman.domain.humanresources.client.PieceworkProxy;

public class PieceworkCellAdapter extends AbstractCellAdapter<PieceworkProxy> {
	
	public static final String HIRE_COMMAND = "hire";

	@Override
	public SafeHtml getNorth(PieceworkProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getQualification().getName()).toSafeHtml();
	}

	@Override
	public SafeHtml getWest(PieceworkProxy object) {
		return Icon.CHEF.toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(PieceworkProxy object) {
		return new SafeHtmlBuilder()
			.appendEscaped(object.isFreelance() ? "Freelance" : "Full time")
			.appendHtmlConstant(" &bull; ")
			.append(object.getPay())
			.appendHtmlConstant(" &euro;")
			.appendEscaped(object.isFreelance()? "/day" : "/month")
			.toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(PieceworkProxy object) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<a href=\"javascript:;\" " + InteractiveCellAdapter.getCommandAttribute(HIRE_COMMAND) + ">Hire</a>");
		
		return sb.toSafeHtml();
	}
}
