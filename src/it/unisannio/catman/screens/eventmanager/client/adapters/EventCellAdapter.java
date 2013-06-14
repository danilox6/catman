package it.unisannio.catman.screens.eventmanager.client.adapters;

import java.util.Date;

import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.InteractiveCellAdapter;
import it.unisannio.catman.domain.workflow.client.EventProxy;

public class EventCellAdapter extends InteractiveCellAdapter<EventProxy>{

	@Override
	public SafeHtml getNorth(EventProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getTitle()).toSafeHtml();
	}

	@Override
	public SafeHtml getEast(EventProxy object) {
		return getSimpleSelectionCheckBox(object);
	}

	@Override
	public SafeHtml getSouth(EventProxy object) {
		// TODO Auto-generated method stub
		return super.getSouth(object);
	}

	@Override
	public SafeHtml getWest(EventProxy object) {
		return DateAdapter.adapt(object.getStartDate());
	}

	static class DateAdapter{
		
		private static final int SIZE = 40;
		private static final DateFormat MONTH_F = new DateFormat("MMM");
		private static final DateFormat DAY_F = new DateFormat("dd");
		
		static SafeHtml adapt(Date date){ //FIXME(Michele) solo una prova, bisognerebbe cambiare almeno i colori
			SafeHtmlBuilder sb = new SafeHtmlBuilder();
			sb.appendHtmlConstant("<div style='width:"+SIZE+"px; height:"+SIZE+"px; border: 1px solid #ccc; text-align:center;'>" +
					"<div style='height:35%; background-color:red; font-size:11px; line-height:"+(SIZE*35/100)+"px;'>"+MONTH_F.format(date)+"</div>"+
					"<div style='height:65%; background-color:white; line-height:"+(SIZE*65/100)+"px;'>"+DAY_F.format(date)+"</div></div>");
			return sb.toSafeHtml();
		}
		
		private static class DateFormat extends DateTimeFormat{

			public DateFormat(String pattern) {
				super(pattern);
			}
			
			
			
		}
	}
}
