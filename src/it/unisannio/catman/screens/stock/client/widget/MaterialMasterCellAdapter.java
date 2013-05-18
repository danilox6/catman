package it.unisannio.catman.screens.stock.client.widget;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.cell.SelectorAbstractCellAdapter;
import it.unisannio.catman.domain.equipment.client.MaterialProxy;

public class MaterialMasterCellAdapter extends SelectorAbstractCellAdapter<MaterialProxy>{

	@Override
	public SafeHtml getWest(MaterialProxy object) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />");
		return sb.toSafeHtml();
	}
	
	@Override
	public SafeHtml getNorth(MaterialProxy object) {
		return new SafeHtmlBuilder().appendEscaped("Material x	").toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(MaterialProxy object) {
		return new SafeHtmlBuilder().appendEscaped("33 in stock").toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(MaterialProxy object) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<input type='checkbox'" + (isSelected(object)?"checked='checked'":"") + "/>");
		return sb.toSafeHtml();
	}

}
