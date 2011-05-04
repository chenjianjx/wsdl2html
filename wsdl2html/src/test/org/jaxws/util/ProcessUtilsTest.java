package org.jaxws.util;

import org.jaxws.util.os.SystemProcessException;
import org.jaxws.util.os.SystemProcessUtils;
import org.junit.Test;


/** 
*
* @author chenjianjx
*/

public class ProcessUtilsTest  {
	 

	/**
	 * @throws SystemProcessException 
	 * @see org.jaxws.util.os.SystemProcessUtils#exec
	 */
	@Test
	public void testExec() throws SystemProcessException {
		 
		 SystemProcessUtils.exec(new String[]{"java", "-version"});
	}
}
