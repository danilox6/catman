package it.unisannio.catman.screens.workers.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite {

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {
	}
	
	@UiField Label titleLabel;
	@UiField(provided = true) CellTree cellTree;

	public DetailView() {
		cellTree = new CellTree(new WorkersTreeModel(), null);
		cellTree.setAnimationEnabled(true);
		initWidget(uiBinder.createAndBindUi(this));
	}

}
