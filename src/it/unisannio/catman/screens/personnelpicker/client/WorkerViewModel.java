package it.unisannio.catman.screens.personnelpicker.client;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.domain.humanresources.client.EmployeeProxy;
import it.unisannio.catman.domain.humanresources.client.JobBoardProxy;
import it.unisannio.catman.screens.personnelpicker.client.DetailView.EmployeeProxyMock;
import it.unisannio.catman.screens.personnelpicker.client.widget.EmployeCellTreeAdapter;
import it.unisannio.catman.screens.personnelpicker.client.widget.JobBoardCellTreeAdapter;

import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

public class WorkerViewModel implements TreeViewModel{
	
	
	
	
	public WorkerViewModel(){
		
	}

	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		if (value == null) {
			ListDataProvider<Category> categoryDataProvider = new ListDataProvider<Category>();
			List<Category> categories = categoryDataProvider.getList();
			categories.add(new Category("Workers"));
			categories.add(new Category("Candidates"));
			categories.add(new Category("Job Boards"));
			return new DefaultNodeInfo<Category>(categoryDataProvider, new CategoryCell());
		}
		else if (value instanceof Category) {
			Category category = (Category) value;
			
			//FIXME Query workers, candidates and JobBoards by categories
			if(category.getName().equals("Workers")){
				ListDataProvider<EmployeeProxy> dataProvider = new ListDataProvider<EmployeeProxy>();
				List<EmployeeProxy> dataList = dataProvider.getList();
				dataList.add(new EmployeeProxyMock());
				dataList.add(new EmployeeProxyMock());
				dataList.add(new EmployeeProxyMock());
				
				return new DefaultNodeInfo<EmployeeProxy>(dataProvider,new MasterCell<EmployeeProxy>(new EmployeCellTreeAdapter()));
			}else if(category.getName().equals("Candidates")){
				ListDataProvider<EmployeeProxy> dataProvider = new ListDataProvider<EmployeeProxy>();
				List<EmployeeProxy> dataList = dataProvider.getList();
				dataList.add(new EmployeeProxyMock());
				dataList.add(new EmployeeProxyMock());
				dataList.add(new EmployeeProxyMock());
				
				return new DefaultNodeInfo<EmployeeProxy>(dataProvider,new MasterCell<EmployeeProxy>(new EmployeCellTreeAdapter()));
			} else if(category.getName().equals("Job Boards")){
				ListDataProvider<JobBoardProxy> dataProvider = new ListDataProvider<JobBoardProxy>();
				List<JobBoardProxy> dataList = dataProvider.getList();
				dataList.add(new JobBoardProxyMock());
				dataList.add(new JobBoardProxyMock());
				dataList.add(new JobBoardProxyMock());
				
				return new DefaultNodeInfo<JobBoardProxy>(dataProvider,new MasterCell<JobBoardProxy>(new JobBoardCellTreeAdapter()));
			}
		}
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		return value instanceof EmployeeProxy || value instanceof JobBoardProxy;
	}
	
	
	
	class JobBoardProxyMock implements JobBoardProxy{
		
	}

	class Category{
		private String name;
		
		public Category(String name){
			this.name = name;
		}
		
		public String getName(){
			return name;
		}
		
	}
	
	class CategoryCell extends AbstractCell<Category>{

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, Category value, SafeHtmlBuilder sb) {
			sb.appendEscaped(value.getName());
		}
		
	}
}
