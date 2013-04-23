package it.unisannio.catman.common.client.widget;

import java.util.List;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.Range;
import com.google.gwt.user.cellview.client.CellList.Resources;
import com.google.gwt.view.client.RangeChangeEvent.Handler;
import com.google.gwt.view.client.RowCountChangeEvent;
import com.google.gwt.view.client.SelectionModel;

public class DetailSectionCellList<T> extends Composite implements HasData<T>{

	private static DetailSectionCellListUiBinder uiBinder = GWT.create(DetailSectionCellListUiBinder.class);

	interface DetailSectionCellListUiBinder extends UiBinder<Widget, DetailSectionCellList<?>> {} 
 
	@UiField protected Label titleLabel;
	@UiField protected SimplePanel contentPanel;
	protected CellList<T> cellList;
	
	public DetailSectionCellList(String title, Cell<T> cell){
		this(title, cell, getDefaultResources(), null);
	}
	
	public DetailSectionCellList(String title, Cell<T> cell, Resources resources) {
		this(title, cell, resources, null);
	}
	
	public DetailSectionCellList(String title, Cell<T> cell, ProvidesKey<T> keyProvider) {
		this(title, cell, getDefaultResources(), keyProvider);
	}
	
	public DetailSectionCellList(String title, Cell<T> cell, Resources resources, ProvidesKey<T> keyProvider) {
		initWidget(uiBinder.createAndBindUi(this));
		setTitle(title);
		cellList = new CellList<T>(cell, resources, keyProvider);
		contentPanel.add(cellList);
	}
	
	public void setTitle(String title){
		titleLabel.setText(title);
	}
	
	public CellList<T> getUnderlyingCellList(){
		return cellList;
	}

	@Override
	public HandlerRegistration addRangeChangeHandler(Handler handler) {
		return cellList.addRangeChangeHandler(handler);
	}

	@Override
	public HandlerRegistration addRowCountChangeHandler(RowCountChangeEvent.Handler handler) {
		return cellList.addRowCountChangeHandler(handler);
	}

	@Override
	public int getRowCount() {
		return cellList.getRowCount();
	}

	@Override
	public Range getVisibleRange() {
		return cellList.getVisibleRange();
	}

	@Override
	public boolean isRowCountExact() {
		return cellList.isRowCountExact();
	}

	@Override
	public void setRowCount(int count) {
		cellList.setRowCount(count);
	}

	@Override
	public void setRowCount(int count, boolean isExact) {
		cellList.setRowCount(count, isExact);
		
	}

	@Override
	public void setVisibleRange(int start, int length) {
		cellList.setVisibleRange(start, length);
	}

	@Override
	public void setVisibleRange(Range range) {
		cellList.setVisibleRange(range);
	}

	@Override
	public HandlerRegistration addCellPreviewHandler(CellPreviewEvent.Handler<T> handler) {
		return cellList.addCellPreviewHandler(handler);
	}

	@Override
	public SelectionModel<? super T> getSelectionModel() {
		return cellList.getSelectionModel();
	}

	@Override
	public T getVisibleItem(int indexOnPage) {
		return cellList.getVisibleItem(indexOnPage);
	}

	@Override
	public int getVisibleItemCount() {
		return cellList.getVisibleItemCount();
	}

	@Override
	public Iterable<T> getVisibleItems() {
		return cellList.getVisibleItems();
	}

	@Override
	public void setRowData(int start, List<? extends T> values) {
		cellList.setRowData(start, values);
	}

	@Override
	public void setSelectionModel(SelectionModel<? super T> selectionModel) {
		cellList.setSelectionModel(selectionModel);
	}

	@Override
	public void setVisibleRangeAndClearData(Range range, boolean forceRangeChangeEvent) {
		cellList.setVisibleRangeAndClearData(range, forceRangeChangeEvent);
	}
	
	private static Resources getDefaultResources() {
		return GWT.create(Resources.class);
	}

}
