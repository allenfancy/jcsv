package com.googlecode.jcsv.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides some useful functions.
 * @Description	 提供一些有用的方法
 * 
 */
public class CSVUtil {
	
	private CSVUtil() {
		// Prevent instantiating and inheriting
	}

	/**
	 * Concats the String[] array data to a single String, using the specified
	 * delimiter as the glue.
	 *
	 * <code>implode({"A", "B", "C"}, ";")</code> would result in A;B;C
	 *
	 * @param data
	 *            the strings that should be concatinated
	 * @param delimiter
	 *            the delimiter
	 * @return the concatinated string
	 */
	public static String implode(String[] data, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			if (i != 0) {
				sb.append(delimiter);
			}

			sb.append(data[i]);
		}

		return sb.toString();
	}

	/**
	 * Splits the provided text into an array, separator specified, preserving
	 * all tokens, including empty tokens created by adjacent separators.
	 *
	 * CSVUtil.split(null, *, true) = null
	 * CSVUtil.split("", *, , true) = []
	 * CSVUtil.split("a.b.c", '.', true) = ["a", "b", "c"]
	 * CSVUtil.split("a...c", '.', true) = ["a", "", "", "c"]
	 * CSVUtil.split("a...c", '.', false) = ["a", "c"]
	 *
	 * @param str
	 *            the string to parse  需要解析的字符串
	 * @param separatorChar
	 *            the seperator char   分割字符
	 * @param preserveAllTokens 保留所有的token
	 *            if true, adjacent separators are treated as empty token
	 *            separators
	 *            如果是true，相邻的分割符被视为空的令牌分割器
	 *            
	 * @return the splitted string 分裂的字符串
	 */
	public static String[] split(String str, char separatorChar, boolean preserveAllTokens) {
		//如果需要解析的字符串为空的话，直接退出
		if (str == null) {
			return null;
		}
		//如果字符串的长度的零的话
		int len = str.length();
		if (len == 0) {
			return new String[0];
		}
		List<String> list = new ArrayList<String>();
		int i = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		while (i < len) {
			if (str.charAt(i) == separatorChar) {
				if (match || preserveAllTokens) {
					//剪切字符串
					list.add(str.substring(start, i));
					match = false;
					lastMatch = true;
				}
				start = ++i;
				continue;
			}
			lastMatch = false;
			match = true;
			i++;
		}
		if (match || preserveAllTokens && lastMatch) {
			list.add(str.substring(start, i));
		}
		return list.toArray(new String[list.size()]);
	}
}
