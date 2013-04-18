package it.unisannio.catman.screens.inbox.client;

import java.util.LinkedList;
import java.util.List;

import it.unisannio.catman.common.client.cell.BaseListItemCell;
import it.unisannio.catman.common.client.cell.ListItemCellData;
import it.unisannio.catman.screens.inbox.client.widget.EventCellData;

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
	
	@UiField SimplePanel northPanel;
	@UiField SimplePanel southPanel;
	@UiField SimplePanel centerPanel;
	//@UiField MasterItemListPanel masterItemList;

	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		CellList<ListItemCellData> cellList = new CellList<ListItemCellData>(new BaseListItemCell());
		
		List<ListItemCellData> values = new LinkedList<ListItemCellData>();
		values.add(new EventCellData("Nome evento", "completato", "Villa Margherita"));
		values.add(new EventCellData("Nome evento", "completato", "Villa Margherita"));
		values.add(new EventCellData("Nome evento", "completato", "Villa Margherita"));
		values.add(new EventCellData("Nome evento", "completato", "Villa Margherita"));
		cellList.setRowData(values);
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
