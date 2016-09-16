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
 *         Code that increases the performance by simultaneously running 2 threads. 
 *         And taking those results as input for final service call and returns results. *         
 *         This code showed 25% increase in performance compared to InitialExecution.java
 * 
 */
public class UsingThreads {

	static List<Order> orderList;
	static List<Order> orderListWithPriceOfItemAndOrderTotal;
	static List<TaxRate> taxRateList;
	static ArrayList<Order> finalOrderList;
	static long startTimeInNano, endTimeInNano;
	static long startTimeInMilli, endTimeInMilli;

	public static void main(String[] args) {

		// Create two threads:
		Thread thread1 = new Thread() {
			public void run() {
				startTimeInNano = System.nanoTime();
				startTimeInMilli = System.currentTimeMillis();
				System.out.println("Running Thread 1....");
				//Service A
				orderList = GenerateOrdersService.getMeOrders();
				//Service AA
				orderListWithPriceOfItemAndOrderTotal = new GeneratePriceAndTotalService()
						.setPriceAndTotalForEachOrder(orderList);
			}
		};

		Thread thread2 = new Thread() {
			public void run() {
				// Service B
				System.out.println("Running Thread 2....");
				taxRateList = GenerateTaxRate.generateTaxRates();
			}
		};

		// Start thread1 and thread2 in parallel.
		thread1.start();
		thread2.start();

		// Wait for them both the threads to finish
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out
				.println("Done with Parallel Processing...Using the Results of Thread 1 and Thread 2");

		//Final callServiceA that takes results of previous calls as inputs and displays final output.
		finalOrderList = (ArrayList<Order>) new UsingThreads().callServiceA(
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
		endTimeInNano = System.nanoTime();
		endTimeInMilli = System.currentTimeMillis();
		System.out
				.println("===================================================================");
		System.out.println("Using Threads took "
				+ (endTimeInNano - startTimeInNano) + " nanoseconds");
		System.out.println("Using Threads took "
				+ (endTimeInMilli - startTimeInMilli) + " milliseconds");
		System.out
				.println("===================================================================");
	}

	private List<Order> callServiceA(
			List<Order> orderListWithPriceOfItemAndOrderTotal,
			List<TaxRate> taxRateList) {
		// Calling Service CC using Output of Service AA and Service B
		return new OrdersWithTaxValue().setTaxToBePaidForEachOrder(
				orderListWithPriceOfItemAndOrderTotal, taxRateList);

	}

}
