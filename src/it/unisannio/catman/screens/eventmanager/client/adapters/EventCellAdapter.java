package it.unisannio.catman.screens.eventmanager.client.adapters;

import java.util.Date;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.SelectableCellAdapter;
import it.unisannio.catman.domain.workflow.client.EventProxy;

public class EventCellAdapter extends SelectableCellAdapter<EventProxy>{

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
		return DateAdapter.adapt(null);
	}

	static class DateAdapter{
		
		private static final int SIZE = 40;
		
		static SafeHtml adapt(Date date){ //FIXME(Michele) solo una prova
			SafeHtmlBuilder sb = new SafeHtmlBuilder();
			sb.appendHtmlConstant("<div style='width:"+SIZE+"px; height:"+SIZE+"px; border: 1px solid #ccc; text-align:center;'>" +
					"<div style='height:35%; background-color:red; font-size:11px; line-height:"+(SIZE*35/100)+"px;'>Mag</div>"+
					"<div style='height:65%; background-color:white; line-height:"+(SIZE*65/100)+"px;'>24</div></div>");
			return sb.toSafeHtml();
		}
	}
}
