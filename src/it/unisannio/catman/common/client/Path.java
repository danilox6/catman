
package it.unisannio.catman.common.client;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryMapper;

public class Path extends Place {
	public static Path to(Intent in) {
		PlaceController pc = App.getInstance().getPlaceController();
		Path current = (Path) pc.getWhere();
		
		return new Path(current, in);
	}
	
	public static Path to(Screen s, String... params) {
		return to(new Intent(s).withParams(params));
	}
	
	public static Path to(String s, String... params) {
		return to(new Intent(s).withParams(params));
	}
	
	private final Path prev;
	private final Intent current;
	
	private transient int size = -1;
	
	public Path(Path prev, Intent current) {
		this.prev = prev;
		this.current = current;
	}
	
	public Path(Path prev, String current) {
		this(prev, new Intent(current));
	}
	
	public Path(Intent current) {
		this(null, current);
	}
	
	public Path(Intent... trail) {
		Path t = null;
		for(int i = 0; i < trail.length - 1; ++i) {
			t = new Path(t, trail[i]);
		}
		prev = t;
		current = trail[trail.length - 1];
	}
	
	public Intent peek() {
		return current;
	}
	
	public Intent peek(int depth) {
		if(depth < 0)
			throw new IllegalArgumentException("Depth can only be positive, got " + depth);
		
		if(depth == 0)
			return peek();
		
		if(prev == null)
			return null;
		
		return prev.peek(depth - 1);
	}
	
	public Path push(Intent i) {
		return new Path(this, i);
	}
	
	public Path pop() {
		return prev;
	}
	
	public Path pop(int depth) {
		if(depth < 0)
			throw new IllegalArgumentException("Depth can only be positive, got " + depth);
		
		if(depth == 0)
			return pop();
		
		if(prev == null)
			return null;
		
		return prev.pop(depth - 1);
	}
	
	public int size() {
		if(size == -1)
			size = (prev == null) ? 1 : prev.size() + 1;
		
		return size;
	}
	
	public Intent getMaster() {
		if(size() < 2)
			return null;
		
		return peek(size() > 2 ? 1 : 0);
	}
	
	public Path getMasterPath() {
		return (size() > 2) ? pop() : this;
	}
	
	public Intent getDetail() {
		if(size() < 3)
			return null;
		
		return peek();
	}
	
	public Path getMenuPath() {
		return size() == 1 ? this : pop(Math.min(size() - 2, 1));
	}
	
	public String getToken() {
		PlaceHistoryMapper phm = App.getInstance().getPlaceHistoryMapper();
		return phm.getToken(this);
	}
	
	@Override
	public String toString() {
		return (prev == null ? "" : prev.toString()) + "->" + current;
	}
}
