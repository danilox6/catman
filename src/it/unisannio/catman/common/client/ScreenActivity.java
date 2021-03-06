package it.unisannio.catman.common.client;

import com.google.gwt.activity.shared.AbstractActivity;

public abstract class ScreenActivity extends AbstractActivity {
	
	private Path path;
	
	void setPath(Path path) {
		this.path = path;
	}
	
	protected Path getPath() {
		return path;
	}
	
	protected Intent getIntent() {
		return getPath().peek();
	}
	
	public void goTo(Intent in) {
		App.goTo(pathTo(in));
	}
	
	public void goUp() {
		App.goTo(getPath().pop());
	}
	
	protected Path pathTo(Intent in) {
		return new Path(getPath(), in);
	}
	
	protected Path pathTo(String s) {
		return pathTo(new Intent(s));
	}

	protected static DataStore getDataStore() {
		return App.getInstance().getDataStore();
	}
}
