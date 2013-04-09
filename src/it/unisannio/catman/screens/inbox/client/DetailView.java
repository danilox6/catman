package it.unisannio.catman.screens.inbox.client;

import it.unisannio.catman.common.client.widget.DetailItemListPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite implements Inbox.Detail.View {

	private static DetailViewUiBinder uiBinder = GWT
			.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {
	}

	@UiField SimplePanel northPanel;
	@UiField DetailItemListPanel detailItemList;
	@UiField SimplePanel southPanel;


	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));

		detailItemList.setHeight((Window.getClientHeight() - 24 - 0)+"px");	//FIXME Hardcoded size
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				int height = event.getHeight();
				detailItemList.setHeight((height - northPanel.getOffsetHeight() - southPanel.getOffsetHeight()) + "px");
				//Window.alert(height + " -n:"+northPanel.getOffsetHeight()+ " -s:"+southPanel.getOffsetHeight());
			}
		});
	}

}
