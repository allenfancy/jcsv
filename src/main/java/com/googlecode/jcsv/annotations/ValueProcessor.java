package com.googlecode.jcsv.annotations;

/**
 * The ValueProcessor is used to convert a string value
 * into an object of type E. This is used for annotation parsing.
 * 值处理器，被用来将string value 转为类型为E的对象，这是用于注解解析.
 * The implementations for the primitives and String are located
 * in com.googlecode.jcsv.annotations.processors
 *
 * @param <E> the destination type
 */
public interface ValueProcessor<E> {
	/**
	 * Converts value into an object of type E.
	 * 将value转为类型为E的对象
	 * @param value the value that should be converted
	 * @Param 这个参数value应该被转换
	 * @return the converted object
	 */
	public E processValue(String value);
}
