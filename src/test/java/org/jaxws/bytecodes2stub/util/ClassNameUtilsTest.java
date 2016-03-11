package org.jaxws.bytecodes2stub.util;

import static org.jaxws.util.lang.ClassNameUtils.getRelativeDir;
import static org.jaxws.util.lang.ClassNameUtils.toSimpleFileName;
import static org.junit.Assert.assertEquals;

import org.jaxws.util.lang.ClassNameUtils;
import org.junit.Test;

/**
 * 
 * @author chenjianjx
 */

public class ClassNameUtilsTest {

	@Test
	public void testToSimpleFileName() {
		assertEquals("String.class", toSimpleFileName("java.lang.String"));
		assertEquals("Random$RandomType.class", toSimpleFileName("com.random.Random$RandomType"));
		assertEquals("BareClass.class", toSimpleFileName("BareClass"));
	}

	@Test
	public void testGetRelativePath() {
		assertEquals("java/lang", getRelativeDir("java.lang.String"));
		assertEquals("", getRelativeDir("String"));
	}

	/**
	 * @see org.jaxws.util.lang.ClassNameUtils#toPackageName
	 */
	@Test
	public void testToPackageName() {

		assertEquals("com.sun", ClassNameUtils.toPackageName("com/sun"));
		assertEquals("", ClassNameUtils.toPackageName(""));
	}
}
