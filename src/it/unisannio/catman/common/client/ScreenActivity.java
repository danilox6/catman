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
	
	protected void goTo(Intent in) {
		App.goTo(pathTo(in));
	}
	
	protected Path pathTo(Intent in) {
		return new Path(getPath(), in);
	}
	
	protected Path pathTo(String s) {
		return pathTo(new Intent(s));
	}

}
