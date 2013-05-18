package it.unisannio.catman.screens.event.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractMasterView;
import it.unisannio.catman.common.client.widget.TitleHeadWidget;
import it.unisannio.catman.domain.documents.client.DocumentProxy;
import it.unisannio.catman.screens.event.client.widget.DocumentCellAdapter;
import it.unisannio.catman.screens.event.client.widget.MasterBottomBarWidget;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.ListDataProvider;

public class MasterView extends AbstractMasterView implements Event.Master.View {
	interface Presenter {}

	public MasterView() {
		
		northPanel.add(new TitleHeadWidget("Nome evento"));
		
		//FIXME Proxy adatto
		CellList<DocumentProxy> cellList = new CellList<DocumentProxy>(new MasterCell<DocumentProxy>(new DocumentCellAdapter()));
		
		ListDataProvider<DocumentProxy> dataProvider = new ListDataProvider<DocumentProxy>();
		dataProvider.addDataDisplay(cellList);
		
		List<DocumentProxy> values = dataProvider.getList(); //Da javadoc "Get the list that backs this model. Changes to the list will be reflected in the model."
		values.add(new DocumentProxyMock());
		values.add(new DocumentProxyMock());
		values.add(new DocumentProxyMock());
		values.add(new DocumentProxyMock());
		
		cellList.setRowCount(values.size(), true);

		centerScrollPanel.add(cellList);
		
		southPanel.add(new MasterBottomBarWidget());
	}
	
	//FIXME Solo per i test
	private static class DocumentProxyMock implements DocumentProxy {
		
	}

}
