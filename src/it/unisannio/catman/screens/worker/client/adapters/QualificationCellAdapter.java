package it.unisannio.catman.screens.worker.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;

public class QualificationCellAdapter extends AbstractCellAdapter<QualificationProxy>{

	@Override
	public SafeHtml getNorth(QualificationProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getName()).toSafeHtml();
	}

	@Override
	public SafeHtml getWest(QualificationProxy object) {
		//FIXME Icone
		return new SafeHtmlBuilder().appendHtmlConstant("<span class='"+DATA_LIST_ICON_CLASS+"'>"+ Icon.TIE +"</span>").toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(QualificationProxy object) {
		// TODO Auto-generated method stub
		return super.getEast(object);
	}
}
