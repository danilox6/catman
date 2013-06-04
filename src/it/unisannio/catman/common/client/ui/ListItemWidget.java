package it.unisannio.catman.common.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
 
public class ListItemWidget extends SimplePanel {
    public ListItemWidget() {
        super((Element) Document.get().createLIElement().cast());
    }
    
    public ListItemWidget(Widget w) {
        this();
        this.add(w);
    }
 
    public ListItemWidget(String s) {
        this();
        getElement().setInnerText(s);
    }
    
    public void setId(String id) {
        // Set an attribute common to all tags
        getElement().setId(id);
    }
 
   
}
