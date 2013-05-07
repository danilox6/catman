package it.unisannio.catman.domain.humanresources.client;

public class JobBoardSource implements PersonnelSource{
	
	private String name;

	public JobBoardSource(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
