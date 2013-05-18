package it.unisannio.catman.screens.workers.client;

import com.google.gwt.user.cellview.client.CellTree;

import it.unisannio.catman.common.client.widget.AbstractDetailView;
import it.unisannio.catman.common.client.widget.TitleHeadWidget;
import it.unisannio.catman.domain.humanresources.client.PersonnelSource;

public abstract class PersonnelSourceDetailView extends AbstractDetailView implements Workers.Detail.View {
	interface Presenter {}
	
	public PersonnelSourceDetailView(PersonnelSource source) {
		northPanel.add(new TitleHeadWidget(source.getName()));
		CellTree cellTree = new CellTree(new PersonnelTreeModel(source), null);
		cellTree.setAnimationEnabled(true);
		centerVerticalPanel.add(cellTree);
	}
}
