package it.unisannio.catman.screens.inbox.client;

import java.util.List;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.cell.SelectableCellAdapter;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.domain.equipment.client.SupplierProxy;
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

	private static MasterView2UiBinder uiBinder = GWT
			.create(MasterView2UiBinder.class);

	interface MasterView2UiBinder extends UiBinder<Widget, MasterView2> {
	}

	@UiField Button makeNew;
	@UiField DataList<SupplierProxy> dataList;
	
	private Inbox.Master activity;
	
	public MasterView2(Inbox.Master activity) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.activity = activity;
		
		dataList.setCellAdapter(new SelectableCellAdapter<SupplierProxy>() {

			@Override
			public SafeHtml getNorth(SupplierProxy object) {
				return new SafeHtmlBuilder().appendEscaped(object.getName()).toSafeHtml();
			}
			
		});
		
		final DataStore store = App.getInstance().getDataStore();
		
		Query<SupplierProxy> query = new Query<SupplierProxy>() {

			@Override
			public Request<List<SupplierProxy>> list(int start, int length) {
				return store.suppliers().listAll(start, length);
			}

			@Override
			public Request<Integer> count() {
				return store.events().count();
			}

			@Override
			public Request<Void> deleteAll(List<SupplierProxy> skip) {
				throw new UnsupportedOperationException(); // FIXME
			}

			@Override
			public Request<Void> deleteSet(List<SupplierProxy> set) {
				throw new UnsupportedOperationException(); // FIXME
			}
		};
		
		dataList.setDataProvider(new QueryDataProvider<SupplierProxy>(query));
		
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
