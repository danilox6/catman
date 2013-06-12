package it.unisannio.catman.screens.worker.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.PieceworkProxy;

public class PieceworkCellAdapter extends AbstractCellAdapter<PieceworkProxy> {
	
	public static final String HIRE_BUTTON_ATTIBUTE = "hire-button";

	@Override
	public SafeHtml getNorth(PieceworkProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getQualification().getName()).toSafeHtml();
	}

	@Override
	public SafeHtml getWest(PieceworkProxy object) {
		return new SafeHtmlBuilder().appendHtmlConstant("<span class='"+DATA_LIST_ICON_CLASS+"'>"+ Icon.TIE +"</span>").toSafeHtml(); //FIXME icona
	}
	
	@Override
	public SafeHtml getSouth(PieceworkProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.isFreelance()?"Freelance":"Full time").toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(PieceworkProxy object) {
		//FIXME separatore, bottone, stile
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendEscaped(object.getPay() + "€"+(object.isFreelance()?"":"/mo.")); //FIXME controllare €/&euro;
		sb.appendHtmlConstant("<button "+HIRE_BUTTON_ATTIBUTE+"='true type='button>Hire</button>");
		return sb.toSafeHtml();
	}
}
