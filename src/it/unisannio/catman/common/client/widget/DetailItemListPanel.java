package it.unisannio.catman.common.client.widget;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DetailItemListPanel extends Composite implements HasWidgets{

	private static DetailItemListPanelUiBinder uiBinder = GWT.create(DetailItemListPanelUiBinder.class);

	interface DetailItemListPanelUiBinder extends UiBinder<Widget, DetailItemListPanel> {}

	@UiField ScrollPanel scrollPanel;
	@UiField VerticalPanel verticalPanel;
	
	public DetailItemListPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				setWidth((event.getWidth() - 320 - 60)+"px"); //FIXME Hardcoded size
			}
		});
	}

	@Override
	public void add(Widget child) {
		verticalPanel.add(child);
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
		return verticalPanel.remove(widget);
	}

}
