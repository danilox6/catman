package it.unisannio.catman.screens.personnelmanager.client;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.HeadWidget;
import it.unisannio.catman.domain.equipment.client.EmployeeProxy;
import it.unisannio.catman.screens.materialsmanager.client.widget.SupplierCellAdapter;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class MasterView extends Composite implements PersonnelManager.Master.View {
	interface Presenter {}

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	@UiField SimplePanel northPanel;
	@UiField SimplePanel southPanel;
	@UiField ScrollPanel centerScrollPanel;
	
	private static MasterView instance;

	public static MasterView getInstance(){
		if(instance == null)
			instance = new MasterView();
		return instance;
	}
	
	private MasterView() {
		instance = this;
		initWidget(uiBinder.createAndBindUi(this));
		
		centerScrollPanel.setHeight((Window.getClientHeight() - 24 - 24)+"px"); //FIXME Hardcoded size
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				int height = event.getHeight();
				centerScrollPanel.setHeight((height - northPanel.getOffsetHeight() - southPanel.getOffsetHeight()) + "px");
				//Window.alert(height + " -n:"+northPanel.getOffsetHeight()+ " -s:"+southPanel.getOffsetHeight());
			}
		});
		
		northPanel.add(new HeadWidget("Title"));
		
		CellList<EmployeeProxy> cellList = new CellList<EmployeeProxy>(new MasterCell<EmployeeProxy>(new SupplierCellAdapter()));

		ListDataProvider<EmployeeProxy> dataProvider = new ListDataProvider<EmployeeProxy>();
		dataProvider.addDataDisplay(cellList);
		
		List<EmployeeProxy> values = dataProvider.getList();
		values.add(new EmployeeProxyMock());
		values.add(new EmployeeProxyMock());
		values.add(new EmployeeProxyMock());
		values.add(new EmployeeProxyMock());
		
		centerScrollPanel.add(cellList);
		
	}

	public SimplePanel getNorthPanel() {
		return northPanel;
	}

	public SimplePanel getSouthPanel() {
		return southPanel;
	}

	//FIXME Solo per i test
	private static class EmployeeProxyMock implements EmployeeProxy {
	
	}

}
