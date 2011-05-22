package org.jaxws.stub2html.view.simple;

import static org.jaxws.stub2html.view.simple.SimpleClassNameDisplayUtils.display;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


/** 
*
* @author kentc
*/

public class SimpleClassNameDisplayUtilsTest  {

    /**
     * @see org.jaxws.stub2html.view.simple.SimpleClassNameDisplayUtils#display
     */
    @Test
    public void testDisplay() {
        assertEquals("",display(""));
        
        assertEquals("A",display("A"));
        assertEquals("2",display("2"));
        assertEquals("a",display("a"));
        
        assertEquals("AB",display("AB"));
        assertEquals("22",display("22"));
        assertEquals("ab",display("ab"));
        
        
        assertEquals("Ab",display("Ab"));
        assertEquals("2b",display("2b"));
        
        
        assertEquals("a B",display("aB"));
        assertEquals("a 2",display("a2"));

        
        
        
        assertEquals("Ab Cd EFG Hi 2k",display("AbCdEFGHi2k"));

        
    
    }
	 
}
