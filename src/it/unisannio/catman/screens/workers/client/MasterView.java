package it.unisannio.catman.screens.workers.client;

import java.util.ArrayList;
import java.util.List;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.screens.workers.client.Workers.Presenter;
import it.unisannio.catman.screens.workers.client.adapters.WorkerMasterAdapter;
import it.unisannio.catman.screens.workers.client.queries.WorkersByQualificationQuery;

import com.github.gwtbootstrap.client.ui.ListBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements Workers.View, ChangeHandler{

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	@UiField ListBox listBox;
	@UiField DataList<WorkerProxy> dataList;
	
	private QueryDataProvider<WorkerProxy> dataProvider = new QueryDataProvider<WorkerProxy>();
	
	private WorkersSource workersSource;
	
	private List<QualificationProxy> qualifications = new ArrayList<QualificationProxy>();
	private Workers.Presenter presenter;

	public MasterView() {
		
		initWidget(uiBinder.createAndBindUi(this));
		
		dataList.setDataProvider(dataProvider);
		dataList.setCellAdapter(new WorkerMasterAdapter());
		
		listBox.addChangeHandler(this);
		
	}

	@Override
	public void setQualificationsList(List<QualificationProxy> qualifications) {
		this.qualifications = qualifications;
		for(QualificationProxy q : qualifications)
			listBox.addItem(q.getName());
		DomEvent.fireNativeEvent(Document.get().createChangeEvent(), listBox); //Fa sì che venga sparato il ChangeEvent
	}

	@Override
	public void setWorkersSource(WorkersSource workersSource) {
		this.workersSource = workersSource;
	}
	
	@Override
	public void onChange(ChangeEvent event) {
		if(!qualifications.isEmpty()){
			int index = listBox.getSelectedIndex();
			QualificationProxy qualification = qualifications.get(index!=-1?index:0);
			dataProvider.setQuery(new WorkersByQualificationQuery(qualification,workersSource));
		}
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent event){
		presenter.goToWorkerScreen((WorkerProxy) event.getSource());
	}

}
