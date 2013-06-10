package it.unisannio.catman.common.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public abstract class LoadingScreenActivity<R extends RequestContext, E extends EntityProxy, V extends IsWidget> extends ScreenActivity {
	
	private R context;
	private V view;
	private String[] with;

	public LoadingScreenActivity(R context, String... with) {
		this.context = context;
		this.with = with;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		this.view = onViewSetup();
		panel.setWidget(view);
		
		try{
			DataStore ds = getDataStore();
			EntityProxyId<E> entityId = ds.getProxyId(getIntent().get(0, ""));
			Request<E> r = context.find(entityId);
			if(with.length > 0)
				r.with(with);
			r.fire(new Receiver<E>() {

				@Override
				public void onSuccess(E response) {
					onLoad(response);
				}

				@Override
				public void onFailure(ServerFailure error) {
					ErrorHandler.handle(error.getMessage()); 
				}
			});
		}catch(IllegalArgumentException e){
			ErrorHandler.handle(); 
		}
		
	}
	
	protected abstract V onViewSetup();
	
	protected V getView() {
		return view;
	}
	
	protected abstract void onLoad(E object);

}
