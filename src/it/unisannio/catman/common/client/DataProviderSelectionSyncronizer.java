package it.unisannio.catman.common.client;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.RangeChangeEvent.Handler;
import com.google.gwt.view.client.SelectionModel;

/**
 * Keeps a {@link MultiSelectionModel} syncronized with a {@link ListDataProvider}.
 */
public class DataProviderSelectionSyncronizer<T> implements HasData<T> { //FIXME nome brutto

	private MultiSelectionModel<T> selectionModel;
	private ListDataProvider<T> dataProvider;
	
	public static<T> void sync(MultiSelectionModel<T> selectionModel, ListDataProvider<T> dataProvider){
		dataProvider.addDataDisplay(new DataProviderSelectionSyncronizer<T>(selectionModel, dataProvider));
	}
	
	private DataProviderSelectionSyncronizer(MultiSelectionModel<T> selectionModel, ListDataProvider<T> dataProvider) {
		this.selectionModel = selectionModel;
		this.dataProvider = dataProvider;
	}
	
	@Override
	public Range getVisibleRange() {
		return new Range(0, dataProvider.getList().size());
	}
	
	@Override
	public void setRowData(int start, List<? extends T> values) {
		for(T value : selectionModel.getSelectedSet())
			if(!values.contains(value))
				selectionModel.setSelected(value, false);
	}

	@Override
	public HandlerRegistration addRangeChangeHandler(Handler handler) {
		return new HandlerRegistration() {
			public void removeHandler() {}
		};
	}
	@Override
	public void setRowCount(int count, boolean isExact) {}

	
	@Override
	public HandlerRegistration addRowCountChangeHandler(com.google.gwt.view.client.RowCountChangeEvent.Handler handler) {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
	}

	@Override
	public int getRowCount() {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
	}

	@Override
	public boolean isRowCountExact() {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
	}

	@Override
	public void setRowCount(int count) {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
		
	}

	@Override
	public void setVisibleRange(int start, int length) {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
		
	}

	@Override
	public void setVisibleRange(Range range) {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
		
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
		
	}

	@Override
	public HandlerRegistration addCellPreviewHandler(com.google.gwt.view.client.CellPreviewEvent.Handler<T> handler) {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
	}

	@Override
	public SelectionModel<? super T> getSelectionModel() {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
	}

	@Override
	public T getVisibleItem(int indexOnPage) {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
	}

	@Override
	public int getVisibleItemCount() {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
	}

	@Override
	public Iterable<T> getVisibleItems() {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
	}

	@Override
	public void setSelectionModel(SelectionModel<? super T> selectionModel) {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
		
	}

	@Override
	public void setVisibleRangeAndClearData(Range range, boolean forceRangeChangeEvent) {
		throw new IllegalStateException("This is a dummy HasData and shouldn't be used");
	}

}
