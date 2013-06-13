package it.unisannio.catman.common.client.cell;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.SelectionModel;

public abstract class InteractiveCellAdapter<T> extends AbstractCellAdapter<T>{
	private SelectionModel<? super T> selectionModel = null;
	
	public static final String SELECT_COMMAND = "select";
	public static final String EDIT_COMMAND = "edit";
	
	public void setSelectionModel(SelectionModel<? super T> selectionModel) {
		this.selectionModel = selectionModel;
	}
	
	protected boolean isSelected(T object){
		return selectionModel != null && selectionModel.isSelected(object);
	}
	
	private final String getCommandAttribute(final String value) {
		return MasterCell.ATTRIBUTE_COMMAND + "=\"" + value + "\"";
	}
	
	protected SafeHtml getSimpleSelectionCheckBox(T object){
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<input " 
				+ MasterCell.ATTRIBUTE_NOBUBBLE + "=\"true\" " 
				+ getCommandAttribute(SELECT_COMMAND)
				+ " type=\"checkbox\" " 
				+ (isSelected(object)?"checked=\"checked\"":"") + "/>");
		return sb.toSafeHtml();
	}
	
	protected SafeHtml getEditLink(String name) {
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<a href=\"javascript:;\" " + getCommandAttribute(EDIT_COMMAND) + ">")
			.appendEscaped(name)
			.appendHtmlConstant("</a>");
		
		return sb.toSafeHtml();
			
	}
}
