package it.unisannio.catman.common.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MasterPanel extends Composite {

	private static MasterPanelUiBinder uiBinder = GWT.create(MasterPanelUiBinder.class);

	interface MasterPanelUiBinder extends UiBinder<Widget, MasterPanel> {
	}
	
	interface Style extends CssResource{
		String header();
		String footer();
		String scrollable();
		String noFooter();
	}
	
	protected @UiField Element container;
	protected @UiField SimplePanel header;
	protected @UiField FlowPanel content;
	protected @UiField SimplePanel footer;
	protected @UiField Style style;
	
	public MasterPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		container.addClassName(style.noFooter());
	}
	
	public void setScrollable(boolean scrollable) {
		if(scrollable)
			container.addClassName(style.scrollable());
		else
			container.removeClassName(style.scrollable());
	}
	
	@UiChild(tagname="header", limit = 1)
	public void addToHeader(IsWidget w) {
		header.add(w);
	}

	@UiChild(tagname="footer", limit = 1)
	public void addToFooter(IsWidget w) {
		footer.add(w);
		container.removeClassName(style.noFooter());
	}
	
	@UiChild(tagname="content")
	public void addToContent(IsWidget w) {
		content.add(w);
	}
}
