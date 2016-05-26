package com.googlecode.jcsv.reader.internal;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.util.Person;
import com.googlecode.jcsv.util.PersonEntryParser;

public class CSVReaderImplTest {
	private CSVReader<Person> csvReader;
	
	private static String getIpAddress() throws UnknownHostException{
		Properties p = System.getProperties();
		return p.get("os.name").toString();
	}

	@Before
	public void setUp() throws Exception {
		InputStream in = new FileInputStream(new File("/Users/allen/Desktop/allen.csv"));
		Reader reader = new InputStreamReader(in,"GBK");//(CSVReaderImplTest.class.getResourceAsStream("/Users/allen/Desktop/allen.csv"));
		System.out.println(getIpAddress());
		CSVStrategy strategy = new CSVStrategy(',', '"', '#', true, true);
		csvReader = new CSVReaderBuilder<Person>(reader).entryParser(new PersonEntryParser()).strategy(strategy).build();
	}

	@After
	public void tearDown() throws Exception {
		csvReader.close();
	}

	@Test
	@SuppressWarnings("serial")
	public void testReadAll() throws IOException {
		List<Person> result = csvReader.readAll();
		List<Person> expected = new ArrayList<Person>() {{
			add(new Person("Hans", "im \"Glück\"", 16));
			add(new Person("Klaus", "Meyer", 33));
		}};
		System.out.println(result);
		assertEquals(expected, result);
	}

	@Test
	public void testReadNext() throws IOException {
		Person result = csvReader.readNext();
		Person expected = new Person("Hans", "im \"Glück\"", 16);
		assertEquals(expected, result);

		result = csvReader.readNext();
		expected = new Person("Klaus", "Meyer", 33);
		assertEquals(expected, result);
	}

	@SuppressWarnings("serial")
	@Test(expected=IllegalStateException.class)
	public void testReadHeader() throws IOException {
		List<String> result = csvReader.readHeader();
		List<String> expected = new ArrayList<String>() {{
			add("Vorname"); add("Nachname"); add("Alter");
		}};
		assertEquals(expected, result);

		// the second call must fail
		csvReader.readHeader();
	}
}
