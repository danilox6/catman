package it.unisannio.catman.common.client.cell;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * Implements {@link CellAdapter} providing a base implementation whose methods
 *  doesn't return <code>null</code> if child classes doesn't override them.
 */
public abstract class AbstractCellAdapter<T> implements CellAdapter<T> {

	@Override
	public SafeHtml getWest(T object) {
		return null;
	}

	@Override
	public SafeHtml getSouth(T object) {
		return null;
	}

	@Override
	public SafeHtml getEast(T object) {
		return null;
	}

	@Override
	public SafeHtml getOverlay(T object) {
		return null;
	}
	
}
