package it.unisannio.catman.common.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MasterPanel extends Composite {

	private static MasterPanelUiBinder uiBinder = GWT.create(MasterPanelUiBinder.class);

	interface MasterPanelUiBinder extends UiBinder<Widget, MasterPanel> {
	}
	
	protected @UiField SimplePanel header;
	protected @UiField SimplePanel content;
	protected @UiField SimplePanel footer;
	protected @UiField DivElement footerDiv;
	protected @UiField MasterPanelStyle style;

	private int contentHeight = 0; 
	
	public MasterPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				adaptHeightToClient(event.getHeight());
			}
		});
		
		footerDiv.getStyle().setDisplay(Display.NONE);
	}
	
	@UiChild(tagname="header")
	public void addToHeader(IsWidget w) {
		header.add(w);
	}

	@UiChild(tagname="footer")
	public void addToFooter(IsWidget w) {
		footer.add(w);
		footerDiv.getStyle().setDisplay(Display.BLOCK);
	}
	
	@UiChild(tagname="content")
	public void addToContent(IsWidget w) {
		content.add(w);
	}
	
	@Override
	protected void onLoad() {
		adaptHeightToClient(Window.getClientHeight());
		super.onLoad();
	}
	

	private void adaptHeightToClient(int clientHeight){
		int headerHeight = getCssPropertyIntValue("height", getCssByClass(style.header(), 	style.getText()));
		int footerHeight = 0;
		if(! (footerDiv.getStyle().getDisplay().equalsIgnoreCase(Display.NONE.toString())))
			footerHeight = getCssPropertyIntValue("height", getCssByClass(style.footer(), 	style.getText()));
		
		contentHeight = clientHeight - headerHeight - footerHeight;
		content.setHeight(contentHeight + "px");
	}
	
	private String getCssByClass(String cssClass, String css){
		String result = "";
		int startIndex = css.indexOf(cssClass+"{")+(cssClass+":").length();
		int endIndex = css.indexOf("}", startIndex);
		result = css.substring(startIndex, endIndex).trim();
		return result;
	}
	
	private String getCssPropertyValue(String property, String css){
		String result = "";
		int startIndex = css.indexOf(property+":")+(property+":").length();
		int endIndex = css.indexOf(";", startIndex);
		result = css.substring(startIndex, endIndex).trim();
		return result;
	}
	
	private int getCssPropertyIntValue(String property, String css){
		String stringValue = getCssPropertyValue(property, css).replaceAll("[^\\d.]", "");
		int value = 0;
		try{
			value = Integer.parseInt(stringValue);
		}catch (NumberFormatException e){}
		return value;
	}
	
	interface MasterPanelStyle extends CssResource{
		String header();
		String footer();
	}
	
	public int getContentHeight() {
		return contentHeight;
	}
}
