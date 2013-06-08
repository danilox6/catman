package it.unisannio.catman.domain.equipment;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Entity
public class Stock extends Supply<Stock, Warehouse> {
	public static Stock findStock(SupplyKey supplyKey) {
		return find(Stock.class, supplyKey);
	}

	public static List<Stock> listStocksByWarehouse(Warehouse warehouse, int offset, int size){
		return getStocksByWarehouseQuery(warehouse)
				.setFirstResult(offset)
				.setMaxResults(size)
				.getResultList();
	}
	
	public static Integer countStocksByWarehouseQuery(Warehouse warehouse){
	/*	EntityManager entityManager = getEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Stock> s = cq.from(Stock.class);
		cq.select(qb.count(cq.from(
		cq.where(cb.equal(s.get("supplier"), warehouse));*/
		
		return getStocksByWarehouseQuery(warehouse).getResultList().size(); //FIXME
	}
	
	private static TypedQuery<Stock> getStocksByWarehouseQuery(Warehouse warehouse){
		EntityManager entityManager = getEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Stock> cq = cb.createQuery(Stock.class);
		Root<Stock> s = cq.from(Stock.class);
		cq.where(cb.equal(s.get("supplier"), warehouse));
		
		return entityManager.createQuery(cq);
	}

	@ManyToOne
	private Warehouse supplier;

	public Warehouse getSupplier() {
		return supplier;
	}

	public void setSupplier(Warehouse supplier) {
		this.supplier = supplier;
		supplierId = supplier.getId();
	}
}
