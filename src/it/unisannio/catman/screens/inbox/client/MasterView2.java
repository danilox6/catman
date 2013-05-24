package it.unisannio.catman.screens.inbox.client;

import java.util.List;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.cell.SelectableCellAdapter;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.workflow.client.CustomerProxy;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.domain.workflow.client.EventRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.GwtEvent;
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
import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.Request;

public class MasterView2 extends Composite {

	private static MasterView2UiBinder uiBinder = GWT.create(MasterView2UiBinder.class);

	interface MasterView2UiBinder extends UiBinder<Widget, MasterView2> {
	}

	@UiField
	Button makeNew;
	@UiField 
	DataList<EventProxy> dataList;

	private Inbox.Master activity;

	public MasterView2(Inbox.Master activity) {
		initWidget(uiBinder.createAndBindUi(this));

		this.activity = activity;

		dataList.setCellAdapter(new SelectableCellAdapter<EventProxy>() {

			@Override
			public SafeHtml getNorth(EventProxy object) {
				return new SafeHtmlBuilder().appendEscaped(object.getTitle()).toSafeHtml();
			}

		});

		final EventRequest rp = App.getInstance().getDataStore().events();

		Query<EventProxy> query = new Query<EventProxy>() {

			@Override
			public Request<List<EventProxy>> list(int start, int length) {
				return rp.listAll(start, length);
			}

			@Override
			public Request<Integer> count() {
				return rp.count();
			}

			@Override
			public Request<Void> deleteAll(List<EventProxy> skip) {
				throw new UnsupportedOperationException(); // FIXME
			}

			@Override
			public Request<Void> deleteSet(List<EventProxy> set) {
				throw new UnsupportedOperationException(); // FIXME
			}
		};

		// dataList.setDataProvider(new QueryDataProvider<EventProxy>(query));

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
