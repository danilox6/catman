package it.unisannio.catman.inbox.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite {

	private static DetailViewUiBinder uiBinder = GWT
			.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {
	}

	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
