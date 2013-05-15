package it.unisannio.catman.common.client.cell;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiRenderer;

public class MasterCell<T> extends AbstractCell<T> implements HasClickHandlers, HasChangeHandlers{

	private List<ClickHandler> clickHandlers = new ArrayList<ClickHandler>();
	private List<ChangeHandler> changeHandlers = new ArrayList<ChangeHandler>();

	interface MasterCellUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder safeHtmlBuilder, SafeHtml north, SafeHtml south, SafeHtml west, SafeHtml east, SafeHtml overlay);
		void onBrowserEvent(MasterCell<?> cell, NativeEvent event, Element parent, Object n);
	}

	private static MasterCellUiRenderer renderer = GWT.create(MasterCellUiRenderer.class);

	private final CellAdapter<T> adapter;
	private NativeEvent nativeEvent;

	public MasterCell(CellAdapter<T> adapter) {
		this(adapter, null);		
	}

	public MasterCell(CellAdapter<T> adapter, ClickHandler clickHandler) {
		super(BrowserEvents.CLICK, BrowserEvents.CHANGE);
		this.adapter = adapter;
		addClickHandler(clickHandler);
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

	@Override
	public void onBrowserEvent(Context context, Element parent, T value, NativeEvent event, ValueUpdater<T> valueUpdater) {
		nativeEvent = event;
		renderer.onBrowserEvent(this, event, parent, value);
	}

	@UiHandler({"masterCell"})
	void onValueChanged(ChangeEvent event, Element parent, Object value){
		ChangeEvent changeEvent = new CustomChangeEvent(value);
		changeEvent.setRelativeElement(getTargetElement(parent));
		fireEvent(changeEvent);
	}

	@UiHandler({"masterCell"})
	void onCellClick(ClickEvent event, Element parent, Object value) {
		ClickEvent clickEvent = new CustomClickEvent(value);
		clickEvent.setRelativeElement(getTargetElement(parent));
		fireEvent(clickEvent);
	}

	private void getClickedElementsWithId(Element parent, TreeSet<ElementWrapper> treeSet, int depth){
		for(int i = 0; i<parent.getChildCount(); i++){
			Element element = (Element) parent.getChild(i);
			if(element.getId() != null && !element.getId().equals("")
					&& isClicked(element))
				treeSet.add(new ElementWrapper(element, depth)); 
			if(element.hasChildNodes())
				getClickedElementsWithId(element, treeSet, depth+1);
		}
	}

	private Element getTargetElement(Element parent){
		if(Element.is(nativeEvent.getEventTarget())){
			Element element = Element.as(nativeEvent.getEventTarget());
			if(element.getId() != null && !element.getId().equals(""))
				return element;
		}
		
		TreeSet<ElementWrapper> treeSet = new TreeSet<ElementWrapper>();
		getClickedElementsWithId(parent, treeSet, 0);
		if(treeSet.isEmpty())
			return parent;
		return treeSet.last().element;
	}

	private boolean isClicked(Element element){
		int x =  nativeEvent.getClientX();
		int y =  nativeEvent.getClientY();

		/*
		Window.alert("x: "+x+"\n" +
				"y: "+y+"\n" +
				"L: "+element.getAbsoluteLeft()+"\n" +
				"R: "+element.getAbsoluteRight()+"\n" +
				"T: "+element.getAbsoluteTop()+"\n" +
				"B: "+element.getAbsoluteBottom());
		 */
		return element.getAbsoluteLeft() < x && x < element.getAbsoluteRight() && 
				element.getAbsoluteTop() < y && y < element.getAbsoluteBottom();
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
	class CustomClickEvent extends ClickEvent{
		private Object value;

		public CustomClickEvent(Object value) {
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
	
	class CustomChangeEvent extends ChangeEvent{
		private Object value;
		
		public CustomChangeEvent(Object value) {
			this.value = value;
		}

		@Override
		public Object getSource() {
			return value;
		}
		
	}
	
	private class ElementWrapper implements Comparable<ElementWrapper>{
		Element element;
		int depth;

		public ElementWrapper(Element element, int depth) {
			this.element = element;
			this.depth = depth;
		}

		@Override
		public int compareTo(ElementWrapper o) {
			return depth - o.depth;
		}

		@Override
		public int hashCode() {
			return depth;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof MasterCell.ElementWrapper) {
				@SuppressWarnings("unchecked")
				ElementWrapper eWrapper = (ElementWrapper) obj;
				return depth == eWrapper.depth;
			}
			return false;
		}
	}
}
