package it.unisannio.catman.common.client;

import it.unisannio.catman.domain.equipment.client.SupplierRequest;
import it.unisannio.catman.domain.workflow.client.CustomerRequest;
import it.unisannio.catman.domain.workflow.client.EventRequest;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface DataStore extends RequestFactory {
	CustomerRequest customers();
	EventRequest events();
	SupplierRequest suppliers();
}
