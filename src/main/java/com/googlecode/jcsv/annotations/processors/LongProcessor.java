package com.googlecode.jcsv.annotations.processors;

import com.googlecode.jcsv.annotations.ValueProcessor;

/**
 * Processes long values.
 * Long字段类型处理类
 * @author Eike Bergmann
 *
 */
public class LongProcessor implements ValueProcessor<Long> {
	/**
	 * Converts value into a long using {@link Long#parseLong(String)}
	 *
	 * @return Long the result
	 */
	public Long processValue(String value) {
		return Long.parseLong(value);
	}
}
