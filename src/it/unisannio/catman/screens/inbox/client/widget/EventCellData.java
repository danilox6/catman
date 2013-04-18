package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.AbstractListItemCellData;

public class EventCellData extends AbstractListItemCellData{
	
	private String title;
	private String state;
	private String place;
	
	
	public EventCellData(String title, String state, String place) {
		this.title = title;
		this.state = state;
		this.place = place;
	}

	@Override
	public SafeHtml getLeftDivHTML() {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />");
		return sb.toSafeHtml();
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public SafeHtml getCaptionDivHTML() {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<input type='checkbox'/>");
		sb.appendEscaped(state);
		sb.appendHtmlConstant("&nbsp;-&nbsp;");
		sb.appendEscaped(place);
		return sb.toSafeHtml();
	}

	@Override
	public SafeHtml getRightDivHTML() {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<input type='checkbox'/>");
		return sb.toSafeHtml();
	}

	
	

	


}
