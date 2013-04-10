package it.unisannio.catman.screens.event.client;

import it.unisannio.catman.common.client.widget.MasterItemListPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements Event.Master.View {
	interface Presenter {}

	private static MasterViewUiBinder uiBinder = GWT
			.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {
	}
	
	@UiField SimplePanel northPanel;
	@UiField SimplePanel southPanel;
	@UiField MasterItemListPanel masterItemList;

	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		masterItemList.setHeight((Window.getClientHeight() - 24 - 24)+"px");		//FIXME Hardcoded size
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				int height = event.getHeight();
				masterItemList.setHeight((height - northPanel.getOffsetHeight() - southPanel.getOffsetHeight()) + "px");
				//Window.alert(height + " -n:"+northPanel.getOffsetHeight()+ " -s:"+southPanel.getOffsetHeight());
			}
		});
	}

}
