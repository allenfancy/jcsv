package com.googlecode.jcsv.reader.internal;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.googlecode.jcsv.reader.CSVEntryParser;

public class DefaultCSVEntryParserTest {

	@Test
	public void testDefaultCSVEntryParser(){
		CSVEntryParser<String[]> parser  = new DefaultCSVEntryParser();
		String[] data = {"吴涛","范春雨	","123","allen"};
		String[] t = parser.parseEntry(data);
		System.out.println(t[0]);
	}
	
	@Test
	public void testParseEntry() {
		CSVEntryParser<String[]> parser = new DefaultCSVEntryParser();
		String[] data = {"A", "B", "C"};

		assertArrayEquals(data, parser.parseEntry(data));
	}


}
