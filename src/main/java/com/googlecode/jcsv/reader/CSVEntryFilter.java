package com.googlecode.jcsv.reader;

/**
 * The CSVEntryFilter is used to filter the records of a
 * csv file.
 * CSVEntryFilter 被用来过滤csv文件中的记录
 * @param <E> the type of the records
 * 记录的类型
 */
public interface CSVEntryFilter<E> {

	/**
	 * Checks whether the object e matches this filter.
	 * 检查这个对象是否匹配上这个过滤器
	 * @param e The object that is to be tested
	 * @return true, if e matches this filter
	 */
	public boolean match(E e);
}
