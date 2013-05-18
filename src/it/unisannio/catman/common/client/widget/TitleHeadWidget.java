package it.unisannio.catman.common.client.widget;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.Label;

/**
 * Extends {@link BaseActionBarWidget} adding a &quot;title&quot; {@link Label} in the <i>leftPanel</i>;    
 *
 */
public class TitleHeadWidget extends BaseActionBarWidget{
	
	protected Label titleLabel;
	
	public TitleHeadWidget() {
		this("Title");
	}
	
	public @UiConstructor TitleHeadWidget(String title){
		super();
		titleLabel = new Label(title);
		leftPanel.add(titleLabel);
	}
	
	public void setTitle(String title){
		titleLabel.setText(title);
	}

}
