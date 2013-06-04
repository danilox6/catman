package it.unisannio.catman.screens.stock.client;

import it.unisannio.catman.common.client.ui.SelectAllButton;

import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MasterView extends Composite {

	private static MasterViewUiBinder uiBinder = GWT.create(MasterViewUiBinder.class);

	interface MasterViewUiBinder extends UiBinder<Widget, MasterView> {}
	
	//TODO il bottone per cancellare deve apparire solo quando qualcosa è selezionato, altrimenti va mostrato quello per aggiungere

	@UiField Heading titleLabel;
	@UiField SelectAllButton selectButton; //FIXME per qualche motivo non si vede
	
	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
