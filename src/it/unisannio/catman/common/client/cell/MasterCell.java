package it.unisannio.catman.common.client.cell;


import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;

public class MasterCell<T> extends AbstractCell<T> {
	
	interface MasterCellUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder safeHtmlBuilder, SafeHtml north, SafeHtml south, SafeHtml west, SafeHtml east, SafeHtml overlay);
		void onBrowserEvent(MasterCell cell, NativeEvent event, Element parent, Object n);
	}
	
	private static MasterCellUiRenderer renderer = GWT.create(MasterCellUiRenderer.class);
	
	private final CellAdapter<T> adapter;
	
	public MasterCell(final CellAdapter<T> adapter) {
		super(BrowserEvents.CLICK);
		this.adapter = adapter;		
	}

	@Override
	public void render(Context context,	T value, SafeHtmlBuilder sb) {
		SafeHtml north = wrap(adapter.getNorth(value), "north");
		SafeHtml south = wrap(adapter.getSouth(value), "south");
		SafeHtml east = wrap(adapter.getEast(value), "east");
		SafeHtml west = wrap(adapter.getWest(value), "west");
		SafeHtml overlay = wrap(adapter.getOverlay(value), "overlay");

		renderer.render(sb, north, south, west, east, overlay);
	}
	
	private SafeHtml wrap(SafeHtml contents, final String region) {
		if(contents != null) {
			contents = new SafeHtmlBuilder()
				.appendHtmlConstant("<div class=\"man-MasterCell-" + region + "\">")
				.append(contents)
				.appendHtmlConstant("</div>")
				.toSafeHtml();
		}
		
		return contents;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onBrowserEvent(Context context, Element parent, T value, NativeEvent event, ValueUpdater<T> valueUpdater) {
		renderer.onBrowserEvent(this, event, parent, value);
	}
	
	/*
	@UiHandler({"mainDiv"})
	void onDivGotPressed(ClickEvent event, Element parent, DummyCellInfo value) {
	  Window.alert("was pressed!");
	}*/

}
