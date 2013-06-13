package it.unisannio.catman.screens.personnelpicker.client;

import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.humanresources.client.JobBoardProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.screens.personnelpicker.client.PersonnelPicker.Presenter;
import it.unisannio.catman.screens.personnelpicker.client.adapters.WorkerMasterAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class MasterView extends Composite implements PersonnelPicker.Master.View{

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	@UiField Heading titleLabel;
	@UiField DataList<WorkerProxy> dataList;
	
	private ListDataProvider<WorkerProxy> dataProvider = new ListDataProvider<WorkerProxy>();
	
	private HashSet<WorkerProxy> selectedWorkers = new HashSet<WorkerProxy>();
	private List<WorkerProxy> workersByPosition = new ArrayList<WorkerProxy>();
	private HashMap<WorkerProxy, JobBoardProxy> jobBoardMapping = new HashMap<WorkerProxy, JobBoardProxy>();
	private WorkerMasterAdapter adapter = new WorkerMasterAdapter();
	
	private Presenter presenter;

	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		dataList.setCellAdapter(adapter);
		dataList.setDataProvider(dataProvider);
	}

	@Override
	public void setPositionProxy(PositionProxy positionProxy) {
		titleLabel.setText(positionProxy.getDescription()+ " " + positionProxy.getQuantityFilled()+"/"+positionProxy.getQuantity());
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e){
		presenter.goToWorkerScreen((WorkerProxy) e.getSource());
	}

	@Override
	public void setWorkersByPosition(List<WorkerProxy> workers) {
		workersByPosition.clear();
		workersByPosition.addAll(workers);
		sortAndSetData();
	}

	@Override
	public void setJobBoarsByPosition(List<JobBoardProxy> jobBoards) {
		jobBoardMapping.clear();
		for(JobBoardProxy jb : jobBoards)
			for(WorkerProxy w : jb.getWorkers())
				jobBoardMapping.put(w, jb);
		adapter.setJobBoardMapping(jobBoardMapping);
		sortAndSetData();
	}
	
	@Override
	public void setSelectedWorkers(List<WorkerProxy> selectedWorkers) {
		this.selectedWorkers.clear();
		this.selectedWorkers.addAll(selectedWorkers);
		adapter.setSelectedWorkers(this.selectedWorkers);
		sortAndSetData();
	}
	
	private void sortAndSetData(){
		List<WorkerProxy> dataList = dataProvider.getList();
		dataList.clear();
		dataList.addAll(workersByPosition);
		Collections.sort(dataList, new MasterDataListComparator());
		
	}


	class MasterDataListComparator implements Comparator<WorkerProxy>{

		@Override
		public int compare(WorkerProxy w1, WorkerProxy w2) {
			if(selectedWorkers.contains(w1) && !selectedWorkers.contains(w2))
				return -1;
			if(!selectedWorkers.contains(w1) && selectedWorkers.contains(w2))
				return 1;
			if(w1.isCandidate() && !w2.isCandidate())
				return -1;
			if(!w1.isCandidate() && w2.isCandidate())
				return 1;
			if(jobBoardMapping.containsKey(w1) && !jobBoardMapping.containsKey(w2))
				return 1;
			if(!jobBoardMapping.containsKey(w1) && jobBoardMapping.containsKey(w2))
				return -1;
			return 0;
		}
		
		
	}

}
