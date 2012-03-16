package it.unisannio.memento;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface Persists {
	Class<?> value(); 
}
