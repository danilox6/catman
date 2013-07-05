package it.unisannio.catman.screens.worker.client;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.Utilities;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.EmploymentContractProxy;
import it.unisannio.catman.domain.humanresources.client.FreelanceContractProxy;
import it.unisannio.catman.domain.humanresources.client.PieceworkProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.screens.worker.client.Worker.Presenter;
import it.unisannio.catman.screens.worker.client.adapters.ContractCellAdapter;
import it.unisannio.catman.screens.worker.client.adapters.PieceworkCellAdapter;
import it.unisannio.catman.screens.worker.client.editors.EmploymentContractEditor;
import it.unisannio.catman.screens.worker.client.editors.FreelanceContractEditor;
import it.unisannio.catman.screens.worker.client.queries.ContractsQuery;
import it.unisannio.catman.screens.worker.client.queries.PieceworksQuery;

import com.github.gwtbootstrap.client.ui.AccordionGroup;
import com.github.gwtbootstrap.client.ui.Alert;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Fieldset;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.github.gwtbootstrap.client.ui.constants.AlertType;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
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
	
	@UiField AccordionGroup contractsAccordion;
	@UiField AccordionGroup pieceworkAccordion;
	
	@UiField SimplePanel alertContainer;
	
	private QueryDataProvider<ContractProxy> contractProvider = new QueryDataProvider<ContractProxy>();
	private QueryDataProvider<PieceworkProxy> pieceworkProvider = new QueryDataProvider<PieceworkProxy>();
	private ContractCellAdapter contractAdapter = new ContractCellAdapter();
	private PieceworkCellAdapter pieceworkAdapter = new PieceworkCellAdapter();
	
	
	private WorkerProxy worker;
	
	private Presenter presenter;
	
	private PositionProxy position;
	
	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		contractList.setCellAdapter(contractAdapter);
		contractList.setDataProvider(contractProvider);
		
		pieceworkList.setCellAdapter(pieceworkAdapter);
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
			candidatesButton.setText("Remove from candidates"); 
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
	
	@Override
	public void setPositionProxy(PositionProxy position) {
		this.position = position;
		pieceworkAdapter.setPosition(position);
		contractAdapter.setPosition(position);
		if(position != null) {
			showAlert("You're now viewing this profile to the extent of assigning the worker to an event. You can hire him by creating a new freelance contract, or using and existing employment contract", AlertType.INFO);
		}
	}
	
	@UiHandler("candidatesButton")
	void handleCandidatesClick(ClickEvent e){
		presenter.setCandidate(worker, !worker.isCandidate());
	}
	
	@UiHandler("pieceworkList")
	void handlePieceworkCellClick(ClickEvent e){
		PieceworkProxy piecework = (PieceworkProxy) e.getSource();
		if(position!=null && !piecework.getQualification().equals(position.getQualification()))
			return;
		if(piecework.isFreelance()){
			if(position==null)
				new FreelanceContractEditor(piecework, new ValueChangeHandler<FreelanceContractProxy>() {
					public void onValueChange(ValueChangeEvent<FreelanceContractProxy> event) {
						refreshContracts();
					}
				}).open();
			else
				presenter.assignFreelanceToPosition(piecework);
		}
		else
			new EmploymentContractEditor(piecework, new ValueChangeHandler<EmploymentContractProxy>() {
				
				@Override
				public void onValueChange(ValueChangeEvent<EmploymentContractProxy> event) {
					refreshContracts();
				}
			}).open();
	}
	
	@UiHandler("contractList")
	void handleContractCellClick(ClickEvent e){
		if(position != null && e.getSource() instanceof EmploymentContractProxy){
			if(((ContractProxy)e.getSource()).getPiecework().getQualification().equals(position.getQualification()))
				presenter.assignEmployeeToPosition((EmploymentContractProxy) e.getSource());
		}
	}
	
	@Override
	public void refreshContracts() {
		contractList.reload();
		pieceworkAccordion.hide();
		contractsAccordion.show();
	}

	@Override
	public void showAlert(String message, AlertType type) {
		Alert a = new Alert(message,type,false);
		a.setAnimation(true);
		alertContainer.setWidget(a);
		
	}

}
