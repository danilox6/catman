package it.unisannio.catman.screens.personnelpicker.client.widget;

import java.util.List;

import it.unisannio.catman.domain.humanresources.client.EmployeeProxy;

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
 * Basically an horizontal {@link CellList} 
 */
public class SelectedEmployeeCellList extends CellList<EmployeeProxy>{

	public SelectedEmployeeCellList(Cell<EmployeeProxy> cell){
		this(cell, getDefaultResources(), null);
	}

	public SelectedEmployeeCellList(Cell<EmployeeProxy> cell, Resources resources) {
		this(cell, resources, null);
	}

	public SelectedEmployeeCellList(Cell<EmployeeProxy> cell, ProvidesKey<EmployeeProxy> keyProvider) {
		this(cell, getDefaultResources(), keyProvider);
	}

	public SelectedEmployeeCellList(Cell<EmployeeProxy> cell, Resources resources, ProvidesKey<EmployeeProxy> keyProvider) {
		super(cell, resources, keyProvider);
	}

	private static Resources getDefaultResources() {
		return GWT.create(Resources.class);
	}

	@Override
	protected void renderRowValues(SafeHtmlBuilder sb, List<EmployeeProxy> values, int start, SelectionModel<? super EmployeeProxy> selectionModel) {
	    int end = start + values.size();
	    for (int i = start; i < end; i++) {
	      EmployeeProxy value = values.get(i - start);
	      
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
		@Template("<div onclick=\"\" __idx=\"{0}\" class=\"{1}\" style=\"float:left; outline:none; width: 200px;\" >{2}</div>") //FIXME Hardcoded size
		SafeHtml div(int idx, String classes, SafeHtml cellContents);
	}

}
