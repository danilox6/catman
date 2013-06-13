package it.unisannio.catman.domain.equipment.client;

import it.unisannio.catman.domain.equipment.Supply;

import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Supply.class)
public interface SupplyRequest extends RequestContext {

}
