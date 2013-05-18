package it.unisannio.catman.common.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * A {@link BaseActionBarWidget} that can switch from/to "search mode".
 * The {@link Button} that toggles "search mode" must be provided by setSearchButton(Button b) method
 *
 */
public abstract class SearchActionBarWidget extends BaseActionBarWidget{
	
	private Button searchModeButton;
	private List<Widget> leftPanelWidgets = new ArrayList<Widget>();
	private List<Widget> rightPanelWidgets = new ArrayList<Widget>(); 
	
	
	public SearchActionBarWidget() {
	}
	
	public void setSearchButton(Button searchButton) {
		this.searchModeButton = searchButton;
		this.searchModeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				switchToSearchMode();
			}
		});
	}
	
	private void switchToSearchMode(){
		for(int i = 0; i< leftPanel.getWidgetCount();i++){
			leftPanel.getWidget(i).setVisible(false);
			leftPanelWidgets.add(leftPanel.getWidget(i));
		}
		leftPanel.clear();
		for(int i = 0; i< rightPanel.getWidgetCount();i++){
			rightPanel.getWidget(i).setVisible(false);
			rightPanelWidgets.add(rightPanel.getWidget(i));
		}
		rightPanel.clear();
		
		final Button backButton = new Button("Back");
		final Button searchButton = new Button("Search");
		final TextBox queryTextBox = new TextBox();
		//queryTextBox.setWidth("100%"); //FIXME Il text box deve reiempire il widget
		
		ClickHandler clickHandler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(event.getSource().equals(backButton)){
					switchToNormalMode();
				}else if(event.getSource().equals(searchButton)){
					handleResearch(queryTextBox.getText());
					switchToNormalMode();
				}
			}
		};
		backButton.addClickHandler(clickHandler);
		searchButton.addClickHandler(clickHandler);
		
		leftPanel.add(backButton);
		leftPanel.add(queryTextBox);
		rightPanel.add(searchButton);
		
	}
	
	private void switchToNormalMode(){
		leftPanel.clear();
		for(int i = 0; i<leftPanelWidgets.size();i++){
			leftPanelWidgets.get(i).setVisible(true);
			leftPanel.add(leftPanelWidgets.get(i));
		}
		rightPanel.clear();
		for(int i = 0; i<rightPanelWidgets.size();i++){
			rightPanelWidgets.get(i).setVisible(true);
			rightPanel.add(rightPanelWidgets.get(i));
		}
	}
	
	public abstract void handleResearch(String query); //FIXME Giusto per prova
}
