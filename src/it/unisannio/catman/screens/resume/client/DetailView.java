package it.unisannio.catman.screens.resume.client;

import java.util.List;

import it.unisannio.catman.common.client.cell.AbstractCellAdapter;
import it.unisannio.catman.common.client.cell.MasterCell;
import it.unisannio.catman.common.client.widget.AbstractDetailView;
import it.unisannio.catman.common.client.widget.DetailSectionWidget;
import it.unisannio.catman.domain.contacts.client.ContactProxy;
import it.unisannio.catman.domain.humanresources.client.ResumeProxy;
import it.unisannio.catman.domain.humanresources.client.WorkerProxy;
import it.unisannio.catman.screens.resume.client.widget.DetailHeadWidget;
import it.unisannio.catman.screens.resume.client.widget.ResumeCell;
import it.unisannio.catman.screens.worker.client.widget.ContactCell;
import it.unisannio.catman.screens.worker.client.widget.WorkerCellAdapter;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.CellWidget;
import com.google.gwt.view.client.ListDataProvider;

public class DetailView extends AbstractDetailView implements Resume.Detail.View{

	public DetailView() {
		
		northPanel.add(new DetailHeadWidget("John Phantom"));
		
		CellWidget<WorkerProxy> workerInfoCell = new CellWidget<WorkerProxy>(new MasterCell<WorkerProxy>(new WorkerCellAdapter()));
		workerInfoCell.setValue(new MockWorkerProxy());
		workerInfoCell.setWidth("100%");
		centerVerticalPanel.add(workerInfoCell);
		
		DetailSectionWidget contactInfoSection = new DetailSectionWidget("Contact Info");
		CellWidget<ContactProxy> contactInfoCell = new CellWidget<ContactProxy>(new ContactCell());
		contactInfoCell.setValue(new MockContactProxy());
		contactInfoCell.setWidth("100%");
		contactInfoSection.add(contactInfoCell);
		centerVerticalPanel.add(contactInfoSection);
		
		DetailSectionWidget resumeSection = new DetailSectionWidget("Contact Info");
		CellWidget<ResumeProxy> resumeCell = new CellWidget<ResumeProxy>(new ResumeCell());
		resumeCell.setValue(new MockResumeProxy());
		resumeCell.setWidth("100%");
		resumeSection.add(resumeCell);
		centerVerticalPanel.add(resumeSection);
		
		DetailSectionWidget jobsSection = new DetailSectionWidget("Available as");
		CellList<JobProxy> cellList = new CellList<JobProxy>(new MasterCell<JobProxy>(new JobCellAdapter()));
		ListDataProvider<JobProxy> dataProvider = new ListDataProvider<JobProxy>();
		dataProvider.addDataDisplay(cellList);
		
		List<JobProxy> jobs = dataProvider.getList();
		jobs.add(new MockJobProxy());
		jobs.add(new MockJobProxy());
		jobs.add(new MockJobProxy());
		jobs.add(new MockJobProxy());
		jobs.add(new MockJobProxy());
		jobs.add(new MockJobProxy());
		
		jobsSection.add(cellList);
		centerVerticalPanel.add(jobsSection);
	}

	//FIXME solo per testing
	class MockWorkerProxy implements WorkerProxy{}
	class MockContactProxy implements ContactProxy{}
	class MockResumeProxy implements ResumeProxy{}
	
	interface JobProxy{}
	class MockJobProxy implements JobProxy{}
	
	class JobCellAdapter extends AbstractCellAdapter<JobProxy>{
		@Override
		public SafeHtml getNorth(JobProxy object) {
			return new SafeHtmlBuilder().appendEscaped("Freelance Contract").toSafeHtml();
		}
		@Override
		public SafeHtml getWest(JobProxy object) {
			return new SafeHtmlBuilder().appendHtmlConstant("<img src='/prova.jpg' width='32px' height='32px' />").toSafeHtml();
		}

		@Override
		public SafeHtml getSouth(JobProxy object) {
			return new SafeHtmlBuilder().appendEscaped("Waiter").toSafeHtml();
		}
		
		@Override
		public SafeHtml getEast(JobProxy object) {
			return new SafeHtmlBuilder().appendHtmlConstant("100&euro;/day | <button>Hire Me!</button>").toSafeHtml();
		}
	}

}
