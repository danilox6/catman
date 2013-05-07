package it.unisannio.catman.screens.personnelpicker.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.JobBoardSource;

public class JobBoardCellTreeAdapter extends AbstractCellAdapter<JobBoardSource>{

	@Override
	public SafeHtml getNorth(JobBoardSource object) {
		return new SafeHtmlBuilder().appendEscaped("Job Board Mock").toSafeHtml();
	}

	@Override
	public SafeHtml getEast(JobBoardSource object) {
		return new SafeHtmlBuilder().appendEscaped(">").toSafeHtml();
	}
}
