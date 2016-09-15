package com.a.service;

import java.util.ArrayList;
import java.util.List;

import com.perf.beans.Order;

/**
 * @author Senthil Natarajan
 * 
 *         This is our Service A We generate 100 Orders using getMeOrders method
 */
public final class GenerateOrdersService {

	// This is our Service A
	public static List<Order> getMeOrders() {

		List<Order> orderList = new ArrayList<>();
		Order tempOrder = null;

		// Let us generate 100 Orders
		for (int i = 1; i < 101; i++) {
			tempOrder = new Order();
			tempOrder.setOrderNumber(i);
			tempOrder.setOrderQuantity(i * 2);
			// Let us add state code
			if (i < 20) {
				tempOrder.setStateCode("CA");
			} else if ((i >= 20) && (i <= 40)) {
				tempOrder.setStateCode("FL");
			} else if ((i >= 40) && (i <= 60)) {
				tempOrder.setStateCode("TX");
			} else if ((i >= 60) && (i <= 80)) {
				tempOrder.setStateCode("GA");
			} else if ((i >= 80)) {
				tempOrder.setStateCode("AL");
			}
			orderList.add(tempOrder);
		}
		return orderList;
	}
}
