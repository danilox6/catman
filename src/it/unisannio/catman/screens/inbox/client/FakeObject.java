package it.unisannio.catman.screens.inbox.client;

public class FakeObject {
	
	private String a;
	private String b;

	public FakeObject() {
		// TODO Auto-generated constructor stub
	}
	
	public FakeObject(String a) {
		this.a = a;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return b;
	}

	public void setB(String b) {
		this.b = b;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "A: " + a + " B: " + b;
	}
}
