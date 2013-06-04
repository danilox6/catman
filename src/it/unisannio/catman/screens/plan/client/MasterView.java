package it.unisannio.catman.screens.plan.client;

import it.unisannio.catman.common.client.ui.SelectAllButton;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite implements ChangeHandler{

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}

	@UiField ListBox listBox;
	@UiField Button deleteButton;
	@UiField SelectAllButton selectButton; //FIXME Per qualche motivo non si vede

	private String[] listItems = {"Ruoli", "Materiali"};
	
	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));

		listBox.addChangeHandler(this);
		
		for(String item: listItems)
			listBox.addItem(item);
		DomEvent.fireNativeEvent(Document.get().createChangeEvent(), listBox); //Fa sì che venga sparato il ChangeEvent

	}

	@Override
	public void onChange(ChangeEvent event) {
		listItems[listBox.getSelectedIndex()].toString();
	}

	@UiHandler("deleteButton")
	void handleDeleteButtonClick(ClickEvent e){
		
	}

}
