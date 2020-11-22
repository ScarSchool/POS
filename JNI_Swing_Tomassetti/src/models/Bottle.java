package models;

public class Bottle {
	private int index;
	private String material;
	private String owner;
	private boolean filled;
	private BottleType bottleType;

	public Bottle(String material, String owner, Boolean filled, BottleType bottleType){
		this.material = material;
		this.owner = owner;
		this.filled = filled;
		this.bottleType = bottleType;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String number) {
		this.material = number;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public boolean getFilled() {
		return filled;
	}
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
	public BottleType getBottleType() {
		return bottleType;
	}
	public void setBottleType(BottleType bottleType) {
		this.bottleType = bottleType;
	}
}