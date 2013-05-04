package it.unisannio.catman.common.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;

public class DeleteButton<T> extends Button implements SelectionChangeEvent.Handler, ClickHandler{
	
	MultiSelectionModel<T> selectionModel;

	public DeleteButton(String html, MultiSelectionModel<T> selectionModel) {
		super(html);
		this.selectionModel = selectionModel;
		this.selectionModel.addSelectionChangeHandler(this);
		if(selectionModel.getSelectedSet().isEmpty())
			this.setEnabled(false);
		else
			this.setEnabled(true);
		this.addClickHandler(this);
	}

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		Window.alert(selectionModel.getSelectedSet().toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onSelectionChange(SelectionChangeEvent event) {
		selectionModel = (MultiSelectionModel<T>) event.getSource();
		if(selectionModel.getSelectedSet().isEmpty())
			this.setEnabled(false);
		else
			this.setEnabled(true);
	}

}
