package org.jaxws.stub2html.service;

import static org.jaxws.stub2html.service.JavaLanguageVariableFactory.isVariableRequired;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import javax.xml.bind.annotation.XmlElement;

import org.jaxws.stub2html.service.JavaLanguageVariableFactory;
import org.junit.Test;

/**
 * 
 * @author chenjianjx
 * 
 */
public class JavaLanguageVariableFactoryTest {

	@Test
	public void isVariableRequiredTest() throws Exception {
		assertFalse(isVariableRequired(getXmlElementAnnotation("requiredAndNill")));
		assertTrue(isVariableRequired(getXmlElementAnnotation("requiredButNotNill")));
		assertFalse(isVariableRequired(getXmlElementAnnotation("nillButNotRequired")));
		assertFalse(isVariableRequired(getXmlElementAnnotation("notNillNotRequired")));
	}

	@Test
	public void createVariableFromFieldTest() throws Exception {
		assertNotNull(JavaLanguageVariableFactory.createVariableFromField(getField("annotated")));
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void createVariableFromFieldTest_Exception() throws Exception {
		assertNotNull(JavaLanguageVariableFactory.createVariableFromField(getField("notAnnotated")));
	}
	

	private XmlElement getXmlElementAnnotation(String fieldName) throws NoSuchFieldException {
		Field sampleField = getField(fieldName);
		XmlElement annotation = sampleField.getAnnotation(XmlElement.class);
		return annotation;
	}

	private Field getField(String fieldName) throws NoSuchFieldException {
		return SampleClass.class.getDeclaredField(fieldName);
	}
	
	
	
	@SuppressWarnings("unused")
	private static final class SampleClass {
		@XmlElement(required = true, nillable = true)
		private Integer requiredAndNill;

		@XmlElement(required = true, nillable = false)
		private Integer requiredButNotNill;

		@XmlElement(required = false, nillable = true)
		private Integer nillButNotRequired;

		@XmlElement(required = false, nillable = false)
		private Integer notNillNotRequired;

		@XmlElement
		private Integer annotated;

		private Integer notAnnotated;

	}
}


