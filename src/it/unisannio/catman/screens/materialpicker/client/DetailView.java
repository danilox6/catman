package it.unisannio.catman.screens.materialpicker.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.DetailItemListPanel;
import it.unisannio.catman.common.client.widget.DetailSectionWidget;
import it.unisannio.catman.domain.equipment.client.EmployeeProxy;
import it.unisannio.catman.screens.materialpicker.client.widget.DetailHeadWidget;
import it.unisannio.catman.screens.materialpicker.client.widget.SupplierCellAdapter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class DetailView extends Composite implements MaterialPicker.Detail.View {

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {
	}

	@UiField SimplePanel northPanel;
	@UiField DetailItemListPanel detailItemList;
	@UiField SimplePanel southPanel;


	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));

		detailItemList.setHeight((Window.getClientHeight() - 24 - 0)+"px");	//FIXME Hardcoded size
		detailItemList.setWidth((Window.getClientWidth() - 320 - 60)+"px"); //FIXME Hardcoded size
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				int height = event.getHeight();
				detailItemList.setHeight((height - northPanel.getOffsetHeight() - southPanel.getOffsetHeight()) + "px");
				//Window.alert(height + " -n:"+northPanel.getOffsetHeight()+ " -s:"+southPanel.getOffsetHeight());
			}
		});
		
		northPanel.add(new DetailHeadWidget("Materiale X (3/5)"));
		
		DetailSectionWidget suppliersSection = new DetailSectionWidget("Fornitori");
		
		CellList<EmployeeProxy> cellList = new CellList<EmployeeProxy>(new MasterCell<EmployeeProxy>(new SupplierCellAdapter()));

		suppliersSection.add(cellList);
		detailItemList.add(suppliersSection);
		
		ListDataProvider<EmployeeProxy> suppliersDataProvider = new ListDataProvider<EmployeeProxy>();
		suppliersDataProvider.addDataDisplay(cellList);
		
		List<EmployeeProxy> suppliers = suppliersDataProvider.getList();
		suppliers.add(new SupplierProxyMock());
		suppliers.add(new SupplierProxyMock());
		suppliers.add(new SupplierProxyMock());
		suppliers.add(new SupplierProxyMock());

	}
	
	//FIXME Solo per i test
	private static class SupplierProxyMock implements EmployeeProxy {
		
	}

}
