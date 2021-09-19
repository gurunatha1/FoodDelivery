package com.edu.order;

public class DeliveryExecutive {

	private String name;
	
	private int allowance;
	
	private int deliveryCharge;
	
	private int noOfOrders;
	
	private boolean isNeedToPrintSameLocation;
	
	public DeliveryExecutive(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getAllowance() {
		return this.allowance;
	}
	
	public int getDeliveryCharge() {
		return this.deliveryCharge;
	}
	
	public int getNoOfOrders() {
		return this.noOfOrders;
	}
	
	public void addAllowance(int allowance) {
		this.allowance = this.allowance + allowance;
	}
	
	public void addDeliveryCharge(int deliveryCharge) {
		this.deliveryCharge = this.deliveryCharge + deliveryCharge;
	}
	public void addtNoOfOrders(int noOfOrders) {
		this.noOfOrders = this.noOfOrders  + noOfOrders;
	}
	
	public int getTotalCharges() {
		return this.allowance + this.deliveryCharge;
	}
	
	public void setIsNeedToPrintSameLocation(boolean isNeedToPrintSameLocation) {
		this.isNeedToPrintSameLocation = isNeedToPrintSameLocation;
	}
	
	public boolean isNeedToPrintSameLocation() {
		return this.isNeedToPrintSameLocation;
	}
}
