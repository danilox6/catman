package it.unisannio.catman.common.client.ui;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.QueryDataProvider.SelectionState;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.AbstractHasData;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;

public class SelectAllButton extends Button{

	private QueryDataProvider<?> dataProvider;
	
	public SelectAllButton() {
		setType(ButtonType.LINK);
		addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				dataProvider.setSelectedAll(dataProvider.getSelectionState()==SelectionState.NONE_SELECTED);
				for(HasData<?> display : dataProvider.getDataDisplays())
					if(display instanceof AbstractHasData)
						((AbstractHasData<?>)display).redraw();
			}
		});
	}
	
	public void setDataProvider(QueryDataProvider<?> queryDataProvider){
		this.dataProvider =  queryDataProvider;
		dataProvider.addSelectionChangeHandler(new  SelectionChangeEvent.Handler() {
			
			@Override
			public void onSelectionChange(SelectionChangeEvent event) {
				switch (dataProvider.getSelectionState()) {
					case ALL_SELECTED:
						setIcon(IconType.CHECK); 
						break;
					case SOME_SELECTED:
						setIcon(IconType.CHECK_MINUS); 
						break;
					case NONE_SELECTED:
						setIcon(IconType.CHECK_EMPTY); 
						break;
					}
			}
		});
	}
	

}
