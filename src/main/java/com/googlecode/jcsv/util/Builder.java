package com.googlecode.jcsv.util;

/**
 * The Builder interfaces indicates that the class can build
 * objects of type E.
 * 
 * 该生成器接口表示这个类创建一个E型对象
 *
 * @param <E> The type of object that should be build.
 * 			  这个类型的对象应该被创建
 */
public interface Builder<E> {
	
	/**
	 * Finally builds the object.
	 * 最终创建的对象
	 * @return the build object
	 * 返回这个对象
	 */
	public E build();
}
