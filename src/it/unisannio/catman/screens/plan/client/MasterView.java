package it.unisannio.catman.screens.plan.client;

import it.unisannio.catman.common.client.widget.MasterItemListPanel;
import it.unisannio.catman.screens.plan.client.widget.MasterHeadBarWidget;
import it.unisannio.catman.screens.plan.client.widget.PlanMasterItemWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements Plan.Master.View, ChangeHandler {
	interface Presenter {}

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	@UiField SimplePanel northPanel;
	@UiField SimplePanel southPanel;
	@UiField MasterItemListPanel masterItemList;
	@UiField ListBox listBox;
	
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
		
		listBox.addChangeHandler(this);
		DomEvent.fireNativeEvent(Document.get().createChangeEvent(), listBox); //Fa sì che venga sparato il ChangeEvent
		
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

	@Override
	public void onChange(ChangeEvent event) {
		if(event.getSource().equals(listBox)){
			switch (((MasterHeadBarWidget) event.getSource()).getSelection()){
				case ROLES:
					masterItemList.clear();
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del ruolo"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del ruolo"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del ruolo"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del ruolo"));
					break;
				case MATERIALS:
					masterItemList.clear();
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
					masterItemList.add(new PlanMasterItemWidget("/prova.jpg", "Nome del materiale"));
			
					
					
					break;
			}
		}
	}

}
