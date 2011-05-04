package org.jaxws.util.lang;

import org.junit.Test;

import static org.jaxws.util.lang.NameConversionUtils.camelToHyphen;
import static org.junit.Assert.*;
import org.jaxws.util.lang.NameConversionUtils;


/** 
*
* @author chenjianjx
*/

public class NameConversionUtilsTest  {
	public NameConversionUtilsTest  (){
	}

	 
	@Test
	public void testIsLowerCaseLetter() {
		 assertTrue(NameConversionUtils.isLowerCaseLetter('d'));
		 assertFalse(NameConversionUtils.isLowerCaseLetter('D'));
		 assertFalse(NameConversionUtils.isLowerCaseLetter('3'));
	}

	/**
	 * @see org.jaxws.util.lang.NameConversionUtils#isUpperCaseLetter
	 */
	@Test
	public void testIsUpperCaseLetter() {
		 assertTrue(NameConversionUtils.isUpperCaseLetter('D'));
		 assertFalse(NameConversionUtils.isUpperCaseLetter('d'));
		 assertFalse(NameConversionUtils.isUpperCaseLetter('3'));
	
	}


	/**
	 * @see org.jaxws.util.lang.NameConversionUtils#camelToHyphen
	 */
	@Test
	public void testCamelToHyphen() {
		 
		assertEquals("hello-world", camelToHyphen("helloWorld"));
		assertEquals("hello-world", camelToHyphen("HelloWorld"));
		assertEquals("hello-world-baby", camelToHyphen("helloWorldBaby"));
		assertEquals("hello-world2-baby", camelToHyphen("helloWorld2Baby"));
	}
}


