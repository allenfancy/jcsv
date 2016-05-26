package com.googlecode.jcsv.writer.internal;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.googlecode.jcsv.writer.CSVEntryConverter;
/**
 * @Description	测试默认条目转换类 测试
 * @author allen
 *
 */
public class DefaultCSVEntryConverterTest {

	@Test
	public void testDefaultCSVEntryConverter(){
		CSVEntryConverter<String[]> converter = new DefaultCSVEntryConverter();
		String[] data = {"A","B","C"};
		System.out.println(converter.convertEntry(data));
	}
	@Test
	public void testConvertEntry() {
		CSVEntryConverter<String[]> converter = new DefaultCSVEntryConverter();
		String[] data = {"A", "B", "C"};

		assertArrayEquals(data, converter.convertEntry(data));
	}

}
