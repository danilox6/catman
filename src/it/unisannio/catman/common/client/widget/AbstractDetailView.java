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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractDetailView extends Composite {
	
	private static AbstractDetailViewUiBinder uiBinder = GWT.create(AbstractDetailViewUiBinder.class);

	interface AbstractDetailViewUiBinder extends UiBinder<Widget, AbstractDetailView> {}
	
	//FIXME Hardcoded size
	private final int MASTER_WIDTH = 320;
	private final int MENU_WIDTH = 60;
	
	protected @UiField SimplePanel northPanel;
	protected @UiField ScrollPanel centerScrollPanel;
	protected @UiField SimplePanel southPanel;
	protected @UiField VerticalPanel centerVerticalPanel;
	
	public AbstractDetailView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				adaptHeightToClient(event.getHeight());
				adaptWidthToClient(event.getWidth());
			}
		});
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		adaptWidthToClient(Window.getClientWidth());
		adaptHeightToClient(Window.getClientHeight());
	}

	private void adaptHeightToClient(int clientHeight){
		centerScrollPanel.setHeight((clientHeight - northPanel.getOffsetHeight() - southPanel.getOffsetHeight()) + "px");
	}
	
	private void adaptWidthToClient(int clientWidth){
		String width = (clientWidth - MASTER_WIDTH - MENU_WIDTH)+"px";
		northPanel.setWidth(width);
		southPanel.setWidth(width);
		centerScrollPanel.setWidth(width);
	}
}
