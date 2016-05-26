package com.googlecode.jcsv.reader.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.CachedCSVReader;

/**
 * CSV缓存读取实现
 * @author allen
 *
 * @param <E>
 */
public class CachedCSVReaderImpl<E> implements CachedCSVReader<E> {

	private final CSVReader<E> reader;
	//缓存条目
	private final List<E> cachedEntries;
	//当前下标
	private int currentIndex;

	public CachedCSVReaderImpl(CSVReader<E> reader) {
		this.reader = reader;
		this.cachedEntries = new ArrayList<E>();
		currentIndex = -1;
	}
	//下一个
	public boolean hasNext() {
		if (currentIndex + 1 >= cachedEntries.size()) {
			cacheNextEntry();
		}

		return currentIndex + 1 < cachedEntries.size();
	}

	public E next() {
		if (!hasNext()) {
			throw new NoSuchElementException(String.format("size: %s, index: %s", cachedEntries.size(), currentIndex + 1));
		}

		currentIndex++;
		return cachedEntries.get(currentIndex);
	}

	public boolean hasPrevious() {
		return currentIndex > 0;
	}

	public E previous() {
		if (!hasPrevious()) {
			throw new NoSuchElementException(String.format("size: %s, index: %s", cachedEntries.size(), currentIndex - 1));
		}

		currentIndex--;
		return cachedEntries.get(currentIndex);
	}

	public int nextIndex() {
		if (currentIndex >= cachedEntries.size()) {
			cacheNextEntry();
		}

		if (currentIndex >= cachedEntries.size()) {
			return cachedEntries.size();
		}

		return currentIndex + 1;
	}

	public int previousIndex() {
		return currentIndex - 1;
	}

	public E get(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("i has to be greater 0, but was " + index);
		}

		readUntil(index);

		if (cachedEntries.size() < index) {
			throw new ArrayIndexOutOfBoundsException(String.format("size: %s, index: %s", cachedEntries.size(), index));
		}

		return cachedEntries.get(index);
	}

	public void remove() {
		throw new UnsupportedOperationException("remove not allowed");
	}

	public void set(Object e) {
		throw new UnsupportedOperationException("set not allowed");
	}

	public void add(Object e) {
		throw new UnsupportedOperationException("add not allowed");
	}

	public void close() throws IOException {
		reader.close();
	}

	private void readUntil(int i) {
		while (cacheNextEntry() && cachedEntries.size() <= i);
	}
	//缓存下一个条目
	private boolean cacheNextEntry() {
		boolean success = false;
		try {
			E entry = reader.readNext();
			if (entry != null) {
				cachedEntries.add(entry);
				success = true;
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return success;
	}

}
