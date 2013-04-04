package it.unisannio.catman.client;


import it.unisannio.catman.client.Screen.HasDetail;
import it.unisannio.catman.client.Screen.HasMaster;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class ScreenActivityMapper implements ActivityMapper {
	public static enum Panel { MASTER, DETAIL; }
	
	private final Panel panel;
	
	public ScreenActivityMapper(Panel panel) {
		this.panel = panel;
	}
	
	@Override
	public Activity getActivity(Place place) {
		if(!(place instanceof Trail))
			return null;
		
		Trail trail = (Trail) place;
		Intent in = null;
		Screen s = null;
		
		switch(panel) {
		case MASTER:
			in = trail.getMaster();
			if(in == null)
				return null;

			s = in.getScreen();
			if(s instanceof HasMaster) {
				return ((HasMaster) s).getMaster(in);
			}
			break;
			
		case DETAIL:
			in = trail.getDetail();
			if(in == null)
				return null;
			
			s = in.getScreen();
			if(s instanceof HasDetail) {
				return ((HasDetail) s).getDetail(in);
			}
			break;
		}
		
		return null;
	}

}
