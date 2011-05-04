package org.jaxws.stub2html.util;

import java.util.Collection;

/**
 * 
 * @author chenjianjx
 * 
 */
public class MyClassUtils {

 
	public static boolean isClassArrayOrCollection(Class<?> clazz) {
		return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
	}
}
