package it.unisannio.catman.screens.inbox.client.widget;

import com.google.gwt.user.client.ui.Button;
import it.unisannio.catman.common.client.widget.BaseActionBarWidget;
import it.unisannio.catman.common.client.widget.DeleteButton;
import it.unisannio.catman.common.client.widget.SelectAllButton;
import it.unisannio.catman.common.client.widget.SelectAllHandler;
import it.unisannio.catman.domain.documents.client.DossierProxy;

public class SelectionHandlerBottomBar extends BaseActionBarWidget{
		
	
	public SelectionHandlerBottomBar(final SelectAllHandler<DossierProxy> selectAllHandler) {
		
		leftPanel.add(new DeleteButton<DossierProxy>(selectAllHandler.getMultiSelectionModel()));
		leftPanel.add(new Button("F"));
		leftPanel.add(new Button("I"));
		rightPanel.add(new Button("N"));
		rightPanel.add(new Button("T"));
		rightPanel.add(new Button("I"));
		rightPanel.add(new SelectAllButton<DossierProxy>(selectAllHandler));
	}
}
