package it.unisannio.memento;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface PersistedBy {
	Class<? extends GenericDAO<?, ?>> value();
}
