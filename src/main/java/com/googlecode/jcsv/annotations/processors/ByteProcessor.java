package com.googlecode.jcsv.annotations.processors;

import com.googlecode.jcsv.annotations.ValueProcessor;

/**
 * Processes byte values.
 * 字节转化
 * @author Eike Bergmann
 *
 */
public class ByteProcessor implements ValueProcessor<Byte> {
	/**
	 * Converts value into a short using {@link Short#parseShort(String)}
	 * 将value转为一个Byte
	 * @return Byte the result
	 */
	public Byte processValue(String value) {
		return Byte.parseByte(value);
	}
}
