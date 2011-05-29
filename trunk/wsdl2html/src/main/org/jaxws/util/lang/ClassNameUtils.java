package org.jaxws.util.lang;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author chenjianjx
 * 
 */
public class ClassNameUtils {

	/**
	 * com.sun.Hello => com/sun
	 * 
	 * @param className
	 * @return
	 */
	public static String getRelativeDir(String className) {
		int lastIndexOfPoint = className.lastIndexOf(".");
		if (lastIndexOfPoint < 0) {
			return "";
		}
		String packageName = className.substring(0, lastIndexOfPoint);
		return toRelativeDir(packageName);
	}

	/**
	 *  com/sun => com.sun
	 */
	public static String toRelativeDir(String packageName) {
		return StringUtils.replace(packageName, ".", "/");
	}

	/**
	 * com.sun.Hello => Hello.java
	 * 
	 * @param className
	 * @return
	 */
	public static String toSimpleFileName(String className) {
		String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
		return simpleClassName + ".class";
	}

	/**
	 *   com/sun =>  com.sun
	 * @param relativeDir
	 * @return
	 */
	public static String toPackageName(String relativeDir) {
		return StringUtils.replace(relativeDir, "/", ".");
	}

}
