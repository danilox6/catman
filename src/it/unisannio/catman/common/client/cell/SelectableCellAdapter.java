package it.unisannio.catman.common.client.cell;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.SelectionModel;

public abstract class SelectableCellAdapter<T> extends AbstractCellAdapter<T>{
	private SelectionModel<? super T> selectionModel = null;
	
	public static final String SELECTOR_ATTIBUTE = "data-selector";
	
	public void setSelectionModel(SelectionModel<? super T> selectionModel) {
		this.selectionModel = selectionModel;
	}
	
	protected boolean isSelected(T object){
		return selectionModel != null && selectionModel.isSelected(object);
	}
	
	protected SafeHtml getSimpleSelectionCheckBox(T object){
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<input "+SELECTOR_ATTIBUTE+"='true' type='checkbox' " + (isSelected(object)?"checked='checked'":"") + "/>");
		return sb.toSafeHtml();
	}
}
