package it.unisannio.catman.common.client.ui;

import java.util.List;

import it.unisannio.catman.common.client.HasFindAll;

import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.text.shared.Renderer;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class EntityListBox<E extends EntityProxy> extends ValueListBox<E> {
	
	private Request<List<E>> request;
	private boolean isLoading = false;
	private boolean extEnabled = true;

 	public <R extends RequestContext & HasFindAll<E>> EntityListBox(R requestContext, Renderer<E> renderer) {
		this(requestContext.findAll(), renderer);
	}
	
	public EntityListBox(Request<List<E>> request, Renderer<E> renderer) {
		super(renderer);
		this.request = request;
	}
	
	public EntityListBox(Renderer<E> renderer) {
		super(renderer);
		super.setEnabled(false);
	}
	 
	@Override
	protected void onLoad() {
		super.onLoad();
		if(request != null && !isLoading) {
			load();
		}		
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<E> handler) {
		return super.addValueChangeHandler(handler);
	}
	
	public void setRequest(Request<List<E>> r) {
		this.request = r;
		load();
	}
	
	private void load() {
		isLoading = true;
		super.setEnabled(false);
		request.fire(new Receiver<List<E>> () {

			@Override
			public void onSuccess(List<E> response) {
				EntityListBox.super.setEnabled(extEnabled);
				setAcceptableValues(response);
				isLoading = false;
			}
			
			@Override
			public void onFailure(ServerFailure error) {
				GWT.log("Error loading entity list!");
				super.onFailure(error);
				isLoading = false;
			}
		});
	}
	
	
	
	@Override
	public void setEnabled(boolean enabled) {
		extEnabled = enabled;
		super.setEnabled(enabled);
	}
	
	public boolean isExtEnabled() {
		return extEnabled;
	}
}
