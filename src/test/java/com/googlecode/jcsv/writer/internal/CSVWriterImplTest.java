package com.googlecode.jcsv.writer.internal;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.jcsv.writer.CSVEntryConverter;
import com.googlecode.jcsv.writer.CSVWriter;

public class CSVWriterImplTest extends TestCase {

	private static final String NEW_LINE = System.getProperty("line.separator");
	//csvWriter
	private CSVWriter<Person> csvWriter;
	private final StringWriter stringWriter = new StringWriter();

	@Override
	@Before
	public void setUp() throws Exception {
		// build a new csvWriter that can write person objects
		csvWriter = new CSVWriterBuilder<Person>(stringWriter).entryConverter(new PersonEntryConverter()).build();
	}

	@Override
	@After
	public void tearDown() throws Exception {
		csvWriter.close();
	}

	@Test
	public void testWriteAll() throws IOException {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Hans", "im \"allen\"", 16,"SH"));
		persons.add(new Person("Klaus", "Meyer", 33,"HK"));
		
		csvWriter.writeAll(persons);
		String result = stringWriter.toString();
		InputStream in = new ByteArrayInputStream(stringWriter.toString().getBytes());
		System.out.println(result);

	}

	@Test
	public void testWrite() throws IOException {
		// write first person
		csvWriter.write(new Person("Hans", "im Glück", 16,"LA"));
		String result = stringWriter.toString();
		System.out.println(result);
		System.out.println("----------------------------");
		// write next person
		csvWriter.write(new Person("Klaus", "Meyer", 33,"NY"));
		result = stringWriter.toString();
		System.out.println(result);
	}

	public void testClose() throws IOException {
		csvWriter.close();
	}
	/**
	 * CSV条目转换器
	 * @author allen
	 *
	 */
	private static class PersonEntryConverter implements CSVEntryConverter<Person> {
		public String[] convertEntry(Person person) {
			/*String[] token = new String[3];
			token[0] = person.getFirstName();
			token[1] = person.getLastName();
			token[2] = person.getAge() + "";*/
			List<String> token = new ArrayList<String>();
			token.add(person.getFirstName());
			token.add(person.getLastName());
			token.add(person.getAge() + "");
			token.add(person.getAddress());
			return token.toArray(new String[token.size()]);
		}
	}

	private static class Person {
		private final String firstName;
		private final String lastName;
		private final int age;
		private final String address;

		public Person(String firstName, String lastName, int age,String address) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.age = age;
			this.address = address;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public int getAge() {
			return age;
		}

		public String getAddress() {
			return address;
		}
		
	}

}
