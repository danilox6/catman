package it.unisannio.catman.screens.eventmanager.client;

import it.unisannio.catman.common.client.widget.MasterItemListPanel;
import it.unisannio.catman.screens.inbox.client.widget.EventMasterItemWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements EventManager.Master.View {
	interface Presenter {}

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	@UiField SimplePanel northPanel;
	@UiField SimplePanel southPanel;
	@UiField MasterItemListPanel masterItemList;
	
	private static MasterView instance;

	public static MasterView getInstance(){
		if(instance == null)
			instance = new MasterView();
		return instance;
	}
	
	private MasterView() {
		instance = this;
		initWidget(uiBinder.createAndBindUi(this));
		
		masterItemList.setHeight((Window.getClientHeight() - 24 - 24)+"px"); //FIXME Hardcoded size
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				int height = event.getHeight();
				masterItemList.setHeight((height - northPanel.getOffsetHeight() - southPanel.getOffsetHeight()) + "px");
				//Window.alert(height + " -n:"+northPanel.getOffsetHeight()+ " -s:"+southPanel.getOffsetHeight());
			}
		});
		
		
		masterItemList.add(new EventMasterItemWidget("/prova.jpg", "Nome evento", "completato", "Villa Margherita"));
		masterItemList.add(new EventMasterItemWidget("/prova.jpg", "Cena", "completato", "Casa"));
		masterItemList.add(new EventMasterItemWidget("/prova.jpg", "Pranzo", "completato", "Scuola"));
		masterItemList.add(new EventMasterItemWidget("/prova.jpg", "TANTA ROBA !1!11", "completato", "Spotted: Unisannio"));
		masterItemList.add(new EventMasterItemWidget("/prova.jpg", "Bunga Bunga", "completato", "Arcore"));
		masterItemList.add(new EventMasterItemWidget("/prova.jpg", "Qualcosa", "completato", "In un posto"));
		masterItemList.add(new EventMasterItemWidget("/prova.jpg", "Cena speciale", "completato", "Chiesa"));
		masterItemList.add(new EventMasterItemWidget("/prova.jpg", "Pranzo importante", "completato", "Piazza"));
		masterItemList.add(new EventMasterItemWidget("/prova.jpg", "Pranzetto", "completato", "Giardino"));
		masterItemList.add(new EventMasterItemWidget("/prova.jpg", "Giusto un tozzetto di pane e una scodella d'acqua", "completato", "Palazzo Reale"));
		
	}

	public SimplePanel getNorthPanel() {
		return northPanel;
	}

	public SimplePanel getSouthPanel() {
		return southPanel;
	}

	public MasterItemListPanel getMasterItemList() {
		return masterItemList;
	}

}
