package it.unisannio.catman.screens.materialmanager.client;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractMasterView;
import it.unisannio.catman.common.client.widget.BaseActionBarWidget;
import it.unisannio.catman.common.client.widget.TitleHeadWidget;
import it.unisannio.catman.domain.equipment.client.SupplierProxy;
import it.unisannio.catman.screens.materialmanager.client.widget.SupplierCellAdapter;

import java.util.List;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.ListDataProvider;

public class MasterView extends AbstractMasterView implements MaterialManager.Master.View {
	interface Presenter {}
	
	public MasterView() {
		
		northPanel.add(new TitleHeadWidget("Sources"));
		
		CellList<SupplierProxy> cellList = new CellList<SupplierProxy>(new MasterCell<SupplierProxy>(new SupplierCellAdapter()));

		ListDataProvider<SupplierProxy> dataProvider = new ListDataProvider<SupplierProxy>();
		dataProvider.addDataDisplay(cellList);
		
		List<SupplierProxy> values = dataProvider.getList();
		values.add(new SupplierProxyMock());
		values.add(new SupplierProxyMock());
		values.add(new SupplierProxyMock());
		values.add(new SupplierProxyMock());
		
		centerScrollPanel.add(cellList);
		
		southPanel.add(new BaseActionBarWidget());
		
	}

	//FIXME Solo per i test
	private static class SupplierProxyMock implements SupplierProxy {
	
	}

}
