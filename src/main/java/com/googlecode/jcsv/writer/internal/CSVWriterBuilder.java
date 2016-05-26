package com.googlecode.jcsv.writer.internal;

import java.io.Writer;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.util.Builder;
import com.googlecode.jcsv.writer.CSVColumnJoiner;
import com.googlecode.jcsv.writer.CSVEntryConverter;
import com.googlecode.jcsv.writer.CSVWriter;

/**
 * The builder that creates the CSVWriterImpl instance.
 * 使用Builder模式创建一个CSVWriterImpl 实例
 * @param <E> The Type of the records
 */
public class CSVWriterBuilder<E> implements Builder<CSVWriter<E>>{
	//
	final Writer writer;
	//使用默认的策略模式
	CSVStrategy strategy = CSVStrategy.DEFAULT;
	//使用条目转换类
	CSVEntryConverter<E> entryConverter;
	//CSVCoulmn
	CSVColumnJoiner columnJoiner = new CSVColumnJoinerImpl();

	/**
	 * Creates a Builder for the CSVWriterImpl
	 * 使用Builder去创建
	 * @param writer the character output stream
	 * @param 字符串的写出流
	 */
	public CSVWriterBuilder(Writer writer) {
		this.writer = writer;
	}

	/**
	 * Sets the strategy that the CSVWriterImpl will use.
	 * 设置CSV执行策略，被CSVWriterImpl 去使用
	 * @param strategy the csv strategy
	 * @return this builder
	 */
	public CSVWriterBuilder<E> strategy(CSVStrategy strategy) {
		this.strategy = strategy;
		return this;
	}

	/**
	 * Sets the entry converter that the CSVWriterImpl will use.
	 * 设置条目的转换规则
	 * @param entryConverter the entry converter
	 * @return this builder
	 */
	public CSVWriterBuilder<E> entryConverter(CSVEntryConverter<E> entryConverter) {
		this.entryConverter = entryConverter;
		return this;
	}

	/**
	 * Sets the column joiner strategy that the CSVWriterImpl will use.
	 * If you don't specify your own csv tokenizer strategy, the default
	 * column joiner will be used.
	 * 设置 column 加入策略
	 * {@link com.googlecode.jcsv.writer.internal.CSVColumnJoinerImpl}
	 *
	 * @param columnJoiner the column joiner
	 * @return this builder
	 */
	public CSVWriterBuilder<E> columnJoiner(CSVColumnJoiner columnJoiner) {
		this.columnJoiner = columnJoiner;
		return this;
	}

	/**
	 * Builds the CSVWriterImpl, using the specified configuration
	 * 
	 * @return the CSVWriterImpl instance
	 */
	public CSVWriter<E> build() {
		if (entryConverter == null) {
			throw new IllegalStateException("you have to specify an entry converter");
		}

		return new CSVWriterImpl<E>(this);
	}

	/**
	 * Returns a default configured CSVWriterImpl<String[]>.
	 * It uses the DefaultCSVEntryParser that allows you to
	 * write a String[] arrayas an entry in your csv file.
	 * 使用默认的创建使用策略，<E>为一个字符串数组
	 * @param writer the character output stream
	 * @return the CSVWriterImpl
	 */
	public static CSVWriter<String[]> newDefaultWriter(Writer writer) {
		//然后设置默认的CSV转换策略
		return new CSVWriterBuilder<String[]>(writer).entryConverter(new DefaultCSVEntryConverter()).build();
	}
}
