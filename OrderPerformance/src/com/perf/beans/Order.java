/**
 * 
 */
package com.perf.beans;

import java.math.BigDecimal;

/**
 * @author Senthil Natarajan 
 * 		   Order Bean Encapsulates orderNumber, orderQuantity,
 *         priceOfItem orderTotal, stateCode and taxToBePaid
 */
public class Order {

	private int orderNumber;
	private int orderQuantity;
	private BigDecimal priceOfItem;
	private BigDecimal orderTotal;
	private String stateCode;
	private BigDecimal taxToBePaid;

	/**
	 * @return the orderNumber
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * @param orderNumber
	 *            the orderNumber to set
	 */
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	/**
	 * @return the orderQuantity
	 */
	public int getOrderQuantity() {
		return orderQuantity;
	}

	/**
	 * @param orderQuantity
	 *            the orderQuantity to set
	 */
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	/**
	 * @return the priceOfItem
	 */
	public BigDecimal getPriceOfItem() {
		return priceOfItem;
	}

	/**
	 * @param priceOfItem
	 *            the priceOfItem to set
	 */
	public void setPriceOfItem(BigDecimal priceOfItem) {
		this.priceOfItem = priceOfItem;
	}

	/**
	 * @return the orderTotal
	 */
	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	/**
	 * @param orderTotal
	 *            the orderTotal to set
	 */
	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @param stateCode
	 *            the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	/**
	 * @return the taxToBePaid
	 */
	public BigDecimal getTaxToBePaid() {
		return taxToBePaid;
	}

	/**
	 * @param taxToBePaid
	 *            the taxToBePaid to set
	 */
	public void setTaxToBePaid(BigDecimal taxToBePaid) {
		this.taxToBePaid = taxToBePaid;
	}

}
