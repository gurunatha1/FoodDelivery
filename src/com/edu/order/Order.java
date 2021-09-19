package com.edu.order;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Order {

	private int customerId;
	
	private String restaurant;
	
	private String destinationPoint;
	
	private String orderTime;
	
	public Order(int customerId, String restaurant, String destinationPoint, String orderTime) {
		this.customerId = customerId;
		this.restaurant = restaurant;
		this.destinationPoint = destinationPoint;
		this.orderTime = orderTime;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public String getDestinationPoint() {
		return destinationPoint;
	}

	public String getOrderTime() {
		return this.orderTime;
	}
	
	public String getPickUpTime() throws ParseException {
		String timeString = LocalDate.now() + " " + getOrderTime();
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm aa");
		Date date = df.parse(timeString);
		SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm aa");
		return formatTime.format(new Date(date.getTime() + (15 * 60 * 1000)) );
	}
	
	public String getDeliveryTime() throws ParseException {
		String timeString = LocalDate.now() + " " + getPickUpTime();
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm aa");
		Date date = df.parse(timeString);
		SimpleDateFormat formatTime = new SimpleDateFormat("hh:mm aa");
		return formatTime.format(new Date(date.getTime() + (30 * 60 * 1000)) );
	}
	
}
