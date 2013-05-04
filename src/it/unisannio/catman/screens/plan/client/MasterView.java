package it.unisannio.catman.screens.plan.client;

import java.util.List;

import it.unisannio.catman.common.client.DataProviderSelectionSyncronizer;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.cell.SelectorAbstractCellAdapter;
import it.unisannio.catman.common.client.widget.AbstractMasterView;
import it.unisannio.catman.common.client.widget.SelectAllHandler;
import it.unisannio.catman.screens.plan.client.widget.MasterBottomBarWidget;
import it.unisannio.catman.screens.plan.client.widget.MasterHeadBarWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

public class MasterView extends AbstractMasterView implements Plan.Master.View, ChangeHandler {
	interface Presenter {}

	private ListBox listBox = new MasterHeadBarWidget(); 
	private ListDataProvider<PlanProxy> dataProvider;
	
	public MasterView() {
		
		northPanel.add(listBox); 
		
		PlanCellAdapter cellAdapter = new PlanCellAdapter();
		CellList<PlanProxy> cellList = new CellList<PlanProxy>(new MasterCell<PlanProxy>(cellAdapter));
		MultiSelectionModel<PlanProxy> selectionModel = new MultiSelectionModel<PlanProxy>();
		cellList.setSelectionModel(selectionModel, DefaultSelectionEventManager.<PlanProxy>createCheckboxManager());
		
		cellAdapter.setSelectionModel(selectionModel);
		
		dataProvider = new ListDataProvider<PlanProxy>();
		dataProvider.addDataDisplay(cellList);
		
		DataProviderSelectionSyncronizer.<PlanProxy>sync(selectionModel, dataProvider);
		
		centerScrollPanel.add(cellList);
		
		southPanel.add(new MasterBottomBarWidget<PlanProxy>(new SelectAllHandler<PlanProxy>(selectionModel, dataProvider)));
		
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
					break;
			}
		}
	}
	
	//FIXME solo per test
	public interface PlanProxy {}
	class PlanProxyMock implements PlanProxy{}
	class PlanCellAdapter extends SelectorAbstractCellAdapter<PlanProxy>{
		
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
			sb.appendHtmlConstant("<input type='checkbox'" + (isSelected(d)?"checked='checked'":"") + "/>");
			return sb.toSafeHtml();
		}

	}	

}
