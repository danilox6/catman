package it.unisannio.catman.domain.humanresources.client;

import java.util.ArrayList;
import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.Receiver;

import it.unisannio.catman.common.client.App;
import it.unisannio.catman.common.client.DataStore;

public class WorkersSource implements EntityProxy{
	public static enum Source {WORKERS, CANDIDATES, JOB_BOARD};

	private Source sourceType;
	private JobBoardProxy jobBoardProxy;
	private int count = -1;

	public WorkersSource(Source sourceType){
		this.sourceType = sourceType;
	}

	public WorkersSource(Source sourceType, int count){
		this.sourceType = sourceType;
		this.count = count;
	}

	public WorkersSource(JobBoardProxy jobBoard){
		this.sourceType = Source.JOB_BOARD;
		this.jobBoardProxy = jobBoard; 
		this.count = getJobBoardProxy().getWorkersCount();
	}

	public WorkersSource(String sourceType) {
		this.sourceType = fromString(sourceType);
	}

	public Source getSource() {
		return sourceType;
	}

	public String getJobBoardHystoryToken() {
		if (sourceType == Source.JOB_BOARD)
			return App.getInstance().getDataStore().getHistoryToken(getJobBoardProxy().stableId());
		return null;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public String getName() {
		if(sourceType == Source.WORKERS)
			return "Workers";
		if(sourceType == Source.CANDIDATES)
			return "Candidates";
		return getJobBoardProxy().getName();
	}

	public JobBoardProxy getJobBoardProxy() {
		if(jobBoardProxy==null)
			throw new IllegalStateException("JobBoardProxy cannot be null if sourceType is JOB_BOARD");
		return jobBoardProxy;
	}

	public void setJobBoardProxy(JobBoardProxy jobBoardProxy) {
		this.jobBoardProxy = jobBoardProxy;
	}

	public static Source fromString(String string){
		if(string.equals(Source.WORKERS.toString()))
			return Source.WORKERS;
		if(string.equals(Source.CANDIDATES.toString()))
			return Source.CANDIDATES;
		if(string.equals(Source.JOB_BOARD.toString()))
			return Source.JOB_BOARD;
		throw new IllegalArgumentException(string);
	}

	//FIXME Workaround per usare WorkerSource nella DataList
	@Override
	public EntityProxyId<?> stableId() {
		throw new UnsupportedOperationException();
	}



	public static void findWorkersSources(final Receiver<List<WorkersSource>> receiver){
		final DataStore dataStore = App.getInstance().getDataStore();

		final List<WorkersSource> workersSources = new ArrayList<WorkersSource>();

		dataStore.workers().countInWorkersSource().fire(new Receiver<Integer>() {

			@Override
			public void onSuccess(Integer response) {
				workersSources.add(new WorkersSource(Source.WORKERS,response));

				dataStore.workers().countInCandidates().fire(new Receiver<Integer>() {

					@Override
					public void onSuccess(Integer response) {

						workersSources.add(new WorkersSource(Source.CANDIDATES,response));
						dataStore.jobBoards().findAll().fire(new Receiver<List<JobBoardProxy>>() {

							@Override
							public void onSuccess(List<JobBoardProxy> jobBoards) {
								for (JobBoardProxy jobBoard : jobBoards){
									workersSources.add(new WorkersSource(jobBoard));
								}
								receiver.onSuccess(workersSources);
							}
						});
					}
				});
			}
		});

	}

	public static void findWorkersSources(final Receiver<List<WorkersSource>> receiver, final QualificationProxy q) {
		final DataStore dataStore = App.getInstance().getDataStore();

		final List<WorkersSource> workersSources = new ArrayList<WorkersSource>();
		dataStore.workers().countByQualificationInWorkersSource(q).fire(new Receiver<Integer>() {

			@Override
			public void onSuccess(Integer response) {
				if(response != 0)
					workersSources.add(new WorkersSource(Source.WORKERS,response));

				dataStore.workers().countByQualificationInCandidates(q).fire(new Receiver<Integer>() {
					@Override
					public void onSuccess(Integer response) {
						if(response != 0)
							workersSources.add(new WorkersSource(Source.CANDIDATES,response));
						dataStore.jobBoards().findByQualification(q).fire(new Receiver<List<JobBoardProxy>>() {

							@Override
							public void onSuccess(final List<JobBoardProxy> jobBoards) {
								final int targetSize = workersSources.size() + jobBoards.size();

								for (final JobBoardProxy jobBoard : jobBoards){

									dataStore.workers().countByQualificationInJobBoard(q, jobBoard).fire(new Receiver<Integer>(){

										@Override
										public void onSuccess(Integer response) {
											WorkersSource ws = new WorkersSource(jobBoard);
											ws.setCount(response);
											workersSources.add(ws);

											if(workersSources.size() == targetSize)
												receiver.onSuccess(workersSources);
										}

									});
								}
							}
						});
					}
				});
			}
		});

	}

}
