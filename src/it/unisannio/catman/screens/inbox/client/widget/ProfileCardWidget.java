package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProfileCardWidget extends Composite {

	private static ProfileCardWidgetUiBinder uiBinder = GWT
			.create(ProfileCardWidgetUiBinder.class);

	interface ProfileCardWidgetUiBinder extends
			UiBinder<Widget, ProfileCardWidget> {
	}

	public ProfileCardWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
