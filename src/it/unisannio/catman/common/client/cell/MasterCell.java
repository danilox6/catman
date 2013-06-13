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
	public static final String ATTRIBUTE_COMMAND = "data-command";
	public static final String ATTRIBUTE_NOBUBBLE = "data-nobubble";
	
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

	private CellAdapter<? super T> adapter;
	private NativeEvent nativeEvent;
	
	private Type type = Type.EMBEDDED;
	
	public MasterCell(){
		super(BrowserEvents.CLICK, BrowserEvents.CHANGE);
	}
	
	public MasterCell(CellAdapter<? super T> adapter) {
		this();
		setCellAdapter(adapter);
	}
	
	public void setCellAdapter(CellAdapter<? super T> adapter) {
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
		if(element.hasAttribute(ATTRIBUTE_NOBUBBLE)) {
			return;
		}
		
		String command = null;
		if(element.hasAttribute(ATTRIBUTE_COMMAND)) {
			command = element.getAttribute(ATTRIBUTE_COMMAND);
			nativeEvent.preventDefault();
		}
		ClickEvent clickEvent = new CellClickEvent(value, command); 
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
	public static class CellClickEvent extends ClickEvent {
		private Object value;
		private String command;

		private CellClickEvent(Object value, String command) {
			this.value = value;
			this.command = command;
		}

		@Override
		public Object getSource() {
			return value;
		}

		public String getCommand() {
			return command;
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
