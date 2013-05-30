package it.unisannio.catman.common.client;


import it.unisannio.catman.common.client.Screen.HasDetail;
import it.unisannio.catman.common.client.Screen.HasMaster;

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
		
		if(!(place instanceof Path))
			return null;
		
		Path path = (Path) place;
		Intent in = null;
		Screen s = null;
		
		switch(panel) {
		case MASTER:
			in = path.getMaster();
			if(in == null)
				return null;

			s = in.getScreen();
			if(s instanceof HasMaster) {
				return setTrail(((HasMaster) s).getMaster(in), path.getMasterPath());
			}
			break;
			
		case DETAIL:
			in = path.getDetail();
			if(in == null)
				return null;
			
			s = in.getScreen();
			if(s instanceof HasDetail) {
				return setTrail(((HasDetail) s).getDetail(in), path);
			}
			break;
		}
		
		return null;
	}
	
	private Activity setTrail(Activity a, Path t) {
		if(a instanceof ScreenActivity)
			((ScreenActivity) a).setPath(t);
		
		return a;
	}

}
