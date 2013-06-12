package it.unisannio.catman.screens.worker.client;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.Utilities;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.PieceworkProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.worker.client.Worker.Presenter;
import it.unisannio.catman.screens.worker.client.adapters.ContractCellAdapter;
import it.unisannio.catman.screens.worker.client.adapters.PieceworkCellAdapter;
import it.unisannio.catman.screens.worker.client.queries.ContractsQuery;
import it.unisannio.catman.screens.worker.client.queries.PieceworksQuery;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite implements Worker.View{

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {}
	
	@UiField Heading titleLabel;
	@UiField HTML contactInfoHtml;
	@UiField Paragraph resumeParagraph;
	@UiField Button candidatesButton;
	
	@UiField Fieldset contactInfoFieldset;
	@UiField Fieldset resumeFieldset;
	
	@UiField DataList<ContractProxy> contractList;
	@UiField DataList<PieceworkProxy> pieceworkList;
	
	private QueryDataProvider<ContractProxy> contractProvider = new QueryDataProvider<ContractProxy>();
	private QueryDataProvider<PieceworkProxy> pieceworkProvider = new QueryDataProvider<PieceworkProxy>();

	private WorkerProxy worker;
	
	private Presenter presenter;
	
	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		contractList.setCellAdapter(new ContractCellAdapter());
		contractList.setDataProvider(contractProvider);
		
		pieceworkList.setCellAdapter(new PieceworkCellAdapter());
		pieceworkList.setDataProvider(pieceworkProvider);
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setContractsQuery(ContractsQuery contractsQuery) {
		contractProvider.setQuery(contractsQuery);
	}

	@Override
	public void setPieceworksQuery(PieceworksQuery pieceworksQuery) {
		pieceworkProvider.setQuery(pieceworksQuery);
	}

	@Override
	public void setWorkerProxy(WorkerProxy workerProxy) {
		worker = workerProxy;
		
		titleLabel.setText(worker.getName());
		
		if(workerProxy.isCandidate()){
			candidatesButton.setType(ButtonType.DANGER);
			candidatesButton.setText("Remove from candidates"); //FIXME Rivedere testo
		}else{
			candidatesButton.setType(ButtonType.PRIMARY);
			candidatesButton.setText("Add to candidates"); 
		}
			
		candidatesButton.setVisible(!worker.isWorking());
		
		String contactInfo = Utilities.buildContactInfoHTML(worker);
		if(contactInfo != null){
			contactInfoHtml.setHTML(contactInfo);
			contactInfoFieldset.setVisible(true);
		}
		if(worker.getResume()!=null && !worker.getResume().trim().equals("")){
			resumeParagraph.setText(worker.getResume());
			resumeFieldset.setVisible(true);
		}
		
	}
	
	@UiHandler("candidatesButton")
	void handleCandidatesClick(ClickEvent e){
		presenter.setCandidate(worker, !worker.isCandidate());
	}
	
	@UiHandler("pieceworkList")
	void handleCellCLick(ClickEvent e){
		if(e.getRelativeElement().hasAttribute(PieceworkCellAdapter.HIRE_BUTTON_ATTIBUTE)) //TODO
			Window.alert("//TODO");
	}

}
