package it.unisannio.catman.screens.personnelpicker.client.adapters;

import java.util.HashMap;
import java.util.HashSet;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.domain.humanresources.client.JobBoardProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;

public class WorkerMasterAdapter extends AbstractCellAdapter<WorkerProxy>{
	
	private HashMap<WorkerProxy, JobBoardProxy> jobBoardMapping = new HashMap<WorkerProxy, JobBoardProxy>();
	private HashSet<WorkerProxy> selectedWorkers = new HashSet<WorkerProxy>();
	
	@Override
	public SafeHtml getNorth(WorkerProxy object) {
		return new SafeHtmlBuilder().appendEscaped(object.getName()).toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(WorkerProxy object) {
		return Icon.CONTACT.toSafeHtml();
	}
	
	@Override
	public SafeHtml getEast(WorkerProxy object) {
		if(selectedWorkers.contains(object))
			return new SafeHtmlBuilder().appendHtmlConstant("&#x2714;").toSafeHtml();
		return super.getEast(object);
	}
	
	@Override
	public SafeHtml getSouth(WorkerProxy object) {
		String source;
		if(jobBoardMapping.containsKey(object))
			source = (object.isCandidate()?"Candidates, ":"")+jobBoardMapping.get(object).getName();
		else 
			source = (object.isCandidate()?"Candidates": "Workers");
		return new SafeHtmlBuilder().appendEscaped(source).toSafeHtml();
	}

	public void setJobBoardMapping(HashMap<WorkerProxy, JobBoardProxy> jobBoardMapping) {
		this.jobBoardMapping = jobBoardMapping;
	}
	
	public void setSelectedWorkers(HashSet<WorkerProxy> selectedWorkers) {
		this.selectedWorkers = selectedWorkers;
	}

}
