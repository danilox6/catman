package it.unisannio.catman.common.client.cell;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiRenderer;

public class MasterCell<T> extends AbstractCell<T> implements HasClickHandlers, HasChangeHandlers {
	
	public static enum Type { STANDALONE, EMBEDDED; }

	private List<ClickHandler> clickHandlers = new ArrayList<ClickHandler>();
	private List<ChangeHandler> changeHandlers = new ArrayList<ChangeHandler>();

	interface MasterCellUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder safeHtmlBuilder, SafeHtml north, SafeHtml south, SafeHtml west, SafeHtml east, SafeHtml overlay, String type);
		void onBrowserEvent(MasterCell<?> cell, NativeEvent event, Element parent, Object n);
		Style getStyle();
	}
	
	interface Style extends CssResource {
		String west();
		String east();
		String north();
		String south();
		String overlay();
		
		String standalone();
		String embedded();
	}

	private static MasterCellUiRenderer renderer = GWT.create(MasterCellUiRenderer.class);

	private CellAdapter<T> adapter;
	private NativeEvent nativeEvent;
	
	private Type type = Type.EMBEDDED;
	
	public MasterCell(){
		super(BrowserEvents.CLICK, BrowserEvents.CHANGE);
	}

	public MasterCell(CellAdapter<T> adapter) { //FIXME Potrebbe non essere necessario
		super(BrowserEvents.CLICK, BrowserEvents.CHANGE);
		this.adapter = adapter;
	}
	
	public void setCellAdapter(CellAdapter<T> adapter) {
		this.adapter = adapter;
	}
	
	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public void render(Context context,	T value, SafeHtmlBuilder sb) {
		Style style = renderer.getStyle();
		SafeHtml north = wrap(adapter != null ? adapter.getNorth(value) : null, style.north());
		SafeHtml south = wrap(adapter != null ? adapter.getSouth(value) : null, style.south());
		SafeHtml east = wrap(adapter != null ? adapter.getEast(value) : null, style.east());
		SafeHtml west = wrap(adapter != null ? adapter.getWest(value) : null, style.west());
		SafeHtml overlay = wrap(adapter != null ? adapter.getOverlay(value) : null, style.overlay());

		renderer.render(sb, north, south, west, east, overlay, type == Type.EMBEDDED ? style.embedded() : style.standalone());
	}

	private SafeHtml wrap(SafeHtml contents, final String region) {
		if(contents != null) {
			contents = new SafeHtmlBuilder()
			.appendHtmlConstant("<div class=\"" + region + "\">")
			.append(contents)
			.appendHtmlConstant("</div>")
			.toSafeHtml();
			return contents;
		}

		return new SafeHtmlBuilder().appendHtmlConstant("").toSafeHtml();
	}

	@Override
	public void onBrowserEvent(Context context, Element parent, T value, NativeEvent event, ValueUpdater<T> valueUpdater) {
		nativeEvent = event;
		renderer.onBrowserEvent(this, event, parent, value);
	}

	@UiHandler({"masterCell"})
	void onValueChanged(ChangeEvent event, Element parent, Object value){
		ChangeEvent changeEvent = new CellChangeEvent(value);
		//changeEvent.setRelativeElement(getTargetElement(parent));
		fireEvent(changeEvent);
	}

	@UiHandler({"masterCell"})
	void onCellClick(ClickEvent event, Element parent, Object value) {
		EventTarget et = nativeEvent.getEventTarget();
		if(!Element.is(et))
			throw new RuntimeException("Event target must be an element!");
		
		Element element = Element.as(et);
		if(element.hasAttribute(SelectableCellAdapter.SELECTOR_ATTIBUTE)) {
			return;
		}
		ClickEvent clickEvent = new CellClickEvent(value); 
		clickEvent.setRelativeElement(element);
		fireEvent(clickEvent);
	}	

	@Override
	public void fireEvent(GwtEvent<?> event) {
		if(event instanceof ClickEvent){
			ClickEvent clickEvent = (ClickEvent) event;
			for(ClickHandler handler : clickHandlers)
				handler.onClick(clickEvent);
		}else if (event instanceof ChangeEvent){
			ChangeEvent changeEvent = (ChangeEvent) event;
			for(ChangeHandler handler : changeHandlers){
				handler.onChange(changeEvent);
			}
		}else
			throw new RuntimeException("Unable to handle "+event.getClass().getName()+" event");
			
	}

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		if(handler == null)
			return null;
		changeHandlers.add(handler);
		final int index = changeHandlers.size()-1;
		return new HandlerRegistration() {
			@Override
			public void removeHandler() {
				changeHandlers.remove(index);
			}
		};
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		if(handler == null)
			return null;
		clickHandlers.add(handler);
		final int index = clickHandlers.size()-1;
		return new HandlerRegistration() {
			@Override
			public void removeHandler() {
				clickHandlers.remove(index);
			}
		};
	}
	
	/**
	 * Rende pubblico il costruttore di ClickEvent, restituisce il T value nel getSource()
	 * ed evita che fallisca l'asserzione sulla consumazione dell'evento da parte dell'Handler
	 *
	 */
	private class CellClickEvent extends ClickEvent{
		private Object value;

		public CellClickEvent(Object value) {
			this.value = value;
		}

		@Override
		public Object getSource() {
			return value;
		}

		public int getClientX() {
			return nativeEvent.getClientX();
		}
		public int getClientY() {
			return nativeEvent.getClientY();
		}
		public int getScreenX() {
			return nativeEvent.getScreenX();
		}
		public int getScreenY() {
			return nativeEvent.getScreenY();
		}
		public int getRelativeY(Element target) {
			return nativeEvent.getClientY() - target.getAbsoluteTop() + target.getScrollTop() +
					target.getOwnerDocument().getScrollTop();
		}
		public int getRelativeX(Element target) {
			return nativeEvent.getClientX() - target.getAbsoluteLeft() + target.getScrollLeft() +
					target.getOwnerDocument().getScrollLeft();
		}

	}
	
	private class CellChangeEvent extends ChangeEvent{
		private Object value;
		
		public CellChangeEvent(Object value) {
			this.value = value;
		}

		@Override
		public Object getSource() {
			return value;
		}
		
	}
}
