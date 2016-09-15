package com.perf.execution;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.a.service.GenerateOrdersService;
import com.aa.service.GeneratePriceAndTotalService;
import com.b.service.GenerateTaxRate;
import com.c.service.OrdersWithTaxValue;
import com.perf.beans.Order;
import com.perf.beans.TaxRate;

/**
 * @author Senthil Natarajan
 * @param args
 * 
 * Increase Performance Using Executor Service
 * 
 */

class UsingExecutorService {
	// Create a ThreadPool that reuses a fixed number of threads
	static Executor pool = Executors.newFixedThreadPool(10);

	public static void main(String[] args) throws IOException {
		long startTimeInNano = System.nanoTime();
		getOrdersWithTaxValueWithOutPeformanceEnhancer();
		long endTimeInNano = System.nanoTime();

		System.out
				.println("===================================================================");
		System.out.println("UsingExecutorService took "
				+ (endTimeInNano - startTimeInNano) + " nanoseconds");
		System.out
				.println("===================================================================");

		Runnable r = new Runnable() {
			@Override
			public void run() {
				getOrdersWithTaxValueWithOutPeformanceEnhancer();
			}
		};
		// The command may execute in a new thread, in a pooled thread, or in
		// the calling thread, at the discretion of the Executor implementation.
		pool.execute(r);
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
	}
}
