package it.unisannio.catman.screens.personnelpicker.client;

import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;
import it.unisannio.catman.screens.personnelpicker.client.PersonnelPicker.Presenter;
import it.unisannio.catman.screens.personnelpicker.client.adapters.WorkerMasterAdapter;

import java.util.List;

import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements PersonnelPicker.Master.View{

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	@UiField Heading titleLabel;
	@UiField DataList<WorkerProxy> dataList;
	
	private QueryDataProvider<WorkerProxy> dataProvider = new QueryDataProvider<WorkerProxy>();
	
	private Presenter presenter;

	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		dataList.setCellAdapter(new WorkerMasterAdapter());
		dataList.setDataProvider(dataProvider);
	}

	@Override
	public void setPositionProxy(PositionProxy positionProxy) {
		titleLabel.setText(positionProxy.getDescription()+ " " + positionProxy.getQuantityFilled()+"/"+positionProxy.getQuantity());
	}

	@Override
	public void setSelectedWorkers(List<WorkerProxy> selectedWorkers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setWorkersQuery(Query<WorkerProxy> query) {
		dataProvider.setQuery(query);
	}
	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e){
		presenter.goToWorkerScreen((WorkerProxy) e.getSource());
	}

}
