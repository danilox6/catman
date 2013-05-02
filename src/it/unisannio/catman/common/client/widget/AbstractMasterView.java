package it.unisannio.catman.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractMasterView extends Composite {

	private static AbstractMasterViewUiBinder uiBinder = GWT.create(AbstractMasterViewUiBinder.class);

	interface AbstractMasterViewUiBinder extends UiBinder<Widget, AbstractMasterView> {}
	
	protected @UiField SimplePanel northPanel;
	protected @UiField ScrollPanel centerScrollPanel;
	protected @UiField SimplePanel southPanel;

	public AbstractMasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				adaptHeightToClient(event.getHeight());
			}
		});
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		adaptHeightToClient(Window.getClientHeight());
	}

	private void adaptHeightToClient(int clientHeight){
		centerScrollPanel.setHeight((clientHeight - northPanel.getOffsetHeight() - southPanel.getOffsetHeight()) + "px");
	}

}
