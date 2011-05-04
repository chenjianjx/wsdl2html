package org.jaxws.stub2html.view.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.jaxws.stub2html.model.Stub;
import org.jaxws.stub2html.view.JavaNameDisplayStrategy;

/**
 * 
 * @author chenjianjx
 * 
 */
public class SimpleJavaNameDisplayStrategy implements JavaNameDisplayStrategy {

	private static final Set<Class<?>> PRIMITIVE_TYPES = new HashSet<Class<?>>();
	static {
		PRIMITIVE_TYPES.add(String.class);
		PRIMITIVE_TYPES.add(Number.class);
		PRIMITIVE_TYPES.add(int.class);
		PRIMITIVE_TYPES.add(float.class);
		PRIMITIVE_TYPES.add(double.class);
		PRIMITIVE_TYPES.add(byte.class);
		PRIMITIVE_TYPES.add(char.class);
		PRIMITIVE_TYPES.add(Boolean.class);
		PRIMITIVE_TYPES.add(boolean.class);
		PRIMITIVE_TYPES.add(Date.class);
		PRIMITIVE_TYPES.add(Calendar.class);

	}

	public String displayElementName(String elementName) {
		String[] words = StringUtils.split(elementName, "-");
		List<String> capitalized = new ArrayList<String>();
		for (String word : words) {
			capitalized.add(StringUtils.capitalize(word));
		}
		return StringUtils.join(capitalized, " ");
	}

	public String displayMultiOccurs(Stub stub) {
		return stub.isMultiOccurs() ? "Y" : "&nbsp;";
	}

	public String displayRequired(Stub stub) {
		return stub.isRequried() ? "Y" : "&nbsp;";
	}

	public String displayReadableClassName(String className) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < className.length(); i++) {
			char c = className.charAt(i);
			if (!(c <= 'z' && c >= 'a')) {
				sb.append(" ");
			}
			sb.append(c);
		}
		return sb.toString();
	}

	public String displaySubType(Stub stub) {
		if (stub.getSubTypeOfParentStub() == null) {
			return "&nbsp;";
		}
		StringBuilder stringBuffer = new StringBuilder();
		stringBuffer.append(" Only For: ");
		stringBuffer.append(displayReadableClassName(stub.getSubTypeOfParentStub().getSimpleName()));
		return stringBuffer.toString();

	}

	
	
	private boolean isPrimitiveType(Class<?> type) {
		for (Class<?> primitive : PRIMITIVE_TYPES) {
			if (primitive.isAssignableFrom(type)) {
				return true;
			}
		}
		return false;
	}

	public String displayDataType(Class<?> type) {
		if (isPrimitiveType(type)) {
			return StringUtils.capitalize(type.getSimpleName());
		}
		if (type.isEnum()) {
			return Arrays.asList(type.getEnumConstants()).toString();
		}
		return "&nbsp;";
	}

}
