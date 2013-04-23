package it.unisannio.catman.screens.plan.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.screens.plan.client.widget.MasterHeadBarWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionModel;

public class MasterView extends Composite implements Plan.Master.View, ChangeHandler {
	interface Presenter {}

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	@UiField SimplePanel northPanel;
	@UiField SimplePanel southPanel;
	@UiField ScrollPanel centerScrollPanel;
	@UiField ListBox listBox;
	
	private static MasterView instance;
	private ListDataProvider<PlanProxy> dataProvider;
	

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
		
		listBox.addChangeHandler(this);
		DomEvent.fireNativeEvent(Document.get().createChangeEvent(), listBox); //Fa sì che venga sparato il ChangeEvent
		
		PlanCellAdapter cellAdapter = new PlanCellAdapter();
		
		CellList<PlanProxy> cellList = new CellList<PlanProxy>(new MasterCell<PlanProxy>(cellAdapter));
		MultiSelectionModel<PlanProxy> selectionModel = new MultiSelectionModel<PlanProxy>();
		cellList.setSelectionModel(selectionModel, DefaultSelectionEventManager.<PlanProxy>createCheckboxManager());
		
		//FIXME Uno dei due metodi è superfluo, scegliere quello più bello
		cellAdapter.setSelectionModel(selectionModel);
		dataProvider = new ListDataProvider<PlanProxy>();
		dataProvider.addDataDisplay(cellList);
		
		centerScrollPanel.add(cellList);
		
		listBox.addChangeHandler(this);
		DomEvent.fireNativeEvent(Document.get().createChangeEvent(), listBox); //Fa sì che venga sparato il ChangeEvent
	}

	public SimplePanel getNorthPanel() {
		return northPanel;
	}

	public SimplePanel getSouthPanel() {
		return southPanel;
	}

	@Override
	public void onChange(ChangeEvent event) {
		if(event.getSource().equals(listBox) && dataProvider != null){
			List<PlanProxy> masterItemList = dataProvider.getList();
			switch (((MasterHeadBarWidget) event.getSource()).getSelection()){
				case ROLES:
					
					masterItemList.clear();
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					break;
				case MATERIALS:
					masterItemList.clear();
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
					masterItemList.add(new PlanProxyMock());
			
					
					
					break;
			}
		}
	}
	
	//FIXME solo per test
	interface PlanProxy {}
	class PlanProxyMock implements PlanProxy{}
	class PlanCellAdapter extends AbstractCellAdapter<PlanProxy>{
		private SelectionModel<PlanProxy> selectionModel = null;
		
		@Override
		public SafeHtml getWest(PlanProxy d) {
			SafeHtmlBuilder sb = new SafeHtmlBuilder();
			sb.appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />");
			return sb.toSafeHtml();
		}

		@Override
		public SafeHtml getNorth(PlanProxy d) {
			return new SafeHtmlBuilder().appendEscaped("Mock").toSafeHtml();
		}

		@Override
		public SafeHtml getEast(PlanProxy d) {
			SafeHtmlBuilder sb = new SafeHtmlBuilder();
			boolean selected = selectionModel != null && selectionModel.isSelected(d);
			sb.appendHtmlConstant("<input type='checkbox'" + (selected?"checked='checked'":"") + "/>");
			return sb.toSafeHtml();
		}

		public void setSelectionModel(SelectionModel<PlanProxy> selectionModel) {
			this.selectionModel = selectionModel;
		}
	}	

}
