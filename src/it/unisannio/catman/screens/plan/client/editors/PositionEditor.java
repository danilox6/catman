package it.unisannio.catman.screens.plan.client.editors;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.ui.DataEditor;
import it.unisannio.catman.common.client.ui.EntityListBox;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.QualificationRequest;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.domain.planning.client.PositionRequest;

import com.github.gwtbootstrap.client.ui.IntegerBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

public class PositionEditor extends DataEditor<PositionProxy, PositionRequest, PositionEditor> {

	private static PositionEditorUiBinder uiBinder = GWT
			.create(PositionEditorUiBinder.class);

	interface PositionEditorUiBinder extends UiBinder<Widget, PositionEditor> {
	}
	
	interface Driver extends RequestFactoryEditorDriver<PositionProxy, PositionEditor> {}
	
	@UiField
	IntegerBox quantityEditor;
	
	@UiField(provided = true)
	EntityListBox<QualificationProxy, QualificationRequest> qualificationEditor = new EntityListBox<QualificationProxy, QualificationRequest>(
			App.getInstance().getDataStore().qualifications(),
			new AbstractRenderer<QualificationProxy>() {
				@Override
				public String render(QualificationProxy object) {
					if(object == null)
						return "--";
					return object.getName();
				}
		});
	
	private PlanProxy plan;

	public PositionEditor(PlanProxy plan) {
		super(App.getInstance().getDataStore().positions(), PositionProxy.class);
		setForm(uiBinder.createAndBindUi(this));
		setTitle("Add/edit Personnel Requirement");
		
		this.plan = plan;
	}

	@Override
	protected void save(PositionRequest context, PositionProxy entity) {
		if(isNew()) {
			entity.setPlan(plan);
			context.persist().using(entity);	
		}
	}

	@Override
	protected RequestFactoryEditorDriver<PositionProxy, ?> init() {
		Driver driver = GWT.create(Driver.class);
		driver.initialize(App.getInstance().getDataStore(), this);
		
		return driver;
	}

}
