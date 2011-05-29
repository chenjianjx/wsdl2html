package org.jaxws.util;

import org.jaxws.util.os.SystemProcessUtils;
import org.junit.Test;


/** 
*
* @author chenjianjx
*/

public class ProcessUtilsTest  {
	 

	/**
 
	 * @see org.jaxws.util.os.SystemProcessUtils#exec
	 */
	@Test
	public void testExec() throws Exception {
		 
		 SystemProcessUtils.exec(new String[]{"java", "-version"});
	}
}
