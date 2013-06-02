package it.unisannio.catman.screens.inbox.client;

import java.util.Set;

import javax.validation.ConstraintViolation;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.domain.equipment.client.WarehouseProxy;
import it.unisannio.catman.domain.equipment.client.WarehouseRequest;

import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class WarehouseEditor implements Editor<WarehouseProxy> {

	private static WarehouseEditorUiBinder uiBinder = GWT
			.create(WarehouseEditorUiBinder.class);

	interface WarehouseEditorUiBinder extends UiBinder<Widget, WarehouseEditor> {
	}
	
	interface Driver extends RequestFactoryEditorDriver<WarehouseProxy, WarehouseEditor> {}
	
	@UiField
	TextBox nameEditor;
	
	@UiField
	Button okButton;
	
	private Driver driver;
	private WarehouseProxy object;
	private WarehouseRequest context;
	
	private final Modal modal;
	
	public WarehouseEditor(WarehouseProxy object, WarehouseRequest context) {
		modal = (Modal) uiBinder.createAndBindUi(this);
		
		this.object = object;
		this.context = context;
		
		driver = GWT.create(Driver.class);
		driver.initialize(App.getInstance().getDataStore(), this);
		
		driver.edit(object, context);
		modal.show();
	}
	
	@UiHandler("okButton")
	void save(ClickEvent e) {
		driver.flush();
		context.persist().using(object).fire(new Receiver<Void> () {

			@Override
			public void onSuccess(Void response) {
				modal.hide();
			}
			
			@Override
			public void onFailure(ServerFailure error) {
				Window.alert("An error occurred during the operation. Please retry.");
				GWT.log("Error during editor save: " + error.getStackTraceString());
			}
			
			@Override
			public void onConstraintViolation(
					Set<ConstraintViolation<?>> violations) {
				for(ConstraintViolation<?> v : violations) {
					Alert a = new Alert(v.getMessage(), AlertType.ERROR, false);
					a.setAnimation(true);
					modal.insert(a, 0);
				}
			}
		});
		
		
		
	}

}
