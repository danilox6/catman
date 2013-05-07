package it.unisannio.catman.screens.event.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractDetailView;
import it.unisannio.catman.common.client.widget.DetailSectionWidget;
import it.unisannio.catman.domain.workflow.client.DocumentProxy;
import it.unisannio.catman.screens.event.client.widget.DetailHeadWidget;
import it.unisannio.catman.screens.event.client.widget.DocumentCellAdapter;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.ListDataProvider;

public class DetailView extends AbstractDetailView implements Event.Detail.View {
	interface Presenter{}

	public DetailView() {
		
		northPanel.add(new DetailHeadWidget("Nome evento"));
		
		DetailSectionWidget sellsSection = new DetailSectionWidget("Vendite");
		
		CellList<DocumentProxy> sellsCellList = new CellList<DocumentProxy>(new MasterCell<DocumentProxy>(new DocumentCellAdapter()));
		
		sellsSection.add(sellsCellList);
		centerVerticalPanel.add(sellsSection);
		
		ListDataProvider<DocumentProxy> sellsDataProvider = new ListDataProvider<DocumentProxy>();
		sellsDataProvider.addDataDisplay(sellsCellList);
		
		List<DocumentProxy> sellsValues = sellsDataProvider.getList();
		sellsValues.add(new DocumentProxyMock());
		sellsValues.add(new DocumentProxyMock());
		sellsValues.add(new DocumentProxyMock());
		sellsValues.add(new DocumentProxyMock());
		
		DetailSectionWidget logisticSection = new DetailSectionWidget("Logistica");
		CellList<DocumentProxy> logisticCellList = new CellList<DocumentProxy>(new MasterCell<DocumentProxy>(new DocumentCellAdapter()));
		logisticSection.add(logisticCellList);
		centerVerticalPanel.add(logisticSection);
		
		ListDataProvider<DocumentProxy> logisticDataProvider = new ListDataProvider<DocumentProxy>();
		logisticDataProvider.addDataDisplay(logisticCellList);
		
		List<DocumentProxy> logisticValues = logisticDataProvider.getList();
		logisticValues.add(new DocumentProxyMock());
		logisticValues.add(new DocumentProxyMock());
		logisticValues.add(new DocumentProxyMock());
		logisticValues.add(new DocumentProxyMock());
		
	}
	
	//FIXME Solo per i test
	private static class DocumentProxyMock implements DocumentProxy {
		
	}

}
