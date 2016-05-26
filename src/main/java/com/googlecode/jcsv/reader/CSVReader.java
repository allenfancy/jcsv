package com.googlecode.jcsv.reader;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

/**
 * The CSVReader reads and parses csv data.
 * 该CSVReader读取并解析CSV数据
 * 
 * @param <E> the type of the records.
 * @Param <E> 记录的类型
 */
public interface CSVReader<E> extends Iterable<E>, Closeable {

	/**
	 * Reads to the end of the csv file and returns a List of created objects.
	 * Calls readNext() multiple times until null is returned.
	 * 读取到csv文件结尾并返回创建对象的一系列集合。
	 * 调用read下一页()多次，直到返回null。
	 * @return List of E
	 * @throws IOException
	 */
	public List<E> readAll() throws IOException;

	/**
	 * Reads the next record from the csv file and returns it.
	 * If the end of the csv file has been reached, this method returns null.
	 * 从csv文件中读取下一个记录，并返回它
	 * @return the next entry E, null if the end of the file has been reached
	 * @return 下一个E，或者null,文件的结尾一直到达
	 * @throws IOException
	 */
	public E readNext() throws IOException;

	/**
	 * Reads and returns the header of the csv file.
	 * This method must be the first call on this CSVReaderImpl.
	 * 读取并返回csv文件的头部
	 * @return The csv header
	 * @return 这个CSV的头部
	 * @throws IOException
	 */
	public List<String> readHeader() throws IOException;
}
