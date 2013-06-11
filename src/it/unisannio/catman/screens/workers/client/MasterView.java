package it.unisannio.catman.screens.workers.client;

import java.util.ArrayList;
import java.util.List;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.humanresources.client.QualificationProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.domain.humanresources.client.WorkersSource.Source;
import it.unisannio.catman.screens.workers.client.adapters.WorkerMasterAdapter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Request;

public class MasterView extends Composite implements Workers.View, ChangeHandler{

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	@UiField ListBox listBox;
	@UiField DataList<WorkerProxy> dataList;
	
	private QueryDataProvider<WorkerProxy> dataProvider = new QueryDataProvider<WorkerProxy>();
	
	private WorkersSource workersSource;
	
	List<QualificationProxy> qualifications = new ArrayList<QualificationProxy>();

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
			dataProvider.setQuery(new WorkersByQualificationInSource(qualification,workersSource));
		}
	}
	
	class WorkersByQualificationInSource implements Query<WorkerProxy>{
		
		private QualificationProxy qualification;
		private WorkersSource source;
		private DataStore dataStore;
		
		public WorkersByQualificationInSource(QualificationProxy qualification, WorkersSource source) {
			this.qualification = qualification;
			this.source = source;
			dataStore = App.getInstance().getDataStore();
		}

		@Override
		public Request<List<WorkerProxy>> list(int start, int length) {
			if(source.getSource() == Source.WORKERS)
				return dataStore.workers().listByQualificationInWorkersSource(qualification, start, length);
			if(source.getSource() == Source.CANDIDATES)
				return dataStore.workers().listByQualificationInCandidates(qualification, start, length);
			return dataStore.workers().listByQualificationInJobBoard(qualification, source.getJobBoardProxy(), start, start);
		}
		
		@Override
		public Request<Integer> count() {
			if(source.getSource() == Source.WORKERS)
				return dataStore.workers().countByQualificationInWorkersSource(qualification);
			if(source.getSource() == Source.CANDIDATES)
				return dataStore.workers().countByQualificationInCandidates(qualification);
			return dataStore.workers().countByQualificationInJobBoard(qualification, source.getJobBoardProxy());
		}
		
		@Override
		public Request<Void> deleteSet(List<WorkerProxy> set) {
			throw new UnsupportedOperationException(); //FIXME
		}
		
		@Override
		public Request<Void> deleteAll(List<WorkerProxy> skip) {
			throw new UnsupportedOperationException(); //FIXME
		}
	}

}
