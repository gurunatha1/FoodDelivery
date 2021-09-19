package com.edu.order;

public class DeliveryHistory {

	private DeliveryExecutive deliveryExecutive;
	
	private Order order;
	
	public DeliveryHistory(Order order, DeliveryExecutive deliveryExecutive) {
		this.deliveryExecutive = deliveryExecutive;
		this.order = order;
	}
	
	public DeliveryExecutive getDeliveryExecutive() {
		return this.deliveryExecutive;
	}
	
	public Order getOrder() {
		return this.order;
	}
}
