package it.unisannio.catman.common.client.ui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MultiPanel extends Composite {

	private static MultiPanelUiBinder uiBinder = GWT
			.create(MultiPanelUiBinder.class);

	interface MultiPanelUiBinder extends UiBinder<Widget, MultiPanel> {
	}
	
	private int index = 0;	
	private ArrayList<IsWidget> contents = new ArrayList<IsWidget>();
	
	@UiField
	SimplePanel content;

	public MultiPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiChild(tagname = "panel")
	public void addContent(IsWidget content) {
		contents.add(content);
	}
	
	public IsWidget getCurrent() {
		return contents.get(index);
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
		content.setWidget(contents.get(index));
	}
}
