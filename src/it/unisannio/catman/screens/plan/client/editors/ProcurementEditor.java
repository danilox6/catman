package it.unisannio.catman.screens.plan.client.editors;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.ui.DataEditor;
import it.unisannio.catman.common.client.ui.EntityListBox;
import it.unisannio.catman.domain.equipment.client.MaterielProxy;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.planning.client.ProcurementProxy;
import it.unisannio.catman.domain.planning.client.ProcurementRequest;

import com.github.gwtbootstrap.client.ui.IntegerBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

public class ProcurementEditor extends DataEditor<ProcurementProxy, ProcurementRequest, ProcurementEditor> {

	private static ProcurementEditorUiBinder uiBinder = GWT
			.create(ProcurementEditorUiBinder.class);

	interface ProcurementEditorUiBinder extends
			UiBinder<Widget, ProcurementEditor> {
	}
	
	interface Driver extends RequestFactoryEditorDriver<ProcurementProxy, ProcurementEditor> {}
	
	@UiField
	IntegerBox quantityEditor;
	
	@UiField(provided = true)
	EntityListBox<MaterielProxy> materielEditor = new EntityListBox<MaterielProxy>(
			App.getInstance().getDataStore().materiels(),
			new AbstractRenderer<MaterielProxy>() {
				@Override
				public String render(MaterielProxy object) {
					if(object == null)
						return "--";
					return object.getName();
				}
		});
	
	private PlanProxy plan;

	public ProcurementEditor(PlanProxy plan) {
		super(App.getInstance().getDataStore().procurements(), ProcurementProxy.class);
		setForm(uiBinder.createAndBindUi(this));
		setTitle("Add/edit Material Requirement");
		
		this.plan = plan;
	}

	@Override
	protected void save(ProcurementRequest context, ProcurementProxy entity) {
		if(isNew()) {
			entity.setPlan(plan);
			context.persist().using(entity);	
		}
	}

	@Override
	protected RequestFactoryEditorDriver<ProcurementProxy, ?> init() {
		Driver driver = GWT.create(Driver.class);
		driver.initialize(App.getInstance().getDataStore(), this);
		
		return driver;
	}

}