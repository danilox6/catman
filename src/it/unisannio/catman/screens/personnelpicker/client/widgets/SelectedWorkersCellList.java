package it.unisannio.catman.screens.personnelpicker.client.widgets;

import java.util.List;

import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;

/**
 * Basically an horizontal {@link CellList} used to display selected workers 
 */
public class SelectedWorkersCellList extends CellList<WorkerProxy>{

	/**
	 * The width of the cells showing selected workers.
	 */
	private final static int SELECTED_WORKER_CELL_WIDTH = 200;  //FIXME Hardcoded size
	
	public SelectedWorkersCellList(Cell<WorkerProxy> cell){
		this(cell, getDefaultResources(), null);
	}

	public SelectedWorkersCellList(Cell<WorkerProxy> cell, Resources resources) {
		this(cell, resources, null);
	}

	public SelectedWorkersCellList(Cell<WorkerProxy> cell, ProvidesKey<WorkerProxy> keyProvider) {
		this(cell, getDefaultResources(), keyProvider);
	}

	public SelectedWorkersCellList(Cell<WorkerProxy> cell, Resources resources, ProvidesKey<WorkerProxy> keyProvider) {
		super(cell, resources, keyProvider);
	}

	private static Resources getDefaultResources() {
		return GWT.create(Resources.class);
	}

	@Override
	protected void renderRowValues(SafeHtmlBuilder sb, List<WorkerProxy> values, int start, SelectionModel<? super WorkerProxy> selectionModel) {
	    int end = start + values.size();
	    for (int i = start; i < end; i++) {
	      WorkerProxy value = values.get(i - start);
	      
	      StringBuilder classesBuilder = new StringBuilder();
	      SafeHtmlBuilder cellBuilder = new SafeHtmlBuilder();
	      Context context = new Context(i, 0, getValueKey(value));
	      getCell().render(context, value, cellBuilder);
	      sb.append(TEMPLATE.div(i, classesBuilder.toString(), cellBuilder.toSafeHtml()));
	    }
	    sb.appendHtmlConstant("<div style=\"clear:both;\"></div>");
	}
	private static final Template TEMPLATE = GWT.create(Template.class);
	interface Template extends SafeHtmlTemplates {
		@Template("<div onclick=\"\" __idx=\"{0}\" class=\"{1}\" style=\"float:left; outline:none; width: "+ SELECTED_WORKER_CELL_WIDTH +"px;\" >{2}</div>")
		SafeHtml div(int idx, String classes, SafeHtml cellContents);
	}

}
