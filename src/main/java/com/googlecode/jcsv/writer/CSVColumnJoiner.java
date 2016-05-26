package com.googlecode.jcsv.writer;

import com.googlecode.jcsv.CSVStrategy;

/**
 * The csv column joiner receives an array of strings and joines it
 * to a single string that represents a line of the csv file.
 *  该csv条目用来接收一个字符数组
 */
public interface CSVColumnJoiner {
	/**
	 * Converts a String[] array into a single string, using the
	 * given csv strategy.
	 *
	 * @param data incoming data 进入的数据
	 * @param strategy the csv format descriptor CSV策略
	 * @return the joined columns
	 */
	public String joinColumns(String[] data, CSVStrategy strategy);
}
