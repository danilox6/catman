package it.unisannio.catman.screens.materialpicker.client.adapters;

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
	
	public static final String ADD_BUTTON_ATTIBUTE = "add-button";
	public static final String REMOVE_BUTTON_ATTIBUTE = "remove-button";
	public static final String SPINNER_ATTIBUTE = "material-spinner";
	
	private ProcurementProxy procurement;
	
	@Override
	public SafeHtml getWest(SupplyProxy object) {
		return (object instanceof WarehouseProxy? Icon.PACKAGE.toSafeHtml(): Icon.SHOPPING_CART.toSafeHtml()); 
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
		return new SafeHtmlBuilder().appendEscaped(object.getQuantity()+" available - Assigned: "+ getAlreadyFilledBy(object)).toSafeHtml();
	}
	
	
	@Override
	public SafeHtml getEast(SupplyProxy object) {
		
		int max = Math.max(Math.min(object.getQuantity(),procurement.getQuantity()-procurement.getQuantityFilled()), getAlreadyFilledBy(object)); 
//		return new SafeHtmlBuilder().appendHtmlConstant("<span> <input "+SPINNER_ATTIBUTE+"='true'  type='number' min='0' max='"+max+"' step='1' value ='"+value+"' style='width:35px;'/>" +
//				"<button "+MOVE_BUTTON_ATTIBUTE+"='true' type='button'>Move</button></span>").toSafeHtml();
		
		
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<span> <input "+SPINNER_ATTIBUTE+"='true'  value ='0'  type='number' min='0' max='"+max+"' step='1' style='width:35px; text-align:right;'/>" );
		sb.appendHtmlConstant("<a href=\"javascript:;\" " + ADD_BUTTON_ATTIBUTE + "='true'>Assign</a>");
		sb.appendHtmlConstant("&#8208;"); 
		sb.appendHtmlConstant("<a href=\"javascript:;\" " + REMOVE_BUTTON_ATTIBUTE + "='true'>Unassign</a>");
		return sb.toSafeHtml();
	}
	
	public void setProcurement(ProcurementProxy procurement) {
		this.procurement = procurement;
	}
	
	
	private int getAlreadyFilledBy(SupplyProxy supply){
		int count = 0;
		for(SourceProxy source : procurement.getSources())
			if(source.getSupply().equals(supply))
				count += source.getQuantity();
		return count;
	}
}
