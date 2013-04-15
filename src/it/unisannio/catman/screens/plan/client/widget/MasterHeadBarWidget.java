package it.unisannio.catman.screens.plan.client.widget;

import com.google.gwt.user.client.ui.ListBox;

public class MasterHeadBarWidget extends ListBox {
	
	public static enum Selection {ROLES, MATERIALS}
	
	public MasterHeadBarWidget() {
		addItem("Ruoli"); //Index 0 -> Selection.ROLES
		addItem("Materiali"); //Index 1 -> Selection.MATERIALS
		
		setWidth("100%");
	}
	
	public Selection getSelection(){
		if(getSelectedIndex() == 0)
			return Selection.ROLES;
		return Selection.MATERIALS;
	}
}
