package com.googlecode.jcsv.annotations.processors;

import com.googlecode.jcsv.annotations.ValueProcessor;

/**
 * Processes boolean values.
 *  Boolean value转换
 * @author Eike Bergmann
 *
 */
public class BooleanProcessor implements ValueProcessor<Boolean> {
	/**
	 * Converts value into a boolean.
	 * Returns true, if value is "true" or "1", false otherwise.
	 * 如果value是1 或者是true，就返回为true
	 * @return Boolean the result
	 */
	public Boolean processValue(String value) {
		if (value == null) return false;
		
		return value.equals("1") || value.toLowerCase().equals("true");		
	}
}
