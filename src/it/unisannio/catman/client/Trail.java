
package it.unisannio.catman.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix; 

public class Trail extends Place {
	public static Trail to(Intent in) {
		PlaceController pc = App.getInstance().getPlaceController();
		Trail current = (Trail) pc.getWhere();
		
		return new Trail(current, in);
	}
	
	public static Trail to(Screen s, String... params) {
		return to(new Intent(s).withParams(params));
	}
	
	public static Trail to(String s, String... params) {
		return to(new Intent(s).withParams(params));
	}
	
	private final Trail prev;
	private final Intent current;
	
	public Trail(Trail prev, Intent current) {
		this.prev = prev;
		this.current = current;
	}
	
	public Trail(Trail prev, String current) {
		this(prev, new Intent(current));
	}
	
	public Trail(Intent current) {
		this(null, current);
	}
	
	public Trail(Intent... trail) {
		Trail t = null;
		for(int i = 0; i < trail.length - 1; ++i) {
			t = new Trail(t, trail[i]);
		}
		prev = t;
		current = trail[trail.length - 1];
	}
	
	public Intent peek() {
		return current;
	}
	
	public Intent peek(int levels) {
		if(levels < 0)
			throw new IllegalArgumentException("Levels can only be positive, got " + levels);
		
		if(levels == 0)
			return peek();
		
		if(prev == null)
			return null;
		
		return prev.peek(levels - 1);
	}
	
	public Trail push(Intent i) {
		return new Trail(this, i);
	}
	
	public Trail pop() {
		return prev;
	}
	
	public int size() {
		return (prev == null) ? 1 : prev.size() + 1;
	}
	
	public String getToken() {
		PlaceHistoryMapper phm = App.getInstance().getPlaceHistoryMapper();
		return phm.getToken(this);
	}

	@Prefix("")
	public static class Tokenizer implements PlaceTokenizer<Trail> {

		@Override
		public Trail getPlace(String token) {
			String[] intents = token.split("/");
			
			Trail t = null;
			for(String intent : intents) {
				String[] parts = intent.split(":");
				Intent in = new Intent(parts[0]);
				for(int i = 1; i < parts.length; ++i) {
					if(!parts[i].isEmpty())
						in.put(i, parts[i]);
				}
				t = new Trail(t, in);	
			}
			
			return t;
		}

		@Override
		public String getToken(Trail place) {
			StringBuffer buf = new StringBuffer();
			
			Trail prev = place.pop();
			if(prev != null) {
				buf.append(getToken(prev));
				buf.append('/');
			}
			
			
			
			Intent intent = place.peek();
			buf.append(intent.getScreen().getSlug());
			if(!intent.isEmpty()) {
				int num = intent.lastKey();
				for(int i = 0; i <= num; ++i) {
					buf.append(':');
					if(intent.containsKey(i))
						buf.append(intent.get(i));
					
				}
			}
			
			return buf.toString();
			//return token.startsWith("/") ? token.substring(1) : token;
		}
		
	}
}
