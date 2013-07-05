package it.unisannio.catman.screens.event.client.widgets;

import java.util.Date;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * A {@link PopupPanel} containing a {@link DatePicker} that can be positioned via
 * bottom-left coordinates. It handles window resize.
 *
 */
public class DatePickerPopupPanel extends PopupPanel implements HasValueChangeHandlers<Date>{

	private DatePicker datePicker = new DatePicker();
	private int previousClientHeight;
	
	public DatePickerPopupPanel(){
		super();
		initialize();
	}

	 public DatePickerPopupPanel(boolean autoHide){
		 super(autoHide);
		 initialize();
	 }
	 public DatePickerPopupPanel(boolean autoHide, boolean modal){
		 super(autoHide, modal);
		 initialize();
	 }
	 
	 private void initialize(){
		 this.add(datePicker);
		 previousClientHeight = Window.getClientHeight();
		 Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				setPopupPosition(getPopupLeft(), getPopupTop() + ( event.getHeight() - previousClientHeight));
				previousClientHeight = event.getHeight();
			}
		});
	 }
	 
	 public void setLeftBottomPosition(int left, int bottom){
		 setPopupPosition(left, bottom - datePicker.getOffsetHeight());
	 }
	 
	 @Override
	protected void onLoad() {
		setPopupPosition(getPopupLeft(), getPopupTop() - datePicker.getOffsetHeight());
	}
	 
	 public DatePicker getDatePicker(){
		 return datePicker;
	 }

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
		return datePicker.addValueChangeHandler(handler);
	}
}
