package it.unisannio.catman.screens.inbox.client;

import java.util.LinkedList;
import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.cell.CellAdapter;
import it.unisannio.catman.domain.workflow.Dossier;
import it.unisannio.catman.domain.workflow.client.DossierProxy;
import it.unisannio.catman.screens.inbox.client.widget.DossierCellAdapter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements Inbox.Master.View {
	interface Presenter {}

	private static MasterViewUiBinder uiBinder = GWT
			.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {
	}
	
	// Solo per i test
	private static class DossierProxyMock implements DossierProxy {}
	
	@UiField SimplePanel northPanel;
	@UiField SimplePanel southPanel;
	@UiField SimplePanel centerPanel;
	//@UiField MasterItemListPanel masterItemList;

	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		CellList<DossierProxy> cellList = new CellList<DossierProxy>(new MasterCell<DossierProxy>(new DossierCellAdapter()));
		
		List<DossierProxy> values = new LinkedList<DossierProxy>();
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());
		values.add(new DossierProxyMock());
		
		cellList.setRowCount(values.size(), true);

	    // Push the data into the widget.
	    cellList.setRowData(0, values);
		centerPanel.add(cellList);

		/*
		masterItemList.setHeight((Window.getClientHeight() - 24 - 24)+"px");		//FIXME Hardcoded size
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				int height = event.getHeight();
				masterItemList.setHeight((height - northPanel.getOffsetHeight() - southPanel.getOffsetHeight()) + "px");
				//Window.alert(height + " -n:"+northPanel.getOffsetHeight()+ " -s:"+southPanel.getOffsetHeight());
			}
		});*/
	}

}
