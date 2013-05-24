package it.unisannio.catman.screens.materialpicker.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractDetailView;
import it.unisannio.catman.common.client.widget.DetailSectionWidget;
import it.unisannio.catman.domain.equipment.client.SupplierProxy;
import it.unisannio.catman.screens.materialpicker.client.widget.DetailHeadWidget;
import it.unisannio.catman.screens.materialpicker.client.widget.SupplierCellAdapter;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.ListDataProvider;

public class DetailView extends AbstractDetailView implements MaterialPicker.Detail.View {
	interface Presenter{}
	
	@SuppressWarnings("rawtypes")
	public DetailView() {
		
		northPanel.add(new DetailHeadWidget("Materiale X (3/5)"));
		
		DetailSectionWidget suppliersSection = new DetailSectionWidget("Fornitori");
		
		MasterCell<SupplierProxy> cell = new MasterCell<SupplierProxy>(new SupplierCellAdapter());
		CellList<SupplierProxy> cellList = new CellList<SupplierProxy>(cell);
		
		cell.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				Element element = event.getRelativeElement();
				Window.alert(element.getId() + ": " + element.getPropertyInt("value"));
			}
		});

		suppliersSection.add(cellList);
		centerVerticalPanel.add(suppliersSection);
		
		ListDataProvider<SupplierProxy> suppliersDataProvider = new ListDataProvider<SupplierProxy>();
		suppliersDataProvider.addDataDisplay(cellList);
		
		List<SupplierProxy> suppliers = suppliersDataProvider.getList();

	}
	

}
