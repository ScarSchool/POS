package models;

public class Phone {
	private int index;
	private long number;
	private String owner;
	private boolean paid;
	private PhoneType phoneType;

	public Phone(Long number, String owner, Boolean paid, PhoneType phoneType){
		this.number = number;
		this.owner = owner;
		this.paid = paid;
		this.phoneType = phoneType;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public boolean getPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public PhoneType getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}
}