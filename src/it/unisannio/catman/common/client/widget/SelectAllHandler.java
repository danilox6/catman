package it.unisannio.catman.common.client.widget;

import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

//FIXME nome brutto
public class SelectAllHandler<T> {
	
	private MultiSelectionModel<T> selectionModel;
	private ListDataProvider<T> dataProvider;
	
	public static enum SelectionState {ALL_SELECTED, SOME_SELECTED, NONE_SELECTED};
	
	public SelectAllHandler(MultiSelectionModel<T> selectionModel, ListDataProvider<T> dataProvider) {
		this.selectionModel = selectionModel;
		this.dataProvider = dataProvider;
	}
	
	public SelectionState getSelectionState(){
		if(selectionModel.getSelectedSet().size() == 0)
			return SelectionState.NONE_SELECTED;
		if(selectionModel.getSelectedSet().size() == dataProvider.getList().size())
			return SelectionState.ALL_SELECTED;
		return SelectionState.SOME_SELECTED;
	}
	
	public void selectAll(){
		for(T entry : dataProvider.getList()){
			selectionModel.setSelected(entry, true);
		}
	}
	
	public void deselectAll(){
		for(T entry : dataProvider.getList()){
			selectionModel.setSelected(entry, false);
		}
	}
	
	public void selectOrDeselect(){//FIXME nome brutto
		if(getSelectionState().equals(SelectionState.NONE_SELECTED))
			selectAll();
		else
			deselectAll();
	}
	
	public MultiSelectionModel<T> getMultiSelectionModel(){
		return selectionModel;
	}
}
