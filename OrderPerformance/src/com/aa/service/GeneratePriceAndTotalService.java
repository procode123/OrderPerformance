package com.aa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.a.service.GenerateOrdersService;
import com.perf.beans.Order;

/**
 * @author Senthil Natarajan
 * 
 *         This is our Service AA This sets Price of Item and Order Total for
 *         each order
 */

public class GeneratePriceAndTotalService {

	public List<Order> setPriceAndTotalForEachOrder(List<Order> orderList) {
		BigDecimal itemPrice, itemQuantity = null;
		int i = 0;
		for (Order order : orderList) {
			itemPrice = new BigDecimal(++i * 5);
			order.setPriceOfItem(itemPrice);
			itemQuantity = new BigDecimal(order.getOrderQuantity());
			order.setOrderTotal(itemPrice.multiply(itemQuantity));
		}
		return orderList;
	}
}
