package it.unisannio.catman.common.client.cell;

import com.google.gwt.view.client.SelectionModel;

//FIXME nome brutto 
public abstract class SelectableCellAdapter<T> extends AbstractCellAdapter<T>{
	private SelectionModel<? super T> selectionModel = null;
	
	public SelectableCellAdapter(SelectionModel<T> selectionModel) {
		this.selectionModel = selectionModel;
	}
	
	public SelectableCellAdapter() {}
	
	public  void setSelectionModel(SelectionModel<? super T> selectionModel) {
		this.selectionModel = selectionModel;
	}
	
	public boolean isSelected(T object){
		return selectionModel != null && selectionModel.isSelected(object);
	}
}
