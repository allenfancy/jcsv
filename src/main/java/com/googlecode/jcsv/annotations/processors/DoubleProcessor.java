package com.googlecode.jcsv.annotations.processors;

import com.googlecode.jcsv.annotations.ValueProcessor;

/**
 * Processes double values.
 * Double字段类型处理
 * @author Eike Bergmann
 *
 */
public class DoubleProcessor implements ValueProcessor<Double> {
	/**
	 * Converts value into a double using {@link Double#parseDouble(String)}
	 *
	 * @return Double the result
	 */
	public Double processValue(String value) {
		return Double.parseDouble(value);
	}
}
