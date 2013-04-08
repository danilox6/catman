package it.unisannio.catman.screens.inbox.client.widget;

import it.unisannio.catman.common.client.widget.HeadWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Solo una prova per testare l'estensione di un Widget
 * Aggiunge un tasto di ricerca
 *
 */
public class SearchMasterHeadWidget extends HeadWidget{
	
	TextBox txtSearch;

	public SearchMasterHeadWidget() {
		super();
		rightPanel.add(new Button("Search",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(txtSearch == null){
					txtSearch = new TextBox();
				titleLabel.setVisible(false);
				leftPanel.add(txtSearch);
				}else{
					leftPanel.remove(txtSearch);
					titleLabel.setVisible(true);
					txtSearch = null;
					//TODO Esegui ricerca
				}
			}
		}));
	}
}
