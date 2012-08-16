package it.unisannio.catman.common.model;

import it.unisannio.catman.Setup;

public class BusinessCustomer extends Customer {
	public static enum Type {
		SIMPLE(""), SNC("S.n.c."), SAS("S.a.s."), SPA ("S.p.A."), SRL ("S.r.l."), SAPA("S.a.p.a"), COOP ("coop.");
		
		private final String name;
		
		private Type(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	private String businessName;
	private Type type;
	private String vat;
	
	@Override
	public String getName() {
		return String.format(Setup.CUSTOMER_BUSINESS_NAME_FORMAT, businessName, type);
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getVAT() {
		return vat;
	}

	public void setVAT(String vat) {
		this.vat = vat;
	}

}
