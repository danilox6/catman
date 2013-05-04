package it.unisannio.catman.common.client;

import it.unisannio.catman.domain.workflow.client.CustomerRequest;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface DataStore extends RequestFactory {
	CustomerRequest customers();
}
