package it.unisannio.catman.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({Trail.Tokenizer.class})
public interface ScreenPlaceHistoryMapper extends PlaceHistoryMapper { }