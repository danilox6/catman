package it.unisannio.catman.inbox.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements MasterPresenter.Display {
	interface Presenter {}

	private static MasterViewUiBinder uiBinder = GWT
			.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {
	}

	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
