package it.unisannio.catman.screens.event.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.DetailItemListPanel;
import it.unisannio.catman.common.client.widget.DetailSectionWidget;
import it.unisannio.catman.domain.workflow.client.DocumentProxy;
import it.unisannio.catman.screens.event.client.widget.DetailHeadWidget;
import it.unisannio.catman.screens.event.client.widget.DocumentCellAdapter;

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

public class DetailView extends Composite implements Event.Detail.View {

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
		
		northPanel.add(new DetailHeadWidget("Nome evento"));
		
		DetailSectionWidget sellsSection = new DetailSectionWidget("Vendite");
		
		CellList<DocumentProxy> sellsCellList = new CellList<DocumentProxy>(new MasterCell<DocumentProxy>(new DocumentCellAdapter()));
		
		sellsSection.add(sellsCellList);
		detailItemList.add(sellsSection);
		
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
		detailItemList.add(logisticSection);
		
		ListDataProvider<DocumentProxy> logisticDataProvider = new ListDataProvider<DocumentProxy>();
		logisticDataProvider.addDataDisplay(logisticCellList);
		
		List<DocumentProxy> logisticValues = logisticDataProvider.getList();
		logisticValues.add(new DocumentProxyMock());
		logisticValues.add(new DocumentProxyMock());
		logisticValues.add(new DocumentProxyMock());
		logisticValues.add(new DocumentProxyMock());
		
		//sellsSection
	}
	
	//FIXME Solo per i test
	private static class DocumentProxyMock implements DocumentProxy {
		
	}

}
