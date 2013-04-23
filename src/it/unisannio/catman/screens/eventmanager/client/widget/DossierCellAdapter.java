package it.unisannio.catman.screens.eventmanager.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.workflow.client.DossierProxy;

public class DossierCellAdapter extends AbstractCellAdapter<DossierProxy> implements SelectionChangeEvent.Handler{

	private SelectionModel<DossierProxy> selectionModel = null;
	
	@Override
	public SafeHtml getWest(DossierProxy d) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />");
		return sb.toSafeHtml();
	}

	@Override
	public SafeHtml getNorth(DossierProxy d) {
		return new SafeHtmlBuilder().appendEscaped("Mock").toSafeHtml();
	}

	@Override
	public SafeHtml getSouth(DossierProxy d) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<input type='checkbox'/>");
		sb.appendEscaped("mock");
		sb.appendHtmlConstant("&nbsp;-&nbsp;");
		sb.appendEscaped("mock");
		return sb.toSafeHtml();
	}

	@Override
	public SafeHtml getEast(DossierProxy d) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		boolean selected = selectionModel != null && selectionModel.isSelected(d);
		sb.appendHtmlConstant("<input type='checkbox'" + (selected?"checked='checked'":"") + "/>");
		return sb.toSafeHtml();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		selectionModel = (SelectionModel<DossierProxy>) event.getSource();
	}

	public void setSelectionModel(SelectionModel<DossierProxy> selectionModel) {
		this.selectionModel = selectionModel;
	}
}
