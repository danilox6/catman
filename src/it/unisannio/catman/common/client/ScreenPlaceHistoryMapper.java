package it.unisannio.catman.common.client;

import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceHistoryMapper;

public class ScreenPlaceHistoryMapper implements PlaceHistoryMapper {

	@Override
	public Path getPlace(String token) {
		Path t = null;

		String[] intents = token.split("/");
		
		for(String intent : intents) {
			String[] parts = intent.split("\\:");
			Intent in = new Intent(parts[0]);
			for(int i = 1; i < parts.length; ++i) {
				if(!parts[i].isEmpty())
					in.put(i - 1, URL.decode(parts[i]));
			}
			t = new Path(t, in);	
		}
		
		return t;
	}

	@Override
	public String getToken(Place p) {
		Path place = (Path) p;
		StringBuffer buf = new StringBuffer();
		
		Path prev = place.pop();
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
					buf.append(URL.encode(intent.get(i)));
				
			}
		}

		return buf.toString();
	}
	
}
