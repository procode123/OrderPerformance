package com.perf.beans;

import java.math.BigDecimal;

/**
 * @author Senthil Natarajan 
 * 
 * TaxRate Encapsulates state code and tax rate
 */
public class TaxRate {

	private String stateCode;
	private BigDecimal taxRate;

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
	 * @return the taxRate
	 */
	public BigDecimal getTaxRate() {
		return taxRate;
	}

	/**
	 * @param taxRate
	 *            the taxRate to set
	 */
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

}
