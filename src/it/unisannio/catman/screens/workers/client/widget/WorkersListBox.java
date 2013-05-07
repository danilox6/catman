package it.unisannio.catman.screens.workers.client.widget;

import it.unisannio.catman.domain.humanresources.client.JobProxy;
import it.unisannio.catman.domain.humanresources.client.PersonnelSource;

import com.google.gwt.user.client.ui.ListBox;

public class WorkersListBox extends ListBox {
	
	private JobProxy[] jobs;
	
	public WorkersListBox(PersonnelSource source) {
		//TODO Query for available Jobs/Positions in the given PersonnelSource
		jobs = new JobProxy[] {new JobProxyMock("Waiter"), new JobProxyMock("Chef") , new JobProxyMock("Servi della gleba"), new JobProxyMock("Schiavi")};
		
		for(JobProxy job :jobs){
			addItem(job.toString());
		}
		
		setWidth("100%");
	}
	
	
	public JobProxy getSelectedJob(){
		return jobs[getSelectedIndex()];
	}
	
	
	//FIXME Solo per test
	public static class JobProxyMock implements JobProxy{
		String jobName;

		public JobProxyMock(String jobName) {
			this.jobName = jobName;
		}
		
		public String toString() {
			return jobName;
		}
	}
	
}
