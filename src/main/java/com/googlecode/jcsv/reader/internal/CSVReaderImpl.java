package com.googlecode.jcsv.reader.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVEntryFilter;
import com.googlecode.jcsv.reader.CSVEntryParser;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.CSVTokenizer;

//一个读CSVReader读取实现
public class CSVReaderImpl<E> implements CSVReader<E> {
	//BufferReader 
	private final BufferedReader reader;
	private final CSVStrategy strategy;
	//CSV条目解析器
	private final CSVEntryParser<E> entryParser;
	//CSV条目过滤器
	private final CSVEntryFilter<E> entryFilter;
	//CSV分词器
	private final CSVTokenizer tokenizer;
	//是否读取第一行
	private boolean firstLineRead = false;

	CSVReaderImpl(CSVReaderBuilder<E> builder) {
		this.reader = new BufferedReader(builder.reader);
		this.strategy = builder.strategy;
		this.entryParser = builder.entryParser;
		this.entryFilter = builder.entryFilter;
		this.tokenizer = builder.tokenizer;
	}

	//读取所有
	public List<E> readAll() throws IOException {
		//设置一个条目集合
		List<E> entries = new ArrayList<E>();

		E entry = null;
		while ((entry = readNext()) != null) {
			entries.add(entry);
		}

		return entries;
	}
	//读取下一行
	public E readNext() throws IOException {
		//如果跳过头
		if (strategy.isSkipHeader() && !firstLineRead) {
			reader.readLine(); //读取一行
		}

		E entry = null;
		//验证条目
		boolean validEntry = false;
		do {
			String line = readLine();
			if (line == null) {
				return null;
			}
			//如果当前长度为空，并且是空的话，直接忽略
			if (line.trim().length() == 0 && strategy.isIgnoreEmptyLines()) {
				continue;
			}
			//如果是注释行直接忽略
			if (isCommentLine(line)) {
				continue;
			}
			//
			List<String> data = tokenizer.tokenizeLine(line, strategy, reader);
			//返回条目
			entry = entryParser.parseEntry(data.toArray(new String[data.size()]));
			validEntry = entryFilter != null ? entryFilter.match(entry) : true;
		} while (!validEntry);

		firstLineRead = true;

		return entry;
	}

	public List<String> readHeader() throws IOException {
		if (firstLineRead) {
			throw new IllegalStateException("can not read header, readHeader() must be the first call on this reader");
		}

		String line = readLine();
		if (line == null) {
			throw new IllegalStateException("reached EOF while reading the header");
		}

		List<String> header = tokenizer.tokenizeLine(line, strategy, reader);
		return header;
	}

	/**
	 * Returns the Iterator for this CSVReaderImpl.
	 *
	 * @return Iterator<E> the iterator
	 */
	public Iterator<E> iterator() {
		return new CSVIterator();
	}

	/**
	 * {@link java.io.Closeable#close()}
	 */
	public void close() throws IOException {
		reader.close();
	}
	//判断这一行是否是注释行
	private boolean isCommentLine(String line) {
		return line.startsWith(String.valueOf(strategy.getCommentIndicator()));
	}

	/**
	 * Reads a line from the given reader and sets the firstLineRead flag.
	 *
	 * @return the read line
	 * @throws IOException
	 */
	private String readLine() throws IOException {
		String line = reader.readLine();
		firstLineRead = true;
		return line;
	}

	private class CSVIterator implements Iterator<E> {
		private E nextEntry;

		public boolean hasNext() {
			if (nextEntry != null) {
				return true;
			}

			try {
				nextEntry = readNext();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

			return nextEntry != null;
		}

		public E next() {
			E entry = null;
			if (nextEntry != null) {
				entry = nextEntry;
				nextEntry = null;
			} else {
				try {
					entry = readNext();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}

			return entry;
		}

		public void remove() {
			throw new UnsupportedOperationException("this iterator doesn't support object deletion");
		}
	}
}
