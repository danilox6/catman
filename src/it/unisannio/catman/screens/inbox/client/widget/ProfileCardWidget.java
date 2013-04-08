package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ProfileCardWidget extends Composite {

	private static ProfileCardWidgetUiBinder uiBinder = GWT.create(ProfileCardWidgetUiBinder.class);

	interface ProfileCardWidgetUiBinder extends	UiBinder<Widget, ProfileCardWidget> {}

	@UiField protected Image image;
	@UiField protected Label nameLabel;
	@UiField protected Label roleLabel;

	public ProfileCardWidget() {
		this("/prova.jpg","John Smith","Waiter");
	}

	public ProfileCardWidget(String imageUrl, String name, String role){
		initWidget(uiBinder.createAndBindUi(this));
		setImage(imageUrl);
		setName(name);
		setRole(role);
	}
	
	public void setImage(String imageUrl){
		image.setUrl(imageUrl);
	}
	
	public void setName(String name){
		nameLabel.setText(name);
	}
	
	public void setRole(String role){
		roleLabel.setText(role);
	}
	
	
}
