package it.unisannio.catman.common.client;

import it.unisannio.catman.domain.equipment.client.MaterielRequest;
import it.unisannio.catman.domain.equipment.client.StockRequest;
import it.unisannio.catman.domain.equipment.client.SupplierRequest;
import it.unisannio.catman.domain.equipment.client.WarehouseRequest;
import it.unisannio.catman.domain.humanresources.client.JobBoardRequest;
import it.unisannio.catman.domain.humanresources.client.QualificationRequest;
import it.unisannio.catman.domain.humanresources.client.WorkerRequest;
import it.unisannio.catman.domain.planning.client.PlanRequest;
import it.unisannio.catman.domain.planning.client.PositionRequest;
import it.unisannio.catman.domain.planning.client.ProcurementRequest;
import it.unisannio.catman.domain.workflow.client.CustomerRequest;
import it.unisannio.catman.domain.workflow.client.EventDocumentRequest;
import it.unisannio.catman.domain.workflow.client.EventRequest;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface DataStore extends RequestFactory {
	CustomerRequest customers();
	
	EventRequest events();
	
	EventDocumentRequest eventDocuments();
	
	SupplierRequest suppliers();
	
	WarehouseRequest warehouses();
	
	JobBoardRequest jobBoards();
	
	WorkerRequest workers();
	
	QualificationRequest qualifications();
	
	StockRequest stocks();
	
	MaterielRequest materiels();
	
	PlanRequest plans();
	
	ProcurementRequest procurements();
	
	PositionRequest positions();
}
