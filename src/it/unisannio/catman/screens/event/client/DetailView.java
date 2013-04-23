package it.unisannio.catman.screens.event.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.DetailItemListPanel;
import it.unisannio.catman.common.client.widget.DetailSectionCellList;
import it.unisannio.catman.domain.workflow.client.DocumentProxy;
import it.unisannio.catman.screens.event.client.widget.DetailHeadWidget;
import it.unisannio.catman.screens.event.client.widget.DocumentCellAdapter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
		
		/*
		DetailSectionWidget sellsSection = new DetailSectionWidget("Vendite");
		DetailSectionWidget logisticSection = new DetailSectionWidget("Logistica");
		detailItemList.add(sellsSection);
		detailItemList.add(logisticSection);
		
		sellsSection.add(new DocumentDetailItemWidget("/prova.jpg","Documento","Lorem ipsum dolor sit amet"));
		sellsSection.add(new DocumentDetailItemWidget("/prova.jpg","Documento","Lorem ipsum dolor sit amet"));
		sellsSection.add(new DocumentDetailItemWidget("/prova.jpg","Documento","Lorem ipsum dolor sit amet"));
		
		logisticSection.add(new DocumentDetailItemWidget("/prova.jpg","Documento","Lorem ipsum dolor sit amet"));
		logisticSection.add(new DocumentDetailItemWidget("/prova.jpg","Documento","Lorem ipsum dolor sit amet"));
		logisticSection.add(new DocumentDetailItemWidget("/prova.jpg","Documento","Lorem ipsum dolor sit amet"));
		*/
		DetailSectionCellList<DocumentProxy> sellsSection = new DetailSectionCellList<DocumentProxy>("Vendite", new MasterCell<DocumentProxy>(new DocumentCellAdapter()));
		detailItemList.add(sellsSection);
		
		ListDataProvider<DocumentProxy> sellsDataProvider = new ListDataProvider<DocumentProxy>();
		sellsDataProvider.addDataDisplay(sellsSection);
		
		List<DocumentProxy> sellsValues = sellsDataProvider.getList();
		sellsValues.add(new DocumentProxyMock());
		sellsValues.add(new DocumentProxyMock());
		sellsValues.add(new DocumentProxyMock());
		sellsValues.add(new DocumentProxyMock());
		
		DetailSectionCellList<DocumentProxy> logisticSection = new DetailSectionCellList<DocumentProxy>("Logistica", new MasterCell<DocumentProxy>(new DocumentCellAdapter()));
		detailItemList.add(logisticSection);
		
		ListDataProvider<DocumentProxy> logisticDataProvider = new ListDataProvider<DocumentProxy>();
		logisticDataProvider.addDataDisplay(logisticSection);
		
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
