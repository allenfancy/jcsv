package com.googlecode.jcsv.reader.internal;

import com.googlecode.jcsv.reader.CSVEntryParser;

/**
 * A default implementation of the CSVEntryParser.
 * 一个默认的CSV条目解析实现
 * This entry parser just returns the String[] array that it received.
 * 将返回一个数组
 */
public class DefaultCSVEntryParser implements CSVEntryParser<String[]> {

	/**
	 * returns the input...
	 */
	public String[] parseEntry(String... data) {
		return data;
	}
}
