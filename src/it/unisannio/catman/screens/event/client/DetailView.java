package it.unisannio.catman.screens.event.client;

import java.util.List;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.common.client.cell.CellAdapter;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.common.client.ui.DetailSection;
import it.unisannio.catman.common.client.ui.MasterPanel;
import it.unisannio.catman.domain.workflow.client.CustomerProxy;
import it.unisannio.catman.domain.workflow.client.EventProxy;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Request;

public class DetailView extends Composite implements Event.Detail.View {
	interface Presenter{}

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {}

	private EventProxy eventProxy;

	@UiField MasterPanel masterPanel;
	@UiField Heading titleLabel;
	@UiField Button addButton;
	@UiField DataList<CustomerProxy> sellsDataList;
	@UiField DataList<CustomerProxy> logisticDataList;

	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));

		CellAdapter<CustomerProxy> adapter = new AbstractCellAdapter<CustomerProxy>() {
			@Override
			public SafeHtml getNorth(CustomerProxy object) {
				return new SafeHtmlBuilder().appendEscaped(object.getName()).toSafeHtml();
			}
		};
		

		sellsDataList.setCellAdapter(adapter);
		logisticDataList.setCellAdapter(adapter);
		
		sellsDataList.setPageSize(5);
		logisticDataList.setPageSize(5);
		
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				adaptHeight();
			}
		});
		
		final DataStore store = App.getInstance().getDataStore();

		Query<CustomerProxy> query1 = new Query<CustomerProxy>() {

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

		sellsDataList.setDataProvider(new QueryDataProvider<CustomerProxy>(query1));
		
		
		Query<CustomerProxy> query2 = new Query<CustomerProxy>() {

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
		
		
		logisticDataList.setDataProvider(new QueryDataProvider<CustomerProxy>(query2));

	}

	@Override
	public void setEventProxy(EventProxy eventProxy) {
		this.eventProxy = eventProxy; 
		titleLabel.setText(eventProxy.getTitle());

		

	}

	public EventProxy getEventProxy() {
		return eventProxy;
	}

	@UiHandler("addButton")
	void handleAddButton(ClickEvent event){

	}

	class MockEventProxy implements EventProxy{

		@Override
		public EntityProxyId<?> stableId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long getId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getTitle() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTitle(String title) {
			// TODO Auto-generated method stub

		}

	}
	
	@Override
	protected void onLoad() {
		adaptHeight();
	}
	
	private void adaptHeight(){ // FIXME Ci torno dopo
		//int height = masterPanel.getContentHeight();
		//sellsSection.setHeight(height/2+"px");
		//logisticSection.setHeight(height/2+"px");
	}
}
