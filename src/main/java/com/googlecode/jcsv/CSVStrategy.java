package com.googlecode.jcsv;
/***
 * @Description	CSV策略
 * @author allen
 *
 */
public class CSVStrategy {

	/**
	 * 默认策略
	 * The default CSV strategy.
	 * - delimiter ;  定界符
	 * - quote character " 引号字符
	 * - comment indicator # 注释指示器
	 * - do not skip header 不跳过头部
	 * - ignore empty lines  忽略空行
	 */
	public static final CSVStrategy DEFAULT = new CSVStrategy(';', '"', '#', false, true);

	/**
	 * The USA/UK csv standard.
	 * USA和UK csv 标准
	 * - delimiter ,
	 * - quote character "
	 * - comment indicator #
	 * - do not skip header
	 * - ignore empty lines
	 */
	public static final CSVStrategy UK_DEFAULT = new CSVStrategy(',', '"', '#', false, true);

	private final char delimiter;  //分隔符
	private final char quoteCharacter; //引号符
	private final char commentIndicator; //注释符
	private final boolean skipHeader; //是否跳过头部，true表示跳过，false表示跳过
	private final boolean ignoreEmptyLines; //是否忽略空的行,true是忽略，false是不忽悠

	/**
	 * 创建一个CSV策略
	 * Creates a CSVStrategy.
	 *
	 * @param delimiter
	 * @param quoteCharacter
	 * @param commentIndicator
	 * @param skipHeader
	 * @param ignoreEmptyLines
	 */
	public CSVStrategy(char delimiter, char quoteCharacter, char commentIndicator, boolean skipHeader,
			boolean ignoreEmptyLines) {
		this.delimiter = delimiter;
		this.quoteCharacter = quoteCharacter;
		this.commentIndicator = commentIndicator;
		this.skipHeader = skipHeader;
		this.ignoreEmptyLines = ignoreEmptyLines;
	}

	/**
	 * Returns the delimiter character.
	 */
	public char getDelimiter() {
		return delimiter;
	}

	/**
	 * Returns the quote character.
	 */
	public char getQuoteCharacter() {
		return quoteCharacter;
	}

	/**
	 * Returns the comment indicator.
	 * 返回注释符
	 */
	public char getCommentIndicator() {
		return commentIndicator;
	}

	/**
	 * Skip the header?
	 * 是否头部
	 * @return true, if the csv header should be skipped.
	 */
	public boolean isSkipHeader() {
		return skipHeader;
	}

	/**
	 * Ignore empty lines?
	 * 是否忽略空行
	 * @return true, if empty lines should be ignored.
	 * @return 如果返回时true，就返回空的行
	 */
	public boolean isIgnoreEmptyLines() {
		return ignoreEmptyLines;
	}
}
