package it.unisannio.catman.common.client.widget;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class MasterItemWidget extends Composite {
	private static final BaseListItemWidgetUIBinder binder = GWT.create(BaseListItemWidgetUIBinder.class);
	interface BaseListItemWidgetUIBinder extends UiBinder<Widget,MasterItemWidget> {}
	
	@UiField protected SimplePanel leftPanel;
	@UiField protected VerticalPanel centerPanel;
	@UiField protected Label titleLabel;
	@UiField protected HorizontalPanel captionPanel;
	@UiField protected HorizontalPanel rightPanel;
	
	public MasterItemWidget(){
		initWidget(binder.createAndBindUi(this));
	}
	
	public void setTitle(String title){
		titleLabel.setText(title);
	}
}
