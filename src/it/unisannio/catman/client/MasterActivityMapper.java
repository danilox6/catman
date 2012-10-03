package it.unisannio.catman.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class MasterActivityMapper implements ActivityMapper {

	@Override
	public Activity getActivity(Place place) {
		if(!(place instanceof UnitPlace))
			return null;
		
		UnitPlace up = (UnitPlace) place;
		Unit unit = up.getUnit();
		
		String master = up.getMaster();
		return (master == null) ? unit.getDefaultMasterActivity() : unit.getMasterActivity(master);
	}

}
