package com.googlecode.jcsv.writer.internal;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.writer.CSVColumnJoiner;
import com.googlecode.jcsv.writer.CSVEntryConverter;
import com.googlecode.jcsv.writer.CSVWriter;
/**
 * CSV写入类实现
 * @author allen
 *
 * @param <E>
 */
public class CSVWriterImpl<E> implements CSVWriter<E> {

	private final Writer writer;
	private final CSVStrategy strategy;
	//CSV条目
	private final CSVEntryConverter<E> entryConverter;
	//CSV列
	private final CSVColumnJoiner columnJoiner;
	
	CSVWriterImpl(CSVWriterBuilder<E> builder) {
		this.writer = builder.writer;
		this.strategy = builder.strategy;
		this.entryConverter = builder.entryConverter;
		this.columnJoiner = builder.columnJoiner;
	}

	//循环写入
	public void writeAll(List<E> data) throws IOException {
		//TODO 如果数据量比较的情况下，建议不要使用这种类型的写法。
	/*	for (E e : data) {
			write(e);
		}*/
		//建议使用如下写法，在上面的写法中，要调用迭代器，因此还需要花费一些额外的时间。
		for(int i = 0;i<data.size();i++){
			E e = data.get(i);
			write(e);
		}
	}

	public void write(E e) throws IOException {
		StringBuilder sb = new StringBuilder();
		//将对象转为字符串数组，作为列
		String[] columns = entryConverter.convertEntry(e);
		//
		String line = columnJoiner.joinColumns(columns, strategy);

		sb.append(line);
		sb.append(System.getProperty("line.separator"));

		writer.append(sb.toString());
	}

	public void flush() throws IOException {
		writer.flush();
	}

	public void close() throws IOException {
		writer.close();
	}
}
