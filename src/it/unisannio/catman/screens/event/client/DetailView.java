package it.unisannio.catman.screens.event.client;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.ui.DataList;
import it.unisannio.catman.common.client.ui.MasterPanel;
import it.unisannio.catman.domain.planning.client.PlanProxy;
import it.unisannio.catman.domain.workflow.client.EventDocumentProxy;
import it.unisannio.catman.domain.workflow.client.EventProxy;
import it.unisannio.catman.screens.event.client.Event.Presenter;
import it.unisannio.catman.screens.event.client.adapters.DocumentCellAdapter;
import it.unisannio.catman.screens.event.client.queries.EventDocumentQuery;

import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.Paragraph;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DetailView extends Composite implements Event.View {

	private static DetailViewUiBinder uiBinder = GWT.create(DetailViewUiBinder.class);

	interface DetailViewUiBinder extends UiBinder<Widget, DetailView> {}

	private EventProxy eventProxy;

	@UiField MasterPanel masterPanel;
	@UiField Heading titleLabel;
	@UiField NavLink addPlan;
	@UiField DataList<EventDocumentProxy> documentsDataList;
	
	@UiField Heading eventName;
	@UiField Paragraph customerName;
	
	@UiField Heading startDay;
	@UiField Paragraph startMonth;
	
	@UiField Heading endDay;
	@UiField Paragraph endMonth;
	
	private Presenter presenter;
	
	public DetailView() {
		initWidget(uiBinder.createAndBindUi(this));
		documentsDataList.setCellAdapter(new DocumentCellAdapter());
	}

	private static class DateFormat extends DateTimeFormat{

		public DateFormat(String pattern) {
			super(pattern);
		}
	}
	
	@Override
	public void setEventProxy(EventProxy eventProxy) {
		this.eventProxy = eventProxy; 
		
		titleLabel.setText(eventProxy.getTitle());
		eventName.setText(eventProxy.getTitle());
		

		DateFormat MONTH_F = new DateFormat("MMM");
		DateFormat DAY_F = new DateFormat("dd");  
		startDay.setText(DAY_F.format(eventProxy.getStartDate()));
		startMonth.setText(MONTH_F.format(eventProxy.getStartDate()));
		
		endDay.setText(DAY_F.format(eventProxy.getEndDate()));
		endMonth.setText(MONTH_F.format(eventProxy.getEndDate()));
	}

	public EventProxy getEventProxy() {
		return eventProxy;
	}
	
	@UiHandler("documentsDataList")
	void handleCellClick(ClickEvent event) {
		PlanProxy plan = (PlanProxy) event.getSource();
		presenter.goToPlan(plan);
		
	}

	@UiHandler("addPlan")
	void handleAddButton(ClickEvent event){
		presenter.addPlan();
	}	

	@Override
	public void setDocumentQuery(EventDocumentQuery edq) {
		documentsDataList.setDataProvider(new QueryDataProvider<EventDocumentProxy>(edq));	
	}

	@Override
	public void refresh() {
		addPlan.setDisabled(true);
		documentsDataList.reload();
	}

	@Override
	public void setPresenter(Presenter p) {
		this.presenter = p;
		
	}
}
