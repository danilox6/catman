package it.unisannio.catman.screens.seller.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import it.unisannio.catman.common.client.ScreenActivity;

public class MasterActivity extends ScreenActivity {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		Seller.View view = new MasterView();
		panel.setWidget(view);

		SellerPresenter presenter = new SellerPresenter(view, this, getIntent());
		view.setPresenter(presenter);
	}

}
