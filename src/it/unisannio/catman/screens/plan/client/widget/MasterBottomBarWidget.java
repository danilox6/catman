package it.unisannio.catman.screens.plan.client.widget;

import it.unisannio.catman.common.client.widget.BaseActionBarWidget;
import it.unisannio.catman.common.client.widget.DeleteButton;
import it.unisannio.catman.common.client.widget.SelectAllButton;
import it.unisannio.catman.common.client.widget.SelectAllHandler;

public class MasterBottomBarWidget<T> extends BaseActionBarWidget{

	public MasterBottomBarWidget(SelectAllHandler<T> selectAllHandler) {
		leftPanel.add(new DeleteButton<T>(selectAllHandler.getMultiSelectionModel()));
		rightPanel.add(new SelectAllButton<T>(selectAllHandler));
	}
}
