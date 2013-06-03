package it.unisannio.catman.screens.event.client;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ErrorHandler;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class DetailActivity extends ScreenActivity implements Event.Detail {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {

		final DetailView detailView = new DetailView();
		
		DataStore dataStore = App.getInstance().getDataStore();
		EntityProxyId<EventProxy> entityId = null;			  
		try{
			entityId = dataStore.getProxyId(getIntent().get(0, ""));
		}catch(IllegalArgumentException e){
			ErrorHandler.handle(); 
		}
		if(entityId!=null)
			dataStore.events().find(entityId).fire(new Receiver<EventProxy>() {

				@Override
				public void onSuccess(EventProxy response) {
					detailView.setEventProxy(response);
				}
				
				@Override
				public void onFailure(ServerFailure error) {
					ErrorHandler.handle(error.getMessage()); 
				}
			});		
		
		panel.setWidget(detailView);
	}

}
