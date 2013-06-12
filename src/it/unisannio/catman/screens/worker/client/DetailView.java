package it.unisannio.catman.screens.worker.client;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.Utilities;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.worker.client.adapters.ContractCellAdapter;
import it.unisannio.catman.screens.worker.client.adapters.WorkerCellAdapter;
import it.unisannio.catman.screens.worker.client.queries.ContractsQuery;
import it.unisannio.catman.screens.worker.client.queries.QualificationsQuery;

import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite implements Worker.View{

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {}
	
	@UiField(provided = true) CellWidget<WorkerProxy> workerInfoCell; //FIXME Forse è meglio usare un altro sistema
	@UiField Heading titleLabel;
	@UiField HTML contactInfoHtml;
	@UiField Paragraph resumeParagraph;
	
	@UiField Fieldset contactInfoFieldset;
	@UiField Fieldset resumeFieldset;
	
	@UiField DataList<ContractProxy> contractList;
	@UiField DataList<QualificationProxy> qualificationList;
	
	private QueryDataProvider<ContractProxy> contractProvider = new QueryDataProvider<ContractProxy>();

	public DetailView() {
		workerInfoCell = new CellWidget<WorkerProxy>(new MasterCell<WorkerProxy>(new WorkerCellAdapter()));
		initWidget(uiBinder.createAndBindUi(this));
		
		contractList.setCellAdapter(new ContractCellAdapter());
		contractList.setDataProvider(contractProvider);
		
	}

	@Override
	public void setContractsQuery(ContractsQuery contractsQuery) {
		//contractProvider.setQuery(contractsQuery);
	}

	@Override
	public void setQualificationsQuery(QualificationsQuery qualificationsQuery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWorkerProxy(WorkerProxy workerProxy) {
		titleLabel.setText(workerProxy.getName());
		
		String contactInfo = Utilities.buildContactInfoHTML(workerProxy);
		if(contactInfo != null){
			contactInfoFieldset.setVisible(true);
			contactInfoHtml.setHTML(contactInfo);
		}
		if(workerProxy.getResume()!=null && !workerProxy.getResume().trim().equals("")){
			resumeFieldset.setVisible(true);
			resumeParagraph.setText(contactInfo);
		}
		
		workerInfoCell.setValue(workerProxy);
	}


}
