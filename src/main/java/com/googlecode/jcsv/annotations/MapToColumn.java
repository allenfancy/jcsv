package com.googlecode.jcsv.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The MapToColumn annotation is used to instruct the AnnotationEntryParser.
 * You can specify the column in the csv file and which value processor
 * should be used for converting.
 *  
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MapToColumn {
	/**
	 * The column of the data in the csv file.
	 * This parameter is required.
	 * 在CSV文件中的一列数据
	 * @return the column in the csv file
	 * @return 在csv文件中的行数
	 */
	int column();

	/**
	 * The type of the data.
	 * 这个数据的类型
	 * If set, the appropriate ValueProcessor for this class
	 * will be used to process the data of the csv column.
	 * 如果是set，这个类的适当值护理期将被用于处理所述的csv列的数据
	 * If not set, the type of the field will be used to find
	 * the appropriate column processor.
	 * 如果不是set，字段的类型将被用来找到适当的列表处理器
	 * This parameter is optional.
	 * 这个参数是可选的
	 *
	 * @return the type of the data
	 */
	Class<?> type() default Default.class;

	/**
	 * The default value for the type parameter.
	 * 默认的参数类型
	 */
	public static class Default {}
}