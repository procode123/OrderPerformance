/**
 * 
 */
package com.perf.execution;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.a.service.GenerateOrdersService;
import com.aa.service.GeneratePriceAndTotalService;
import com.b.service.GenerateTaxRate;
import com.c.service.OrdersWithTaxValue;
import com.perf.beans.Order;
import com.perf.beans.TaxRate;

/**
 * @author Senthil Natarajan
 * 
 *         This is the code to be executed Initially..
 * 
 */
public class InitialExecution {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		long startTimeInNano = System.nanoTime();
		long startTimeInMilli = System.currentTimeMillis();
		getOrdersWithTaxValueWithOutPeformanceEnhancer();
		long endTimeInNano = System.nanoTime();
		long endTimeInMilli = System.currentTimeMillis();
		System.out
				.println("===================================================================");
		System.out.println("Initial Execution took "
				+ (endTimeInNano - startTimeInNano) + " nanoseconds");
		System.out.println("Initial Execution took "
				+ (endTimeInMilli - startTimeInMilli) + " milliseconds");
		System.out
				.println("===================================================================");
	}

	@SuppressWarnings("static-access")
	private static void getOrdersWithTaxValueWithOutPeformanceEnhancer() {

		// This is Service A
		List<Order> orderList = GenerateOrdersService.getMeOrders();

		// Calling Service AA using Service A's output
		GeneratePriceAndTotalService generatePriceAndTotalService = new GeneratePriceAndTotalService();
		List<Order> orderListWithPriceOfItemAndOrderTotal = generatePriceAndTotalService
				.setPriceAndTotalForEachOrder(orderList);

		// Calling Service B
		List<TaxRate> taxRateList = GenerateTaxRate.generateTaxRates();

		// Calling Service CC using Output of Service AA and Service B
		ArrayList<Order> finalOrderList = null;
		OrdersWithTaxValue ordersWithTaxValue = new OrdersWithTaxValue();
		// final Order List Now Has, All the properties, including tax to be
		// paid for each order
		finalOrderList = (ArrayList<Order>) ordersWithTaxValue
				.setTaxToBePaidForEachOrder(
						orderListWithPriceOfItemAndOrderTotal, taxRateList);

		StringBuilder sb = new StringBuilder();
		for (Order order : finalOrderList) {
			sb.append("orderNumber is : " + order.getOrderNumber() + "\n");
			sb.append("orderQuantity is : " + order.getOrderQuantity() + "\n");
			sb.append("priceOfItem is :" + order.getPriceOfItem() + "\n");
			sb.append("orderTotal is :" + order.getOrderTotal() + "\n");
			sb.append("stateCode is :" + order.getStateCode() + "\n");
			sb.append("taxToBePaid is :"
					+ order.getTaxToBePaid().setScale(2, RoundingMode.CEILING)
					+ "\n");
			sb.append("==================================================================="
					+ "\n");
		}
		System.out.println(sb.toString());
	}

}
