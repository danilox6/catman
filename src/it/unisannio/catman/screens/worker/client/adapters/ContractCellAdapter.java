package it.unisannio.catman.screens.worker.client.adapters;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import it.unisannio.catman.common.client.DateFormat;
import it.unisannio.catman.common.client.Icon;
import it.unisannio.catman.common.client.cell.InteractiveCellAdapter;
import it.unisannio.catman.domain.humanresources.client.ContractProxy;
import it.unisannio.catman.domain.humanresources.client.EmploymentContractProxy;
import it.unisannio.catman.domain.humanresources.client.FreelanceContractProxy;
import it.unisannio.catman.domain.planning.client.PositionProxy;

public class ContractCellAdapter extends InteractiveCellAdapter<ContractProxy>{
	
public static final String ASSIGN_COMMAND = "assign";
	
	private PositionProxy position;
	private final DateFormat DATE_F = new DateFormat();

	@Override
	public SafeHtml getNorth(ContractProxy object) {
		String title = object instanceof EmploymentContractProxy?"Employment Contract":"Freelance Contract";
		title += " as " + object.getPiecework().getQualification().getName();
		return new SafeHtmlBuilder().appendEscaped(title).toSafeHtml();
	}
	
	@Override
	public SafeHtml getWest(ContractProxy object) {
		return Icon.CHEF.toSafeHtml();
	}
	
	@Override
	public SafeHtml getSouth(ContractProxy object) {
		String contractDuration = "From: "+DATE_F.format(object.getStartDate());
		if(object.getEndDate() != null)
			contractDuration += " to: "+DATE_F.format(object.getEndDate());
		return new SafeHtmlBuilder()
			.appendEscaped(contractDuration)
			.appendHtmlConstant(" &bull; ")
			.append(object.getPiecework().getPay())
			.appendHtmlConstant(object instanceof EmploymentContractProxy? "&euro;/month" : "&euro;/day")
			.toSafeHtml();
	}
	
	
	@Override
	public SafeHtml getOverlay(ContractProxy object) {
		//TODO aggiungere bottone per eliminare un contratto
		return super.getOverlay(object);
	}
	
	@Override
	public SafeHtml getEast(ContractProxy object) {
		if(object instanceof FreelanceContractProxy || position == null || !object.getPiecework().getQualification().equals(position.getQualification()))
			return super.getEast(object);
		SafeHtmlBuilder sb = new SafeHtmlBuilder();
		sb.appendHtmlConstant("<a href=\"javascript:;\" " + InteractiveCellAdapter.getCommandAttribute(ASSIGN_COMMAND) + ">Assign</a>");
		
		return sb.toSafeHtml();
	}
	
	public void setPosition(PositionProxy position) {
		this.position = position;
	}

}
