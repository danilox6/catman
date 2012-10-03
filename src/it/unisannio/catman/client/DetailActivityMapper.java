package it.unisannio.catman.client;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class DetailActivityMapper implements ActivityMapper {

	@Override
	public Activity getActivity(Place place) {
		if(!(place instanceof UnitPlace))
			return null;
		
		UnitPlace up = (UnitPlace) place;
		Unit unit = up.getUnit();
		
		String detail = up.getDetail();
		if(detail == null)
			return null;
		
		return unit.getDetailActivity(detail, up.getId());
	}

}
