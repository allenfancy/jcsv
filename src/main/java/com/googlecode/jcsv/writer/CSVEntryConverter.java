package com.googlecode.jcsv.writer;

/**
 * The CSVEntryConverter receives a java object and converts it
 * into a String[] array that will be written to the output stream.
 * 该CSV条目转换接收一个Java对象并且将其转为成一个字符数组，并且写入到输出流中
 * @param <E> The Type that will be converted
 */
public interface CSVEntryConverter<E> {
	/**
	 * Converts an object of type E into a String[] array,
	 * that will be written into the csv file.
	 * 将对象类型为E的对象转为一个字符串数组，这样就能被写入到CSV文件中
	 * @param e that object that will be converted
	 * @return the data
	 */
	public String[] convertEntry(E e);
}
