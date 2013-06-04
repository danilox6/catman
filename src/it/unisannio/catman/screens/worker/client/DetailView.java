package it.unisannio.catman.screens.worker.client;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite {

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {}
	
	@UiField(provided = true) CellWidget<WorkerProxy> workerInfo;
	@UiField Heading titleLabel;
//	@UiField DataList<ContractProxy> dataList;

	public DetailView() {
		workerInfo = new CellWidget<WorkerProxy>(new MasterCell<WorkerProxy>(new AbstractCellAdapter<WorkerProxy>() {
			@Override
			public SafeHtml getNorth(WorkerProxy object) {
				return new SafeHtmlBuilder().appendEscaped(object!=null?object.getName():"").toSafeHtml();
			}
		}));
		initWidget(uiBinder.createAndBindUi(this));
		
	}

}
