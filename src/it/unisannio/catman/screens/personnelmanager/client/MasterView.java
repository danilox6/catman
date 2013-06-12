package it.unisannio.catman.screens.personnelmanager.client;

import java.util.List;

import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.screens.personnelmanager.client.adapters.WorkersSourceCellAdapter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class MasterView extends Composite implements PersonnelManager.View{

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}

	@UiField DataList<WorkersSource> dataList;
	
	private ListDataProvider<WorkersSource> dataProvider = new ListDataProvider<WorkersSource>();
	
	private PersonnelManager.Presenter presenter;
	
	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		
		dataList.setCellAdapter(new WorkersSourceCellAdapter());
		dataList.setDataProvider(dataProvider);
	}

	@Override
	public void setWorkersSources(List<WorkersSource> workersSources) {
		dataProvider.getList().clear();
		dataProvider.getList().addAll(workersSources);
	}

	@UiHandler("dataList")
	void handleCellClik(ClickEvent e){
		presenter.goToWorkersScreen((WorkersSource) e.getSource());
	}
	
	@Override
	public void setPresenter(PersonnelManager.Presenter presenter) {
		this.presenter = presenter;
	}
}
