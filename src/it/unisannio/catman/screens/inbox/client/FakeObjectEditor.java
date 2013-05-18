package it.unisannio.catman.screens.inbox.client;

import java.awt.TextField;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class FakeObjectEditor extends Composite implements Editor<FakeObject>  {

	private static FakeObjectEditorUiBinder uiBinder = GWT
			.create(FakeObjectEditorUiBinder.class);

	interface FakeObjectEditorUiBinder extends
			UiBinder<Widget, FakeObjectEditor> {
	}
	
	@UiField TextBox aEditor;
	@UiField TextBox bEditor;
	
	@UiField Button ok;

	private Inbox.Master activity;
	
	public FakeObjectEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	void setActivity(Inbox.Master activity) {
		this.activity = activity;
	}

	@UiHandler("ok")
	void handleOk(ClickEvent e) {
		activity.saveObject();
	}
	

}
