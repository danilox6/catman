package it.unisannio.catman.screens.worker.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.JobProxy;

public class ContractCellAdapter extends AbstractCellAdapter<JobProxy>{

	@Override
	public SafeHtml getNorth(JobProxy object) {
		return new SafeHtmlBuilder().appendEscaped("Freelance Contract").toSafeHtml();
	}
	@Override
	public SafeHtml getWest(JobProxy object) {
		return new SafeHtmlBuilder().appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />").toSafeHtml();
	}

	@Override
	public SafeHtml getSouth(JobProxy object) {
		return new SafeHtmlBuilder().appendEscaped("Waiter in Ricevimento di Gala").toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(JobProxy object) {
		return new SafeHtmlBuilder().appendEscaped("100€").toSafeHtml();
	}
	
	@Override
	public SafeHtml getOverlay(JobProxy object) {
		return new SafeHtmlBuilder().appendEscaped("X").toSafeHtml();
	}
}
