package com.googlecode.jcsv.writer.internal;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.util.CSVUtil;
import com.googlecode.jcsv.writer.CSVColumnJoiner;

/**
 * This is a simple implementation of the CSVColumnJoiner.
 * It just performs an <code>CSVUtil.implode</code> on the
 * incoming String array.
 * 简单的列加入实现
 * If you need a full support of the CSV formatting standard
 * you should use
 * {@link com.googlecode.jcsv.writer.internal.CSVColumnJoinerImpl}
 */
public class SimpleCSVColumnJoiner implements CSVColumnJoiner {

	/**
	 * Performs a CSVUtil.implode() to concat the columns, it uses
	 * the delimiter specified by the csv strategy.
	 */
	public String joinColumns(String[] data, CSVStrategy strategy) {
		//使用策略中定义的分割符
		return CSVUtil.implode(data, String.valueOf(strategy.getDelimiter()));
	}

}
