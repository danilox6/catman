package it.unisannio.catman.domain.planning;

import it.unisannio.catman.common.server.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Requirement extends AbstractEntity<Long> {
	
	public static Requirement findRequirement(Long id) {
		return find(Requirement.class, id);
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@Version
	private int version;
	
	@Min(value = 1, message = "At least one resource must be needed")
	private int quantity;

	@ManyToOne
	@NotNull
	private Plan plan;

	public int getQuantity() {
		return quantity;
	}
	
	public abstract String getDescription();
	
	public abstract int getQuantityFilled();

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public boolean isFilled() {
		return getQuantityFilled() == getQuantity();
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
	}
	

}
