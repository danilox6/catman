package it.unisannio.catman.screens.eventmanager.client;

import java.util.List;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;
import it.unisannio.catman.common.client.Query;
import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.cell.SelectableCellAdapter;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.common.client.ui.SelectAllButton;
import it.unisannio.catman.common.client.widget.DatePickerPopupPanel;
import it.unisannio.catman.domain.workflow.client.EventProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Request;

public class MasterView extends Composite {

	private static MasterView2UiBinder uiBinder = GWT.create(MasterView2UiBinder.class);

	interface MasterView2UiBinder extends UiBinder<Widget, MasterView> {}

	@UiField DataList<EventProxy> dataList;
	@UiField Button calendarButton;
	@UiField SelectAllButton selectButton;
	
	private DatePickerPopupPanel datePickerPopupPanel = new DatePickerPopupPanel(true);
	private QueryDataProvider<EventProxy> dataProvider;

	public MasterView() {
		initWidget(uiBinder.createAndBindUi(this));

		dataList.setCellAdapter(new SelectableCellAdapter<EventProxy>(dataProvider) {

			@Override
			public SafeHtml getNorth(EventProxy object) {
				return new SafeHtmlBuilder().appendEscaped(object.getTitle()).toSafeHtml();
			}
			
			@Override
			public SafeHtml getEast(EventProxy object) {
				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				sb.appendHtmlConstant("<input id='selection' type='checkbox'" + (isSelected(object)?"checked='checked'":"") + "/>");
				return sb.toSafeHtml();
			}
		});

		final DataStore store = App.getInstance().getDataStore();

		Query<EventProxy> query = new Query<EventProxy>() {

			@Override
			public Request<List<EventProxy>> list(int start, int length) {
				return store.events().listAll(start, length);
			}

			@Override
			public Request<Integer> count() {
				return store.events().count();
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

		dataProvider = new QueryDataProvider<EventProxy>(query);
		selectButton.setDataProvider(dataProvider);
		
		dataList.setDataProvider(dataProvider);
	}
	
	@UiHandler("calendarButton")
	protected void handleCalendarButtonClick(ClickEvent event){
		int left = calendarButton.getAbsoluteLeft() + 5 ;
		int bottom = calendarButton.getAbsoluteTop() + 0; 
		datePickerPopupPanel.setLeftBottomPosition(left, bottom);
		datePickerPopupPanel.show();
	}
	
	@UiHandler("dataList")
	void handleCellClick(ClickEvent e) {

	}

}
