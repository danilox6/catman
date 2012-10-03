
package it.unisannio.catman.client;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix; 

public class UnitPlace extends Place {
	
	private final Unit unit;
	private final String master;
	private final String detail;
	private final long id;
	
	public UnitPlace(Unit unit, String master, String detail, long id) {
		this.unit = unit;
		this.master = master;
		this.detail = detail;
		this.id = id;
	}
	
	public UnitPlace(Unit unit, String master) {
		this(unit, master, null, 0);
	}
	
	public UnitPlace(Unit unit) {
		this(unit, null, null, 0);
	}

	public Unit getUnit() {
		return unit;
	}

	public String getMaster() {
		return master;
	}

	public String getDetail() {
		return detail;
	}

	public long getId() {
		return id;
	}



	@Prefix("unit")
	public static class Tokenizer implements PlaceTokenizer<UnitPlace> {
		private static Pattern PATTERN_UNIT = Pattern.compile("^([a-z]+)$");
		private static Pattern PATTERN_FULL = Pattern.compile("^([a-z]+)/([a-z]+)/([a-z]+)/(\\d+))$");
		private static Pattern PATTERN_MASTER = Pattern.compile("^([a-z]+)/([a-z]+)$");
		private static Pattern PATTERN_DETAIL = Pattern.compile("^([a-z]+)/([a-z]+)/(\\d+)$");

		@Override
		public UnitPlace getPlace(String token) {
			Matcher matcher = null;
			
			for(Pattern p : Arrays.asList(new Pattern[]{ PATTERN_FULL, PATTERN_DETAIL, PATTERN_MASTER, PATTERN_UNIT })) {
				Matcher m = p.matcher(token);
				if(m.matches()) {
					matcher = m;
					break;
				}
			}
			
			if(matcher != null) {
				Unit unit = Application.get().getUnit(matcher.group(1));
				switch(matcher.groupCount()) {
				case 4: return new UnitPlace(unit, matcher.group(2), matcher.group(3), Long.parseLong(matcher.group(4)));
				case 3: return new UnitPlace(unit, null, matcher.group(2), Long.parseLong(matcher.group(3)));
				case 2: return new UnitPlace(unit, matcher.group(2));
				case 1: return new UnitPlace(unit);
				}
			}
			
			return null;
		}

		@Override
		public String getToken(UnitPlace place) {
			StringBuilder buf = new StringBuilder();
			buf.append(place.getUnit().getName());
			if(place.getMaster() != null)
				buf.append('/').append(place.getMaster());
			
			if(place.getDetail() != null)
				buf.append('/').append(place.getDetail()).append('/').append(place.getId());
			
			return buf.toString();
			
		}
		
	}
}
