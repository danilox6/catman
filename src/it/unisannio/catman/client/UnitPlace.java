package it.unisannio.catman.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix; 

public class UnitPlace extends Place {

	@Prefix("unit")
	public static class Tokenizer implements PlaceTokenizer<UnitPlace> {

		@Override
		public UnitPlace getPlace(String token) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getToken(UnitPlace place) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
