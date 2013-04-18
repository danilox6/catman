package it.unisannio.catman.common.client.cell;


import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;

public class BaseListItemCell extends AbstractCell<ListItemCellData> {
	
	interface BaseMasterItemUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder safeHtmlBuilder, ListItemCellData cellData);
		void onBrowserEvent(BaseListItemCell cell, NativeEvent event, Element parent, ListItemCellData n);
	}
	
	private static BaseMasterItemUiRenderer renderer = GWT.create(BaseMasterItemUiRenderer.class);
	
	public BaseListItemCell() {
		super("click");
	}

	@Override
	public void render(Context context,	ListItemCellData value, SafeHtmlBuilder sb) {
		renderer.render(sb, value);
	}

	@Override
	public void onBrowserEvent(com.google.gwt.cell.client.Cell.Context context,	Element parent, ListItemCellData value, NativeEvent event, ValueUpdater<ListItemCellData> valueUpdater) {
		renderer.onBrowserEvent(this, event, parent, value);
	}
	
	/*
	@UiHandler({"mainDiv"})
	void onDivGotPressed(ClickEvent event, Element parent, DummyCellInfo value) {
	  Window.alert("was pressed!");
	}*/

}
