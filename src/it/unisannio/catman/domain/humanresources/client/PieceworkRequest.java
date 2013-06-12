package it.unisannio.catman.domain.humanresources.client;

import it.unisannio.catman.domain.humanresources.Piecework;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Piecework.class)
public interface PieceworkRequest extends RequestContext{
	InstanceRequest<PieceworkProxy,Void> persist();
}
