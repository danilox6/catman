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
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Request;

public class DetailView2 extends Composite {

	private static DetailActivity2UiBinder uiBinder = GWT.create(DetailActivity2UiBinder.class);

	interface DetailActivity2UiBinder extends UiBinder<Widget, DetailView2> {}

	
	@UiField DataList<CustomerProxy> dataList;
	
	public DetailView2() {
		initWidget(uiBinder.createAndBindUi(this));
		
		dataList.setCellAdapter(new SelectableCellAdapter<CustomerProxy>() {

			@Override
			public SafeHtml getNorth(CustomerProxy object) {
				//return new SafeHtmlBuilder().appendEscaped("Lorem ipsum").toSafeHtml();
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

}
