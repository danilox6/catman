package it.unisannio.catman.screens.plan.client;

import java.util.List;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.ListDataProvider;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractDetailView;
import it.unisannio.catman.common.client.widget.DetailSectionWidget;
import it.unisannio.catman.screens.plan.client.widget.DetailHeadWidget;

public class DetailView extends AbstractDetailView implements Plan.Detail.View{
	interface Presenter{}
	
	public DetailView() {
		northPanel.add(new DetailHeadWidget("Plan"));

		DetailSectionWidget materialsSection = new DetailSectionWidget("Materiali");

		CellList<PlanProxy> materialsCellList = new CellList<PlanProxy>(new MasterCell<PlanProxy>(new PlanCellAdapter()));

		materialsSection.add(materialsCellList);
		centerVerticalPanel.add(materialsSection);

		ListDataProvider<PlanProxy> materialsDataProvider = new ListDataProvider<PlanProxy>();
		materialsDataProvider.addDataDisplay(materialsCellList);

		List<PlanProxy> materialsValues = materialsDataProvider.getList();
		materialsValues.add(new PlanProxyMock());
		materialsValues.add(new PlanProxyMock());
		materialsValues.add(new PlanProxyMock());
		materialsValues.add(new PlanProxyMock());

		DetailSectionWidget personnelSection = new DetailSectionWidget("Personale");
		CellList<PlanProxy> personnelCellList = new CellList<PlanProxy>(new MasterCell<PlanProxy>(new PlanCellAdapter()));
		personnelSection.add(personnelCellList);
		centerVerticalPanel.add(personnelSection);

		ListDataProvider<PlanProxy> personnelDataProvider = new ListDataProvider<PlanProxy>();
		personnelDataProvider.addDataDisplay(personnelCellList);

		List<PlanProxy> personnelValues = personnelDataProvider.getList();
		personnelValues.add(new PlanProxyMock());
		personnelValues.add(new PlanProxyMock());
		personnelValues.add(new PlanProxyMock());
		personnelValues.add(new PlanProxyMock());
	}

	//FIXME Solo per i test
	interface PlanProxy {}
	class PlanProxyMock implements PlanProxy{}
	class PlanCellAdapter extends AbstractCellAdapter<PlanProxy>{

		@Override
		public SafeHtml getNorth(PlanProxy object) {
			return new SafeHtmlBuilder().appendEscaped("Merce").toSafeHtml();
		}

	}

}
