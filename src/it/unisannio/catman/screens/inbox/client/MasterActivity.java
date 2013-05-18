package it.unisannio.catman.screens.inbox.client;

import it.unisannio.catman.common.client.ScreenActivity;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;

public class MasterActivity extends ScreenActivity implements Inbox.Master {
	
	interface Driver extends SimpleBeanEditorDriver<FakeObject, FakeObjectEditor> {}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
	//	Path p = getPath();
	//	Intent in = new Intent("inbox").withParams(String.valueOf(p.size() + 1));
		//panel.setWidget(new Hyperlink(p.toString(), pathTo(in).getToken()));
		panel.setWidget(new MasterView2(this));
	}
	
	private Driver driver = GWT.create(Driver.class);
	private DialogBox dialog = new DialogBox();

	@Override
	public void openNewDialog() {
		
		FakeObjectEditor editor = new FakeObjectEditor();
		editor.setActivity(this);
		driver.initialize(editor);
		
		FakeObject fake = new FakeObject();
		fake.setA("Hello");
		fake.setB("World");
		driver.edit(fake);
		
		
		dialog.add(editor);
		dialog.center();
	}

	@Override
	public void saveObject() {
		FakeObject edited = driver.flush();
		Window.alert(edited.toString());
		dialog.hide();
		
	}

}
