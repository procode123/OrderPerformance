package com.b.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.perf.beans.Order;
import com.perf.beans.TaxRate;

/**
 * @author Senthil Natarajan
 * 
 *         This is our Service B - This generates tax rates for 5 states
 */

public final class GenerateTaxRate {

	// This is our Service B - This generates tax rates for 5 states
	public static List<TaxRate> generateTaxRates() {

		List<TaxRate> taxRateList = new ArrayList<>();
		TaxRate tempTaxRate = null;

		// Lets generate tax for 5 states
		for (int i = 0; i < 5; i++) {
			tempTaxRate = new TaxRate();
			if (i == 0) {
				tempTaxRate.setStateCode("CA");
				tempTaxRate.setTaxRate(new BigDecimal(0.3));
			} else if (i == 1) {
				tempTaxRate.setStateCode("FL");
				tempTaxRate.setTaxRate(new BigDecimal(0.5));
			} else if (i == 2) {
				tempTaxRate.setStateCode("TX");
				tempTaxRate.setTaxRate(new BigDecimal(1.0));
			} else if (i == 3) {
				tempTaxRate.setStateCode("GA");
				tempTaxRate.setTaxRate(new BigDecimal(1.3));
			} else if (i == 4) {
				tempTaxRate.setStateCode("AL");
				tempTaxRate.setTaxRate(new BigDecimal(1.8));
			}

			taxRateList.add(tempTaxRate);
		}
		return taxRateList;
	}
}
