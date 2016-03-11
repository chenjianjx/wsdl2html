package org.jaxws.bytecodes2stub.service;

import static org.jaxws.bytecodes2stub.service.ByteCodePackageLoadingService.getRelativeDir;
import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

/**
 * 
 * @author chenjianjx
 */

public class ByteCodePackageLoadingServiceTest {

	/**
	 * @see org.jaxws.bytecodes2stub.service.ByteCodePackageLoadingService#getRelativePath
	 */
	@Test
	public void testGetRelativePath() {

		File root = new File("c:/classes");
		assertEquals("com/sun", getRelativeDir(root, new File("c:/classes/com/sun/HelloWorld.java")));
		assertEquals("", getRelativeDir(root, new File("c:/classes/Root.java")));
	}
}
