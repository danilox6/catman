package it.unisannio.catman.common.client.widget;

import com.google.gwt.user.client.ui.Label;

/**
 * Extends {@link BaseActionBarWidget} adding a &quot;title&quot; {@link Label} in the <i>leftPanel</i>;    
 *
 */
public class HeadWidget extends BaseActionBarWidget{
	
	protected Label titleLabel;
	
	public HeadWidget() {
		super();	
		titleLabel = new Label("Title");
		leftPanel.add(titleLabel);
	}
	
	public void setTitle(String title){
		titleLabel.setText(title);
	}

}
