package it.unisannio.catman.screens.personnelmanager.client;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractMasterView;
import it.unisannio.catman.common.client.widget.TitleHeadWidget;
import it.unisannio.catman.domain.humanresources.client.CandidatesSource;
import it.unisannio.catman.domain.humanresources.client.JobBoardSource;
import it.unisannio.catman.domain.humanresources.client.PersonnelSource;
import it.unisannio.catman.domain.humanresources.client.WorkersSource;
import it.unisannio.catman.screens.personnelmanager.client.widget.PersonnelSourceCellAdapter;

import java.util.List;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.ListDataProvider;

public class MasterView extends AbstractMasterView implements PersonnelManager.Master.View {
	interface Presenter {}
	
	public MasterView() {
		
		northPanel.add(new TitleHeadWidget("Personnel Manager"));
		
		CellList<PersonnelSource> cellList = new CellList<PersonnelSource>(new MasterCell<PersonnelSource>(new PersonnelSourceCellAdapter()));

		ListDataProvider<PersonnelSource> dataProvider = new ListDataProvider<PersonnelSource>();
		dataProvider.addDataDisplay(cellList);
		
		List<PersonnelSource> values = dataProvider.getList();
		values.add(new WorkersSource());
		values.add(new CandidatesSource());
		values.add(new JobBoardSource("Board 1"));
		values.add(new JobBoardSource("Board 2"));
		
		centerScrollPanel.add(cellList);
		
	}


}
