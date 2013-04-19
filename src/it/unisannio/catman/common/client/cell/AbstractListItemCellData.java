package it.unisannio.catman.common.client.cell;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Implements {@link ListItemCellData} providing a base implementation whose methods
 *  doesn't return <code>null</code> if child classes doesn't override them.
 */
public abstract class AbstractListItemCellData implements ListItemCellData {

	@Override
	public SafeHtml getLeftDivHTML() {
		return new SafeHtmlBuilder().toSafeHtml();
	}

	public String getTitle() {
		return "";
	}

	public SafeHtml getCaptionDivHTML() {
		return new SafeHtmlBuilder().toSafeHtml();
	}

	public SafeHtml getRightDivHTML() {
		return new SafeHtmlBuilder().toSafeHtml();
	}

	public SafeHtml getTopRightDivHTML() {
		return new SafeHtmlBuilder().toSafeHtml();
	}
	
}
