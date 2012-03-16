package it.unisannio.memento;

public @interface PersistedBy {
	Class<? extends GenericDAO<?, ?>> value();
}
