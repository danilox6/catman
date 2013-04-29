package it.unisannio.catman.screens.personnellpicker.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.DetailItemListPanel;
import it.unisannio.catman.common.client.widget.DetailSectionWidget;
import it.unisannio.catman.domain.humanresources.client.EmployeeProxy;
import it.unisannio.catman.screens.personnellpicker.client.widget.DetailHeadWidget;
import it.unisannio.catman.screens.personnellpicker.client.widget.SelectedEmployeeCellAdapter;
import it.unisannio.catman.screens.personnellpicker.client.widget.SelectedEmployeeCellList;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class DetailView extends Composite implements PersonnellPicker.Detail.View {

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {
	}

	@UiField SimplePanel northPanel;
	@UiField DetailItemListPanel detailItemList;
	@UiField SimplePanel southPanel;


	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));

		detailItemList.setHeight((Window.getClientHeight() - 24 - 0)+"px");	//FIXME Hardcoded size
		detailItemList.setWidth((Window.getClientWidth() - 320 - 60)+"px"); //FIXME Hardcoded size
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				int height = event.getHeight();
				detailItemList.setHeight((height - northPanel.getOffsetHeight() - southPanel.getOffsetHeight()) + "px");
				//Window.alert(height + " -n:"+northPanel.getOffsetHeight()+ " -s:"+southPanel.getOffsetHeight());
			}
		});

		northPanel.add(new DetailHeadWidget("Ruolo Y 3/5"));

		DetailSectionWidget selectedSection = new DetailSectionWidget("Selected");
		SelectedEmployeeCellList selectedEmployeeCellList = new SelectedEmployeeCellList(new MasterCell<EmployeeProxy>(new SelectedEmployeeCellAdapter()));
		ListDataProvider<EmployeeProxy> selectedEmployeeaProvider = new ListDataProvider<EmployeeProxy>();
		selectedEmployeeaProvider.addDataDisplay(selectedEmployeeCellList);
		
		List<EmployeeProxy> dataList = selectedEmployeeaProvider.getList();
		dataList.add(new EmployeeProxyMock());
		dataList.add(new EmployeeProxyMock());
		dataList.add(new EmployeeProxyMock());
		dataList.add(new EmployeeProxyMock());
		dataList.add(new EmployeeProxyMock());
		
		
		
		selectedSection.add(selectedEmployeeCellList);
		detailItemList.add(selectedSection);

		DetailSectionWidget availableSection = new DetailSectionWidget("Available");
		CellTree cellTree = new CellTree(new WorkerViewModel(), null);
		cellTree.setAnimationEnabled(true);
		availableSection.add(cellTree);
		detailItemList.add(availableSection);
	}

	//FIXME solo per testing
	static class EmployeeProxyMock implements EmployeeProxy{

	}
	
	class ProvaCell extends AbstractCell<EmployeeProxy>{

		@Override
		public void render(com.google.gwt.cell.client.Cell.Context context, EmployeeProxy value, SafeHtmlBuilder sb) {
			sb.appendEscaped("Prova");
		}
		
	}

}
