package it.unisannio.catman.common.client.cell;

public abstract class AbstractSelectable implements Selectable{

	private boolean selected = false;
	
	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
