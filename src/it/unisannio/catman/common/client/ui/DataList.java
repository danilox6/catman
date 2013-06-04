package it.unisannio.catman.common.client.ui;

import java.util.List;

import it.unisannio.catman.common.client.QueryDataProvider;
import it.unisannio.catman.common.client.cell.CellAdapter;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.cell.SelectableCellAdapter;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.cellview.client.AbstractPager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AbstractDataProvider;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.SelectionModel;
import com.google.web.bindery.requestfactory.shared.EntityProxy;

public class DataList<T extends EntityProxy> extends Composite implements HasClickHandlers, HasChangeHandlers {
	
	/** 
     * A scrolling pager that automatically increases the range every time the 
     * scroll bar reaches the bottom. 
     */ 
    public class ScrollingPager extends AbstractPager { 
        private int incrementSize = 20; 
        private int lastScrollPos = 0; 
        private final ScrollPanel scrollable = new ScrollPanel(); 

        public ScrollingPager() { 
            initWidget(scrollable); 
            //scrollable.setPixelSize(250, 350); // FIXME 
            scrollable.getElement().setTabIndex(-1); 
            
            // Handle scroll events. 
            scrollable.addScrollHandler(new ScrollHandler() { 
                public void onScroll(ScrollEvent p_event) { 
                    // If scrolling up, ignore the event. 
                    int oldScrollPos = lastScrollPos; 
                    lastScrollPos = scrollable.getVerticalScrollPosition(); 
                    if (oldScrollPos >= lastScrollPos) 
                        return; 
                     
                    HasRows display = getDisplay(); 
                    if (display == null) 
                        return; 
                     
                    int maxScrollTop = scrollable.getWidget().getOffsetHeight()  - scrollable.getOffsetHeight(); 
                    if (lastScrollPos >= maxScrollTop) { 
                        // We are near the end, so increase the page size. 
//                        int newPageSize = Math.min(display.getVisibleRange().getLength() + incrementSize, display.getRowCount()); 
                        executeIncrement(); 
                    } 
                } 
            });
        } 

        public int getIncrementSize() { 
            return incrementSize; 
        } 

        @Override 
        public void setDisplay(HasRows display) { 
            assert display instanceof Widget : "display must extend Widget"; 
            scrollable.setWidget((Widget)display); 
            super.setDisplay(display); 
        } 

        public void setIncrementSize(int incrementSize) { 
            this.incrementSize = incrementSize; 
        } 

        @Override 
        protected void onRangeOrRowCountChanged() { 
        }
        
        public void executeIncrement(){
        	if(getDisplay()!=null)
        		getDisplay().setVisibleRange(0, getDisplay().getVisibleRange().getLength() + incrementSize);
        }
    } 

	private MasterCell<T> cell;
	private CellList<T> cellList;
	private ScrollingPager pager;
	
	private SelectionModel<? super T> selectionModel;
	private SimplePanel parentPanel;
	
	private CellAdapter<T> adapter;
	private AbstractDataProvider<T> provider;
	private final int DEFAULT_PAGER_SIZE = 20;
	
	
	public DataList() {
		this(null);
	}
	
	public DataList(CellAdapter<T> cellAdapter) {
		cell = new MasterCell<T>(cellAdapter); 
		cellList = new CellList<T>(cell); 
		
		pager = new ScrollingPager();
		pager.setDisplay(cellList);
		pager.setHeight("100%");
		pager.setWidth("100%");
		cellList.setWidth("100%");
		
		setPageSize(DEFAULT_PAGER_SIZE);
		initWidget(pager);
		
		
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return cell.addClickHandler(handler);
	}
	
	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return cell.addChangeHandler(handler);
	}

	/*
	public void setDataProvider(AbstractDataProvider<T> provider) {
		this.provider = provider;
		this.provider.addDataDisplay(cellList);
	}
	*/
	
	
	public void setSelectionModel(SelectionModel<? super T> selectionModel) {
		this.selectionModel = selectionModel;
		if(adapter instanceof SelectableCellAdapter && selectionModel!=null)
			((SelectableCellAdapter<T>)this.adapter).setSelectionModel(selectionModel);
		cellList.setSelectionModel(selectionModel,DefaultSelectionEventManager.<T>createCheckboxManager());
	}
	
	@SuppressWarnings("unchecked")
	public void setDataProvider(AbstractDataProvider<T> provider) {
		this.provider = provider;
		this.provider.addDataDisplay(cellList);
		if(provider instanceof QueryDataProvider){
			setSelectionModel((SelectionModel<? super T>) provider);
			((QueryDataProvider<T>) provider).reload();
		}
	}
	
	public void setCellAdapter(CellAdapter<T> adapter) {
		this.adapter = adapter;
		if(adapter instanceof SelectableCellAdapter && selectionModel!=null)
			((SelectableCellAdapter<T>)this.adapter).setSelectionModel(selectionModel);
		cell.setCellAdapter(adapter);
	}
	
	public void setPageSize(int size){
		cellList.setPageSize(size);
		pager.setIncrementSize(size);
	}
	
	public void setCellType(MasterCell.Type type) {
		cell.setType(type);
		cellList.redraw();
	}
	
	class CellList<E extends EntityProxy> extends com.google.gwt.user.cellview.client.CellList<E>{

		private int previousHeight = 0;
		
		public CellList(Cell<E> cell) {
			super(cell);
		}
		
		@Override
		public void setRowData(int start, List<? extends E> values) {
			
			super.setRowData(start, values);
			parentPanel = (SimplePanel) this.getParent();
			//Window.alert(values.isEmpty() + " " +parentPanel.getOffsetHeight() + " " + this.getOffsetHeight());
			if(this.getOffsetHeight()!=0 && this.getOffsetHeight()<=parentPanel.getOffsetHeight()){
				if(previousHeight !=  this.getOffsetHeight()){
					previousHeight =  this.getOffsetHeight();
					pager.executeIncrement();
				}
			}
		}
		
	}

	public void redraw() {
		cellList.redraw();
	}
	
}
