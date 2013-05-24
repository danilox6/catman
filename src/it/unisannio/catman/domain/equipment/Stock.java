package it.unisannio.catman.domain.equipment;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Stock extends Supply<Stock, Warehouse> {
	public static Stock findStock(Supply.Key key) {
		return find(Stock.class, key);
	}
}
