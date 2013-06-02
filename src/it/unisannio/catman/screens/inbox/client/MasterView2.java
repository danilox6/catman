package it.unisannio.catman.screens.inbox.client;

import java.util.List;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.cell.SelectableCellAdapter;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.workflow.client.CustomerProxy;
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
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Request;

public class MasterView2 extends Composite {

	private static MasterView2UiBinder uiBinder = GWT.create(MasterView2UiBinder.class);

	interface MasterView2UiBinder extends UiBinder<Widget, MasterView2> {
	}

	@UiField Button makeNew;
	
	@UiField DataList<CustomerProxy> dataList;
	
	private Inbox.Master activity;

	public MasterView2(Inbox.Master activity) {
		initWidget(uiBinder.createAndBindUi(this));

		this.activity = activity;
		
		dataList.setPageSize(20);
		
		dataList.setCellAdapter(new SelectableCellAdapter<CustomerProxy>() {

			@Override
			public SafeHtml getNorth(CustomerProxy object) {
				return new SafeHtmlBuilder().appendEscaped(object.getName()).toSafeHtml();
			}

		});
		
		final DataStore store = App.getInstance().getDataStore();
		
		Query<CustomerProxy> query = new Query<CustomerProxy>() {

			@Override
			public Request<List<CustomerProxy>> list(int start, int length) {
				return store.customers().listAll(start, length);
			}

			@Override
			public Request<Integer> count() {
				return store.customers().count();
			}

			@Override
			public Request<Void> deleteAll(List<CustomerProxy> skip) {
				throw new UnsupportedOperationException(); // FIXME
			}

			@Override
			public Request<Void> deleteSet(List<CustomerProxy> set) {
				throw new UnsupportedOperationException(); // FIXME
			}
		};
		
		dataList.setDataProvider(new QueryDataProvider<CustomerProxy>(query));
		
		
	}

	@UiHandler("makeNew")
	void handleNew(ClickEvent e) {
		activity.openNewDialog();
	}

	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e) {
		Window.alert("Hello "+ ((CustomerProxy) e.getSource()).getName());
	}
	
}
