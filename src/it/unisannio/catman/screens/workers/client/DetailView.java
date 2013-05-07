package it.unisannio.catman.screens.workers.client;

import it.unisannio.catman.domain.humanresources.client.WorkersSource;

public class DetailView extends PersonnelSourceDetailView implements Workers.Detail.View{
	interface Presenter{}

	public DetailView() {
		super(new WorkersSource());
	}

}
