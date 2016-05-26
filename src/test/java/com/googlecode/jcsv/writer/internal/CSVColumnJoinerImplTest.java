package com.googlecode.jcsv.writer.internal;

import junit.framework.TestCase;

import org.junit.Test;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.writer.CSVColumnJoiner;

public class CSVColumnJoinerImplTest extends TestCase {

	@Test
	public void testJoinColumns() {
		CSVColumnJoiner columnJoiner = new CSVColumnJoinerImpl();

		// uses ; as delimiter
		final CSVStrategy strategy = CSVStrategy.DEFAULT;

		// simple test cases
		String[] data = {"A", "B", "C"};
		String result = columnJoiner.joinColumns(data, strategy);
		String expected = "A;B;C";
		System.out.println(result);
		assertEquals(expected, result);

		// single token test
		data = new String[]{"A"};
		result = columnJoiner.joinColumns(data, strategy);
		expected = "A";
		System.out.println(result);
		assertEquals(expected, result);

		// empty array test
		data = new String[0];
		result = columnJoiner.joinColumns(data, strategy);
		expected = "";
		System.out.println(result);
		assertEquals(expected, result);

		// more complex test cases
		// delimiter in token test case
		data = new String[]{"A", "B", "C;D"};
		result = columnJoiner.joinColumns(data, strategy);
		expected = "A;B;\"C;D\"";
		System.out.println(result);
		assertEquals(expected, result);

		// quote in token
		data = new String[]{"A", "B", "C\";\"D"};
		result = columnJoiner.joinColumns(data, strategy);
		expected = "A;B;\"C\"\";\"\"D\"";
		System.out.println(result);
		assertEquals(expected, result);
	}
}
