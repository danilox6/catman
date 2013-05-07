package it.unisannio.catman.screens.personnelpicker.client;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.domain.humanresources.client.CandidatesSource;
import it.unisannio.catman.domain.humanresources.client.PersonnelSource;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.domain.humanresources.client.JobBoardSource;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.screens.personnelpicker.client.DetailView.WorkerProxyMock;
import it.unisannio.catman.screens.personnelpicker.client.widget.PersonnelCellTreeAdapter;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class PersonnelTreeModel implements TreeViewModel{

	public PersonnelTreeModel(){

	}

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value == null) {
			ListDataProvider<PersonnelSource> categoryDataProvider = new ListDataProvider<PersonnelSource>();
			List<PersonnelSource> categories = categoryDataProvider.getList();
			categories.add(new WorkersSource());
			categories.add(new CandidatesSource());
			categories.add(new JobBoardSource("Board 1"));
			categories.add(new JobBoardSource("Board 2"));
			return new DefaultNodeInfo<PersonnelSource>(categoryDataProvider, new PersonnelSourceCell());
		}
		else if (value instanceof PersonnelSource) {
		//	PersonnelSource source = (PersonnelSource) value;

			//FIXME Query workers, candidates and JobBoards by Source
			ListDataProvider<WorkerProxy> dataProvider = new ListDataProvider<WorkerProxy>();
			List<WorkerProxy> dataList = dataProvider.getList();
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());
			dataList.add(new WorkerProxyMock());

			return new DefaultNodeInfo<WorkerProxy>(dataProvider,new MasterCell<WorkerProxy>(new PersonnelCellTreeAdapter()));

		}
		return null;
	}




	@Override
	public boolean isLeaf(Object value) {
		return value instanceof WorkerProxy;
	}


	class PersonnelSourceCell extends AbstractCell<PersonnelSource>{

		public void render(com.google.gwt.cell.client.Cell.Context context, PersonnelSource value, SafeHtmlBuilder sb) {
			sb.appendEscaped(value.getName());
		}

	}
}
