package it.unisannio.catman.domain.humanresources.client;

import java.util.List;

import it.unisannio.catman.domain.humanresources.JobBoard;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(JobBoard.class)
public interface JobBoardRequest extends RequestContext {
	Request<List<JobBoardProxy>> findAll();
}
