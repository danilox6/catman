package it.unisannio.catman.common.client.widget;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.MultiSelectable;
import it.unisannio.catman.common.client.widget.MasterItemListPanel.MultiSelectionChangedEvent.MultiSelectionState;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MasterItemListPanel extends Composite implements HasWidgets, ClickHandler {


	private static MasterItemListPanelUiBinder uiBinder = GWT.create(MasterItemListPanelUiBinder.class);

	interface MasterItemListPanelUiBinder extends UiBinder<Widget, MasterItemListPanel> {}

	@UiField ScrollPanel scrollPanel;
	@UiField VerticalPanel verticalPanel;

	private Set<Widget> multiSelectedWidgets = new HashSet<Widget>();
	private Widget selectedWidget;

	public MasterItemListPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void add(Widget child) {
		FocusPanel wrapper = new FocusPanel();
		wrapper.add(child);
		wrapper.addClickHandler(this);
		verticalPanel.add(wrapper);
	}

	@Override
	public void onClick(ClickEvent event) {
		Object src = event.getSource();
		Widget source;
		if(src instanceof FocusPanel)
			source = ((FocusPanel) src).getWidget();
		else
			source = (Widget) src;

		selectedWidget = source;

		if(selectedWidget instanceof MultiSelectable && ((MultiSelectable)selectedWidget).isSelected()){
			multiSelectedWidgets.add(selectedWidget);
			MultiSelectionState state = MultiSelectionState.SOME_SELECTED;
			if(areAllWidgetSelected())
				state = MultiSelectionState.ALL_SELECTED;
			App.getInstance().getEventBus().fireEvent(new MultiSelectionChangedEvent(multiSelectedWidgets,state));
		}else{
			if(multiSelectedWidgets.remove(selectedWidget)){
				MultiSelectionState state = MultiSelectionState.SOME_SELECTED;
				if(multiSelectedWidgets.isEmpty())
					state = MultiSelectionState.NONE_SELECTED;
				App.getInstance().getEventBus().fireEvent(new MultiSelectionChangedEvent(multiSelectedWidgets,state));
			}
		}



		/*
		Intent in = new Intent("inbox");
		App.goTo(Path.to(in));*/
	}

	public void unselectAll(){
		for(Widget w : multiSelectedWidgets)
			if (w instanceof MultiSelectable)
				((MultiSelectable) w).setSelected(false);
		multiSelectedWidgets.clear();
		App.getInstance().getEventBus().fireEvent(new MultiSelectionChangedEvent(multiSelectedWidgets,MultiSelectionState.NONE_SELECTED));
	}

	public void selectAll(){
		Iterator<Widget> children = iterator();
		while(children.hasNext()){
			Widget widget = children.next();
			if(widget instanceof FocusPanel)
				widget =((FocusPanel)widget).getWidget();
			if(widget instanceof MultiSelectable){
				((MultiSelectable) widget).setSelected(true);
				multiSelectedWidgets.add(widget);
			}
		}
		App.getInstance().getEventBus().fireEvent(new MultiSelectionChangedEvent(multiSelectedWidgets,MultiSelectionState.ALL_SELECTED));
	}

	public void selectOrUnselectAll(){
		if(multiSelectedWidgets.size()==0)
			selectAll();
		else
			unselectAll();
	}

	public boolean areAllWidgetSelected(){
		if(multiSelectedWidgets.size()==0)
			return false;
		Iterator<Widget> children = iterator();
		while(children.hasNext()){
			Widget widget = children.next();
			if(widget instanceof FocusPanel)
				widget =((FocusPanel)widget).getWidget();
			if(widget instanceof MultiSelectable)
				if (!((MultiSelectable) widget).isSelected())
					return false;
		}
		return true;
	}

	public Widget getSelectedWidget(){
		return selectedWidget;
	}

	public Set<Widget> getMultiSelectedWidgets(){
		return multiSelectedWidgets;
	}

	@Override
	public void setHeight(String height) {
		scrollPanel.setHeight(height);
	}

	@Override
	public void setWidth(String width) {
		scrollPanel.setWidth(width);
	}

	@Override
	public void clear() {
		verticalPanel.clear();
	}

	@Override
	public Iterator<Widget> iterator() {
		return verticalPanel.iterator();
	}

	@Override
	public boolean remove(Widget widget) {
		if(selectedWidget.equals(widget))
			selectedWidget = null;
		if(widget instanceof FocusPanel)
			widget =((FocusPanel)widget).getWidget();
		multiSelectedWidgets.remove(widget);
		return verticalPanel.remove(widget);
	}

	public static class MultiSelectionChangedEvent extends GwtEvent<MultiSelectionChangedHandler>{

		public static Type<MultiSelectionChangedHandler> TYPE = new Type<MultiSelectionChangedHandler>();
		public enum MultiSelectionState  {ALL_SELECTED, NONE_SELECTED, SOME_SELECTED };

		private Set<Widget> selected;
		private MultiSelectionState state;

		public MultiSelectionChangedEvent(Set<Widget> selected, MultiSelectionState state ){
			this.selected = selected;
			this.state = state;
		}


		@Override
		public Type<MultiSelectionChangedHandler> getAssociatedType() {
			return TYPE;
		}

		@Override
		protected void dispatch(MultiSelectionChangedHandler handler) {
			handler.onMultiSelectionChanged(this);
		}


		public Set<Widget> getSelectedWidgets() {
			return selected;
		}

		public MultiSelectionState getState() {
			return state;
		}

	}

	public interface MultiSelectionChangedHandler extends EventHandler{
		void onMultiSelectionChanged(MultiSelectionChangedEvent event);
	}
}
