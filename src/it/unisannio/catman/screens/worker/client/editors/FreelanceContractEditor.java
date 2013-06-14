package it.unisannio.catman.screens.worker.client.editors;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.ui.DataEditor;
import it.unisannio.catman.common.client.ui.EntityListBox;
import it.unisannio.catman.domain.humanresources.client.FreelanceContractProxy;
import it.unisannio.catman.domain.humanresources.client.FreelanceContractRequest;
import it.unisannio.catman.domain.humanresources.client.PieceworkProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;

public class FreelanceContractEditor extends DataEditor<FreelanceContractProxy, FreelanceContractRequest, FreelanceContractEditor> {

	private static FreelanceContractEditorUiBinder uiBinder = GWT
			.create(FreelanceContractEditorUiBinder.class);

	interface FreelanceContractEditorUiBinder extends
			UiBinder<Widget, FreelanceContractEditor> {
	}

	interface Driver extends RequestFactoryEditorDriver<FreelanceContractProxy, FreelanceContractEditor> {}
	
	@UiField(provided = true)
	EntityListBox<PositionProxy> positionEditor = new EntityListBox<PositionProxy>(new AbstractRenderer<PositionProxy>() {

		@Override
		public String render(PositionProxy object) {
			if(object == null)
				return "--";
			
			return object.getPlan().getDossier().getTitle();
		}
		
	});
	
	private PieceworkProxy piecework;
	
	public FreelanceContractEditor(PieceworkProxy piecework) {
		super(App.getInstance().getDataStore().freelanceContracts(), FreelanceContractProxy.class);
		setForm(uiBinder.createAndBindUi(this));
		this.piecework = piecework;
		positionEditor.setRequest(App.getInstance().getDataStore().positions().findByPiecework(piecework).with("plan.dossier.title"));
		setTitle("Add/edit contract");
	}


	@Override
	protected void save(FreelanceContractRequest context, FreelanceContractProxy entity) {
		if(isNew()) {
			entity.setPiecework(piecework);
			context.persist().using(entity);
		}
	}

	@Override
	protected RequestFactoryEditorDriver<FreelanceContractProxy, ?> init() {
		Driver driver = GWT.create(Driver.class);
		driver.initialize(App.getInstance().getDataStore(), this);
		
		return driver;
	}
}
