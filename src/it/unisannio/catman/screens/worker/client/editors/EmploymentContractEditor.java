package it.unisannio.catman.screens.worker.client.editors;

import java.util.Date;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.ui.DataEditor;
import it.unisannio.catman.domain.humanresources.client.EmploymentContractProxy;
import it.unisannio.catman.domain.humanresources.client.EmploymentContractRequest;
import it.unisannio.catman.domain.humanresources.client.PieceworkProxy;

import com.github.gwtbootstrap.client.ui.event.ShowEvent;
import com.github.gwtbootstrap.client.ui.event.ShowHandler;
import com.github.gwtbootstrap.datepicker.client.ui.DateBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

public class EmploymentContractEditor extends DataEditor<EmploymentContractProxy, EmploymentContractRequest, EmploymentContractEditor>  {

	private static EmploymentContractEditorUiBinder uiBinder = GWT
			.create(EmploymentContractEditorUiBinder.class);

	interface EmploymentContractEditorUiBinder extends
			UiBinder<Widget, EmploymentContractEditor> {
	}

	interface Driver extends RequestFactoryEditorDriver<EmploymentContractProxy, EmploymentContractEditor> {}
	
	@UiField
	DateBox startDateEditor;
	
	@UiField
	DateBox endDateEditor;
	
	private PieceworkProxy piecework;
	
	public EmploymentContractEditor(PieceworkProxy piecework, ValueChangeHandler<EmploymentContractProxy> handler) {
		super(App.getInstance().getDataStore().employmentContracts(), EmploymentContractProxy.class);
		setForm(uiBinder.createAndBindUi(this));
		this.piecework = piecework;
		setTitle("Add/edit contract");
		this.addValueChangeHandler(handler);
		
		startDateEditor.setFormat("dd/mm/yyyy");
		endDateEditor.setFormat("dd/mm/yyyy");
		
		endDateEditor.addShowHandler(new ShowHandler() {
			boolean done = false;
			
			@Override
			public void onShow(ShowEvent showEvent) {
				if(!done && startDateEditor.getValue()!=null){
					done = true;
					Date date = CalendarUtil.copyDate(startDateEditor.getValue());
					CalendarUtil.addDaysToDate(date,1);
					endDateEditor.setValue(date);
					
				}
			}
		});
	}


	@Override
	protected void save(EmploymentContractRequest context, EmploymentContractProxy entity) {
		if(isNew()) {
			entity = context.edit(entity);
			entity.setPiecework(piecework);
			context.persist().using(entity);
		}
	}

	@Override
	protected RequestFactoryEditorDriver<EmploymentContractProxy, ?> init() {
		Driver driver = GWT.create(Driver.class);
		driver.initialize(App.getInstance().getDataStore(), this);
		
		return driver;
	}
}
