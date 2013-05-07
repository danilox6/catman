package it.unisannio.catman.screens.materialpicker.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractDetailView;
import it.unisannio.catman.common.client.widget.DetailSectionWidget;
import it.unisannio.catman.domain.equipment.client.SupplierProxy;
import it.unisannio.catman.screens.materialpicker.client.widget.DetailHeadWidget;
import it.unisannio.catman.screens.materialpicker.client.widget.SupplierCellAdapter;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.ListDataProvider;

public class DetailView extends AbstractDetailView implements MaterialPicker.Detail.View {
	interface Presenter{}
	
	public DetailView() {
		
		northPanel.add(new DetailHeadWidget("Materiale X (3/5)"));
		
		DetailSectionWidget suppliersSection = new DetailSectionWidget("Fornitori");
		
		CellList<SupplierProxy> cellList = new CellList<SupplierProxy>(new MasterCell<SupplierProxy>(new SupplierCellAdapter()));

		suppliersSection.add(cellList);
		centerVerticalPanel.add(suppliersSection);
		
		ListDataProvider<SupplierProxy> suppliersDataProvider = new ListDataProvider<SupplierProxy>();
		suppliersDataProvider.addDataDisplay(cellList);
		
		List<SupplierProxy> suppliers = suppliersDataProvider.getList();
		suppliers.add(new SupplierProxyMock());
		suppliers.add(new SupplierProxyMock());
		suppliers.add(new SupplierProxyMock());
		suppliers.add(new SupplierProxyMock());

	}
	
	//FIXME Solo per i test
	private static class SupplierProxyMock implements SupplierProxy {
		
	}

}
