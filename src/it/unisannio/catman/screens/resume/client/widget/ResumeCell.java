package it.unisannio.catman.screens.resume.client.widget;

import it.unisannio.catman.domain.humanresources.client.ResumeProxy;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class ResumeCell extends AbstractCell<ResumeProxy>{

	@Override
	public void render(Context context, ResumeProxy value, SafeHtmlBuilder sb) {
		sb.appendEscaped("John worked for many years in the field of software testing: he started working in the industry as mock object, but soon his qualities led him to be promoted by his empoyers to being a test user. Since then, he participated in more than 10'000  unit tests, being one of the most prolific dummy users of the history");
	}
}
