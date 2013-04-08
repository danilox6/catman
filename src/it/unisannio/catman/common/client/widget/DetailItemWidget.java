package it.unisannio.catman.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class DetailItemWidget extends Composite {

	private static DetailItemWidgetUiBinder uiBinder = GWT.create(DetailItemWidgetUiBinder.class);
	interface DetailItemWidgetUiBinder extends	UiBinder<Widget, DetailItemWidget> {}

	@UiField protected SimplePanel leftPanel;
	@UiField protected VerticalPanel centerPanel;
	@UiField protected HorizontalPanel rightPanel;
	@UiField protected SimplePanel topRightPanel;
	@UiField protected Label titleLabel;
	@UiField protected Label captionLabel;
	@UiField protected Image image;
	
	/**
	 * Da usare se si vogliono cambiare le dimensioni del container dell'immagine 
	 */
	@UiField protected Panel leftPanelContainer;
	
	public DetailItemWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public DetailItemWidget(String imageUrl, String title, String caption){
		this();
		setImage(imageUrl);
		setTitle(title);
		setCaption(caption);
	}

	public void setTitle(String title){
		titleLabel.setText(title);
	}
	
	public void setCaption(String caption){
		captionLabel.setText(caption);
	}
	
	public void setImage(String imageUrl){
		image.setUrl(imageUrl);
	}
}
