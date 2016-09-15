package com.c.service;

import java.util.ArrayList;
import java.util.List;

import com.perf.beans.Order;
import com.perf.beans.TaxRate;

/**
 * @author Senthil Natarajan
 * 
 *         This is our Service C - This generates fullyCompletedOrdersList that
 *         includes tax to be paid for each order
 */

public class OrdersWithTaxValue {

	public List<Order> setTaxToBePaidForEachOrder(List<Order> ordersList,
			List<TaxRate> taxRateList) {

		ArrayList<Order> fullyCompletedOrdersList = (ArrayList<Order>) ordersList;
		ArrayList<TaxRate> localTaxRateList = (ArrayList<TaxRate>) taxRateList;

		for (Order order : fullyCompletedOrdersList) {
			for (TaxRate taxRate : localTaxRateList) {
				if (order.getStateCode().equalsIgnoreCase(
						taxRate.getStateCode())) {
					order.setTaxToBePaid(order.getPriceOfItem().multiply(
							taxRate.getTaxRate()));
				}
			}
		}
		return fullyCompletedOrdersList;
	}
}
