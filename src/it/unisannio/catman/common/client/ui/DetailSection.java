package it.unisannio.catman.common.client.ui;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class DetailSection extends Composite implements HasWidgets{

	private static DetailSectionWidgetUiBinder uiBinder = GWT.create(DetailSectionWidgetUiBinder.class);

	interface DetailSectionWidgetUiBinder extends UiBinder<Widget, DetailSection> {}

	@UiField protected Label titleLabel;
	@UiField protected SimplePanel contentPanel;
	
	private String heightString = "100%";
	
	
	public @UiConstructor DetailSection(String title){
		this(title, (Widget[]) null);
	}
	
	public DetailSection(String title, Widget... widgets){
		initWidget(uiBinder.createAndBindUi(this));
		setTitle(title);
		if(widgets != null)
			setWidgets(widgets);
		this.setWidth("100%");
		contentPanel.setWidth("100%");
	}

	public void setTitle(String title){
		titleLabel.setText(title);
	}
	
	public void setWidgets(Widget... widgets){
		contentPanel.clear();
		addWidgets(widgets);
	}
	
	public void addWidgets(Widget... widgets){
		for(Widget widget:widgets)
			addWidget(widget);
	}
	
	public void addWidget(Widget widget){
		if(widget!=null)
			contentPanel.add(widget);
	}
	
	public void removeWidget(int index){
		//contentPanel.remove(index);
		removeWidget(contentPanel.getWidget());
	}
	
	public boolean removeWidget(Widget widget){
		return contentPanel.remove(widget);
	}

	@Override
	public void add(Widget w) {
		addWidget(w);
	}

	@Override
	public void clear() {
		contentPanel.clear();
	}

	@Override
	public Iterator<Widget> iterator() {
		return contentPanel.iterator();
	}

	@Override
	public boolean remove(Widget w) {
		return removeWidget(w);
	}
	
	@Override
	public void setHeight(String sHeight) {
		this.heightString = sHeight;
		if(sHeight.endsWith("px")){
			int height = Integer.parseInt(heightString.substring(0, heightString.lastIndexOf("px")).trim());
			contentPanel.setHeight((height-titleLabel.getOffsetHeight())+"px");
		}
		//contentPanel.setHeight(sHeight);
		super.setHeight(heightString);
	}
	
	@Override
	protected void onLoad() {
		setHeight(heightString);
		super.onLoad();
	}
}