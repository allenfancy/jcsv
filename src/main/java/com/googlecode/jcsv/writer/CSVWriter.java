package com.googlecode.jcsv.writer;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.util.List;

/**
 * 将一个对象类型为E的写入到一个CSV文件中
 * The CSVWriter writes objects of type E to a csv file.
 * 
 * @param <E> The type of the records
 */
public interface CSVWriter<E> extends Closeable, Flushable {
	/**
	 * Writes an entry E to the specified character output stream.
	 * 写入一个指明条目的字符输出流
	 * @param e the entry, that should be written
	 * @throws IOException
	 */
	public void write(E e) throws IOException;

	/**
	 * Writes the data into the specified character output stream.
	 * Calls write(E e) multiple times to write each entry.
	 * 写入所有的数据流
	 * @param data List of Es
	 * @throws IOException
	 */
	public void writeAll(List<E> data) throws IOException;
}
