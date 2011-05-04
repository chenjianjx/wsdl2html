package org.jaxws.stub2html.util;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author chenjianjx
 *
 */
public class GenericsUtilsTest {

	@Test
	public void getFieldGenericTypeTest() throws Exception{
		assertEquals(String.class,  GenericsUtils.getFieldGenericType( getField("stringObject")));
		assertEquals(Integer.class,  GenericsUtils.getFieldGenericType( getField("listOfInteger")));
		assertEquals(String.class,  GenericsUtils.getFieldGenericType( getField("listOfStringList")));
	}
	
 
	
	
	private Field getField(String fieldName) throws NoSuchFieldException {
		return SampleClass.class.getDeclaredField(fieldName);
	}
	
	
	
	
	@SuppressWarnings("unused")
	private static final class SampleClass {
		private String stringObject;
		private List<Integer> listOfInteger;
		
		private List<List<String>> listOfStringList;

	}
}
