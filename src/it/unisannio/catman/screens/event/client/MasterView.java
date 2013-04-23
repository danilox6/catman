package it.unisannio.catman.screens.event.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.HeadWidget;
import it.unisannio.catman.domain.workflow.client.DocumentProxy;
import it.unisannio.catman.screens.event.client.widget.DocumentCellAdapter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;

import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

public class MasterView extends Composite implements Event.Master.View {
	interface Presenter {}

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);
	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	@UiField SimplePanel northPanel;
	@UiField SimplePanel southPanel;
	@UiField ScrollPanel centerScrollPanel;

	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
		
		centerScrollPanel.setHeight((Window.getClientHeight() - 24 - 24)+"px");		//FIXME Hardcoded size
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				int height = event.getHeight();
				centerScrollPanel.setHeight((height - northPanel.getOffsetHeight() - southPanel.getOffsetHeight()) + "px");
				//Window.alert(height + " -n:"+northPanel.getOffsetHeight()+ " -s:"+southPanel.getOffsetHeight());
			}
		});
		
		
		northPanel.add(new HeadWidget("Nome evento"));
		
		DocumentCellAdapter cellAdapter = new DocumentCellAdapter();
		//FIXME Proxy adatto
		CellList<DocumentProxy> cellList = new CellList<DocumentProxy>(new MasterCell<DocumentProxy>(cellAdapter));

		MultiSelectionModel<DocumentProxy> selectionModel = new MultiSelectionModel<DocumentProxy>();
		cellList.setSelectionModel(selectionModel, DefaultSelectionEventManager.<DocumentProxy>createCheckboxManager());
		
		ListDataProvider<DocumentProxy> dataProvider = new ListDataProvider<DocumentProxy>();
		dataProvider.addDataDisplay(cellList);
		
		List<DocumentProxy> values = dataProvider.getList(); //Da javadoc "Get the list that backs this model. Changes to the list will be reflected in the model."
		values.add(new DocumentProxyMock());
		values.add(new DocumentProxyMock());
		values.add(new DocumentProxyMock());
		values.add(new DocumentProxyMock());
		
		cellList.setRowCount(values.size(), true);

		centerScrollPanel.add(cellList);
		
		centerScrollPanel.setHeight((Window.getClientHeight() - 24 - 24)+"px"); //FIXME Hardcoded size
		Window.addResizeHandler(new ResizeHandler() {
			
			@Override
			public void onResize(ResizeEvent event) {
				int height = event.getHeight();
				centerScrollPanel.setHeight((height - northPanel.getOffsetHeight() - southPanel.getOffsetHeight()) + "px");
				//Window.alert(height + " -n:"+northPanel.getOffsetHeight()+ " -s:"+southPanel.getOffsetHeight());
			}
		});
		
	}
	
	//FIXME Solo per i test
	private static class DocumentProxyMock implements DocumentProxy {
		
	}

}
