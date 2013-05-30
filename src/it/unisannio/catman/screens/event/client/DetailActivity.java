package it.unisannio.catman.screens.event.client;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.ScreenActivity;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class DetailActivity extends ScreenActivity implements Event.Detail {

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {

		final DetailView2 detailView = new DetailView2();
		
		DataStore dataStore = App.getInstance().getDataStore();
		EntityProxyId<EventProxy> entityId = null;			  
		try{
			entityId = dataStore.getProxyId(getIntent().get(0, "1@1@PpLTWIzrraS9Nsz6GfgnalkzeDU=")); //FIXME un token di prova creato a mano
		}catch(IllegalArgumentException e){
			GWT.log("Errore token"); //FIXME
		}
		if(entityId!=null)
			dataStore.events().find(entityId).fire(new Receiver<EventProxy>() {

				@Override
				public void onSuccess(EventProxy response) {
					GWT.log("Retrieved event "+ response.getTitle());
					detailView.setEventProxy(response);
				}
				
				@Override
				public void onFailure(ServerFailure error) {
					GWT.log("Event not found");
				}
			});		
		
		panel.setWidget(detailView);
	}

}
