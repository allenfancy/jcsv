package com.googlecode.jcsv.reader;

import java.io.Closeable;
import java.util.ListIterator;

/**
 * The CacheCSVReader improves the CSVReader with a cache for the read entries.
 * 缓存CSVReader提高与用于读出的条目的缓存CSVReader
 * If you need to access the records very often or want to iterate through the
 * list of records back and forth, you might use a cached csv reader.
 *  如果你需要访问记录，往往还是希望通过记录列表进行迭代来回，你可以使用缓存的CSV阅读器
 * This Interface bundles the methods of the ListIterator and Closeable.
 * 此接口捆绑的ListIterator和关闭的方法
 * @param <E>
 *            the type of the records
 */
public interface CachedCSVReader<E> extends ListIterator<E>, Closeable {
	/**
	 * Returns the i's entry of the csv file. If the entry is already in the
	 * cache, the entry will be returned directly. If not, the reader reads
	 * until the position i is reached or the end of the csv file is reached.
	 * 返货CSV文件。如果这个Entry已经在缓存中存在，这个entry将直接被返回。如果没有，
	 * 则阅读器读取，直到达到位置i或到达csv文件的末尾。
	 * @param i	  位置
	 *            the position
	 * @return the entry at position i  根据 位置i 返回 entry
	 * @throws ArrayIndexOutOfBoundsException  抛出数组越界的异常
	 *             if there is no entry at position i
	 */
	public E get(int i);
}
