package com.googlecode.jcsv.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.googlecode.jcsv.CSVStrategy;


/**
 * The CSVTokenizer specifies the behaviour how the CSVReaderImpl parses each line into a List of Strings.
 * 该CSV标记者指定CSVReader默认地将如何每行解析成字符串列表的行为。
 */
public interface CSVTokenizer {
	/**
	 * Splits the line into tokens, using the CSVStrategy, passed by the CSVReaderImpl.
	 * 拆分行成令牌，使用CSVStrategy，由CSVReader默认地将Imple传递
	 * @param line the current line  当前的行数
	 * @param reader the reader may be used to read further lines if the line ends with an open quotation
	 * 		  读者可能会被使用来读取更多的行，如果该行以quotation结束
	 * @return the tokens
	 */
	public List<String> tokenizeLine(String line, CSVStrategy strategy, BufferedReader reader) throws IOException;
}
