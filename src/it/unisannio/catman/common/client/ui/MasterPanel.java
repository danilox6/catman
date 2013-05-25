package it.unisannio.catman.common.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MasterPanel extends Composite {

	private static MasterPanelUiBinder uiBinder = GWT
			.create(MasterPanelUiBinder.class);

	interface MasterPanelUiBinder extends UiBinder<Widget, MasterPanel> {
	}
	
	protected @UiField SimplePanel header;
	protected @UiField ScrollPanel content;
	protected @UiField SimplePanel footer;

	public MasterPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiChild(tagname="header")
	public void addToHeader(IsWidget w) {
		header.add(w);
	}

	@UiChild(tagname="footer")
	public void addToFooter(IsWidget w) {
		footer.add(w);
	}
	
	@UiChild(tagname="content")
	public void addToContent(IsWidget w) {
		content.add(w);
	}
	
	@Override
	public void setWidth(String width) {
		
		content.setWidth(width);
		super.setWidth(width);
	}
}
