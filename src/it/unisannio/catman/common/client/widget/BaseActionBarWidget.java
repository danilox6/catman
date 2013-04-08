package it.unisannio.catman.common.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Base &quot;ActionBae&quot; Widget. Simple {@link HorizontalPanel} that provides two inner {@link HorizontalPanel}:
 * <ul>
 * <li>A left aligned {@link HorizontalPanel} (<i>leftPanel</i>)</li>
 * <li>A right aligned {@link HorizontalPanel} (<i>rightPanel</i>)</li>
 * </ul> 
 */
public class BaseActionBarWidget extends Composite {

	private static MasterBottomWidgetUiBinder uiBinder = GWT.create(MasterBottomWidgetUiBinder.class);

	interface MasterBottomWidgetUiBinder extends UiBinder<Widget, BaseActionBarWidget> {}
	
	/**
	 * Left aligned {@link HorizontalPanel} 
	 */
	@UiField protected HorizontalPanel leftPanel;
	
	/**
	 * Right aligned {@link HorizontalPanel}
	 */
	@UiField protected HorizontalPanel rightPanel;
	

	public BaseActionBarWidget() {
		initWidget( uiBinder.createAndBindUi(this));
	}

}
