package it.unisannio.catman.common.client.ui;

import it.unisannio.catman.common.client.App;

import java.util.Set;

import javax.validation.ConstraintViolation;


import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.ModalFooter;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.github.gwtbootstrap.client.ui.constants.BackdropType;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public abstract class DataEditor<E extends EntityProxy, R extends RequestContext, D extends DataEditor<E, R, D> & Editor<E>> extends Composite implements Editor<E>, HasValueChangeHandlers<E> {

	Modal modal;
	SimplePanel formContainer;
	
	Form form;
	
	private E object;
	private R context; 
	private Class<E> clazz;
	private RequestFactoryEditorDriver<E,?> driver;
	
	private boolean isNew = false;
	

	public DataEditor(R ctx, Class<E> clazz) {
		this.context = ctx;
		this.clazz = clazz;
		
		modal = new Modal();
		modal.setAnimation(true);
		modal.setDynamicSafe(true);
		modal.setBackdrop(BackdropType.STATIC);
		modal.setCloseVisible(true);
		modal.setKeyboard(true);
		
		formContainer = new SimplePanel();
		modal.add(formContainer);
		
		ModalFooter footer = new ModalFooter();
		modal.add(footer);
		
		Button save = new Button("Save");
		save.setType(ButtonType.PRIMARY);
		save.setIcon(IconType.OK);
		footer.add(save);
		save.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				driver.flush();
				save(context, object);
				context.fire(new Receiver<Void> () {

					@Override
					public void onSuccess(Void response) {
						modal.hide();
						fireChangeEvent(object);
					}
					
					@Override
					public void onFailure(ServerFailure error) {
						Window.alert("An error occurred during the operation. Please retry.");
						GWT.log(error.getExceptionType());
						GWT.log(error.getMessage());
						GWT.log("Error during editor save: " + error.getStackTraceString());
					}
					
					@Override
					public void onConstraintViolation(
							Set<ConstraintViolation<?>> violations) {
						for(ConstraintViolation<?> v : violations) {
							alert(v.getMessage(), AlertType.ERROR);
						}
					}
				});
				
			}
			
		});
	}
	
	protected void fireChangeEvent(E value) {
		ValueChangeEvent.fire(DataEditor.this, value);
	}
	
	protected Alert alert(String message, AlertType alert) {
		Alert a = new Alert(message, alert, false);
		a.setAnimation(true);
		modal.insert(a, 0);
		
		return a;
	}
	
	public void open() {
		E object = context.create(clazz);
		isNew = true;
		open(object);
	}
	
	public void open(E object) {
		this.driver = init();
		this.object = object;
		driver.edit(object, context);
		modal.show();
	}

	public void setTitle(String title) {
		modal.setTitle(title);
	}
	
	@UiChild(tagname="field")
	public void addToForm(ControlGroup cg) {
		form.add(cg);
	}
	
	protected boolean isNew() {
		return isNew;
	}
	
	protected void setForm(IsWidget w) {
		formContainer.setWidget(w);		
	}
	
	
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<E> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}
	
	protected abstract void save(R context, E entity);
	
	protected abstract RequestFactoryEditorDriver<E,?> init();	
}
