package it.unisannio.catman.screens.materialmanager.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.equipment.client.SupplierProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;

@SuppressWarnings("rawtypes") 
public class SupplierCellAdapter extends AbstractCellAdapter<SupplierProxy>{

	@Override
	public SafeHtml getNorth(SupplierProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getName()).toSafeHtml();
	}

	@Override
	public SafeHtml getSouth(SupplierProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getSupplyCount()+" material" +
				(object.getSupplyCount()!=1?"s":"")+" available").toSafeHtml();
	}

	@Override
	public SafeHtml getWest(SupplierProxy object) {
		return (object instanceof WarehouseProxy? Icon.FORKLIFT : Icon.SHOPPING_CART).toSafeHtml();
	}

}
