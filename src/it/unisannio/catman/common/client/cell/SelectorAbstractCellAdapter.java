package it.unisannio.catman.common.client.cell;

import com.google.gwt.view.client.SelectionModel;

//FIXME nome brutto 
public abstract class SelectorAbstractCellAdapter<T> extends AbstractCellAdapter<T>{
	private SelectionModel<T> selectionModel = null;
	
	public SelectorAbstractCellAdapter(SelectionModel<T> selectionModel) {
		this.selectionModel = selectionModel;
	}
	
	public SelectorAbstractCellAdapter() {}
	
	public  void setSelectionModel(SelectionModel<T> selectionModel) {
		this.selectionModel = selectionModel;
	}
	
	public boolean isSelected(T object){
		return selectionModel != null && selectionModel.isSelected(object);
	}
}
