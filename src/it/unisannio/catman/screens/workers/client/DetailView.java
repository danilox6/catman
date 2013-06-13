package it.unisannio.catman.screens.workers.client;

import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.screens.workers.client.Workers.Presenter;

import java.util.List;

import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite implements Workers.View, ClickHandler{

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {
	}
	
	@UiField Heading titleLabel;
	@UiField(provided = true) CellTree cellTree;
	
	private Workers.Presenter presenter;
	
	private WorkersTreeModel treeModel = new WorkersTreeModel();

	public DetailView() {
		cellTree = new CellTree(treeModel, null);
		cellTree.setAnimationEnabled(true);
		initWidget(uiBinder.createAndBindUi(this));
		
		treeModel.addClickHandler(this);
	}
	
	@Override
	public void setQualificationsList(List<QualificationProxy> qualifications) {
		treeModel.setQualifications(qualifications);
	}

	@Override
	public void setWorkersSource(WorkersSource workersSource) {
		titleLabel.setText(workersSource.getName());
		treeModel.setWorkersSource(workersSource);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void onClick(ClickEvent event) {
		presenter.goToWorkerScreen((WorkerProxy) event.getSource());
	}

}
