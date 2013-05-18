package it.unisannio.catman.screens.inbox.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.SelectableCellAdapter;
import it.unisannio.catman.common.client.ui.DataList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

public class MasterView2 extends Composite {

	private static MasterView2UiBinder uiBinder = GWT
			.create(MasterView2UiBinder.class);

	interface MasterView2UiBinder extends UiBinder<Widget, MasterView2> {
	}

	@UiField Button makeNew;
	@UiField DataList<FakeObject> dataList;
	
	private Inbox.Master activity;
	
	public MasterView2(Inbox.Master activity) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.activity = activity;
		
		dataList.setCellAdapter(new SelectableCellAdapter<FakeObject>() {

			@Override
			public SafeHtml getNorth(FakeObject object) {
				return new SafeHtmlBuilder().appendEscaped(object.getA()).toSafeHtml();
			}
			
		});
		
		ListDataProvider<FakeObject> provider = new ListDataProvider<FakeObject>();
		List<FakeObject> list = provider.getList();
		list.add(new FakeObject("A"));
		list.add(new FakeObject("B"));
		list.add(new FakeObject("C"));
		list.add(new FakeObject("D"));
		dataList.setDataProvider(provider);
		provider.flush();
		
	}

	@UiHandler("makeNew")
	void handleNew(ClickEvent e) {
		activity.openNewDialog();
	}
	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e) {
		Window.alert("Hello");
	}
}
