package it.unisannio.catman.screens.materialpicker.client.adapters;

import java.util.HashMap;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.equipment.client.OfferProxy;
import it.unisannio.catman.domain.equipment.client.StockProxy;
import it.unisannio.catman.domain.equipment.client.SupplyProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;
import it.unisannio.catman.domain.planning.client.SourceProxy;

public class SupplyCellAdapter extends AbstractCellAdapter<SupplyProxy>{
	
	public static final String MOVE_BUTTON_ATTIBUTE = "hire-button";
	public static final String SPINNER_ATTIBUTE = "material-spinner";
	
	private ProcurementProxy procurement;
	
	private HashMap<SupplyProxy, Integer> quantityFilled = new HashMap<SupplyProxy, Integer>();
	
	public SupplyCellAdapter(HashMap<SupplyProxy, Integer> quantityFilled) {
		 this.quantityFilled = quantityFilled;
	}

	@Override
	public SafeHtml getWest(SupplyProxy object) {
		return (object instanceof WarehouseProxy? Icon.PACKAGE.toSafeHtml(): Icon.SHOPPING_CART.toSafeHtml()); // FIXME Icone
	}

	@Override
	public SafeHtml getNorth(SupplyProxy object) {
		String name;
		if(object instanceof StockProxy)
			name = ((StockProxy) object).getSupplier().getName();
		else
			name = ((OfferProxy) object).getSupplier().getName();
		return new SafeHtmlBuilder().appendEscaped(name).toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(SupplyProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getQuantity()+" available").toSafeHtml();
	}
	
	
	@Override
	public SafeHtml getEast(SupplyProxy object) {
		int value = getAlreadyFilledBy(object);
		int max = value + Math.min(procurement.getQuantity()-procurement.getQuantityFilled(),object.getQuantity()); 
		return new SafeHtmlBuilder().appendHtmlConstant("<span> <input "+SPINNER_ATTIBUTE+"='true'  type='number' min='0' max='"+max+"' step='1' value ='"+value+"' style='width:35px;'/>" +
				"<button "+MOVE_BUTTON_ATTIBUTE+"='true' type='button'>Move</button></span>").toSafeHtml();
	}
	
	public void setProcurement(ProcurementProxy procurement) {
		this.procurement = procurement;
	}
	
	private int getAlreadyFilledBy(SupplyProxy supply){
		int count = 0;
		for(SourceProxy source : procurement.getSources())
			if(source.getSupply().equals(supply))
				count += source.getQuantity();
		quantityFilled.put(supply, count);
		return count;
	}
}
