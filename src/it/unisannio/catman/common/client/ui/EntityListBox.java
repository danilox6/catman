package it.unisannio.catman.common.client.ui;

import java.util.List;

import it.unisannio.catman.common.client.HasFindAll;

import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.text.shared.Renderer;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class EntityListBox<E extends EntityProxy, R extends RequestContext & HasFindAll<E>> extends ValueListBox<E> {
	
	R requestContext;

	public EntityListBox(R requestContext, Renderer<E> renderer) {
		super(renderer);
		this.requestContext = requestContext;
	}
	
	@Override
	protected void onLoad() {
		
		super.onLoad();
		setEnabled(false);
		requestContext.findAll().fire(new Receiver<List<E>> () {

			@Override
			public void onSuccess(List<E> response) {
				setEnabled(true);
				setAcceptableValues(response);
			}
			
			@Override
			public void onFailure(ServerFailure error) {
				GWT.log("Error loading entity list!");
				super.onFailure(error);
			}
		});
		
	}
}
