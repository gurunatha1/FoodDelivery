package com.edu.order.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.edu.order.DeliveryExecutive;
import com.edu.order.DeliveryHistory;
import com.edu.order.Order;

public class BookingAppTest {

	private static Map<String, DeliveryExecutive> AVAILABLE_EXECUTIVES = new LinkedHashMap<>();
	private Map<String, String> executiveVsdeliveryPoint = new HashMap<>();
	private List<DeliveryHistory> deliverHistory = new ArrayList<>();
	private DeliveryExecutive lastDeliveryExecutive;
	private List<DeliveryHistory> tripList = new ArrayList<>();
	
	public static void main(String[] args) throws ParseException {
		BookingAppTest test = new BookingAppTest();
		Order order1 = new Order(1, "A", "D", "9:00 AM");
		test.bookOrder(order1);
		Order order2 = new Order(2, "B", "A", "10:00 AM");
		test.bookOrder(order2);
		Order order3 = new Order(3, "B", "A", "10:10 AM");
		test.bookOrder(order3);
		Order order4 = new Order(4, "D", "C", "10:35 AM");
		test.bookOrder(order4);
		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println("TRIP	EXECUTIVE	RESTAURANT	DESTINATION POINT	ORDERS	PICK-UP_TIME	DELIVERY_TIME	DELIVERY CHARGE");
		test.printDeliveryHistory();
		System.out.println();
		System.out.println("Total earned");
		test.printTotalEarned();
	}
	
	static {
		populateExecutives();
	}
	
	private static void populateExecutives() {
		for(int i = 1; i <= 5; i++) {
			AVAILABLE_EXECUTIVES.put("DE"+i, new DeliveryExecutive("DE"+i));
		}
	}
	
	private void bookOrder(Order order) {
		System.out.println();
		System.out.println("Booking Id : " + (deliverHistory.size() + 1) );
		System.out.println("Available Executives :");
		System.out.println("Executive	Delivery Charge Earned");
		for(String executiveKey : AVAILABLE_EXECUTIVES.keySet()) {
			DeliveryExecutive de = AVAILABLE_EXECUTIVES.get(executiveKey);
			System.out.println(de.getName()+ "		"+ de.getDeliveryCharge());
		}
		DeliveryExecutive executive = findDeliveryExecutive(order);
		String result = "Allotted Delivery Executive: "+executive.getName();
		if(executive.isNeedToPrintSameLocation()) {
			executive.setIsNeedToPrintSameLocation(false);
			result = result + " (because same location within 15mins)";
		}
		System.out.println(result);
		deliverHistory.add(new DeliveryHistory(order, executive));
	}
	

	private DeliveryExecutive findDeliveryExecutive(Order order) {
		if(lastDeliveryExecutive == null) {
			lastDeliveryExecutive = AVAILABLE_EXECUTIVES.get("DE1");
			executiveVsdeliveryPoint.put(lastDeliveryExecutive.getName(), order.getDestinationPoint());
			lastDeliveryExecutive.addDeliveryCharge(50);
			lastDeliveryExecutive.addAllowance(10);
			lastDeliveryExecutive.addtNoOfOrders(1);
			tripList.add(new DeliveryHistory(order, lastDeliveryExecutive));
			return lastDeliveryExecutive;
		}
		for(String executive : AVAILABLE_EXECUTIVES.keySet()) {
			if(executiveVsdeliveryPoint.get(executive) != null && executiveVsdeliveryPoint.get(executive).equals(order.getDestinationPoint())) {
				lastDeliveryExecutive = AVAILABLE_EXECUTIVES.get(executive);
				executiveVsdeliveryPoint.put(lastDeliveryExecutive.getName(), order.getDestinationPoint());
				lastDeliveryExecutive.addDeliveryCharge(5);
				lastDeliveryExecutive.addtNoOfOrders(1);
				lastDeliveryExecutive.setIsNeedToPrintSameLocation(true);
				return lastDeliveryExecutive;
			}
		}
		int executiveKeyIdx = Integer.parseInt(lastDeliveryExecutive.getName().charAt(lastDeliveryExecutive.getName().length()-1)+"") + 1;
		lastDeliveryExecutive = AVAILABLE_EXECUTIVES.get("DE"+executiveKeyIdx);
		executiveVsdeliveryPoint.put(lastDeliveryExecutive.getName(), order.getDestinationPoint());
		lastDeliveryExecutive.addDeliveryCharge(50);
		lastDeliveryExecutive.addAllowance(10);
		lastDeliveryExecutive.addtNoOfOrders(1);
		tripList.add(new DeliveryHistory(order, lastDeliveryExecutive));
		return lastDeliveryExecutive;
	}
	
	private void printTotalEarned() {
		System.out.println();
		System.out.println("Executive	Allowance	Deliver Charges	  Total");
		for(String executiveKey : AVAILABLE_EXECUTIVES.keySet()) {
			DeliveryExecutive de = AVAILABLE_EXECUTIVES.get(executiveKey);
			if(de.getNoOfOrders() > 0) {
				System.out.println(de.getName()+ "		"+ de.getAllowance() + "		" + de.getDeliveryCharge() + "		  " + de.getTotalCharges());
			}
		}
	}
	
	private void printDeliveryHistory() throws ParseException {
		int i = 1;
		for(DeliveryHistory history : tripList) {
			DeliveryExecutive de = history.getDeliveryExecutive();
			Order order = history.getOrder();
			System.out.println(i+"	"+ de.getName()+"		"+order.getRestaurant()+"		"+order.getDestinationPoint()+"			"+de.getNoOfOrders()+"	"+order.getPickUpTime()+ "	"+order.getDeliveryTime()+"	"+de.getDeliveryCharge());
			i = i + 1;
		}
	}
	
}
