package org.jaxws.stub2html.model;

import static org.apache.commons.lang.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Imagine a Java Language Variable = a Field in a Java Class, or a Parameter in
 * a Java Method
 * 
 * @author chenjianjx
 * 
 */
public class JavaLanguageVariable {

	/**
	 * as "order" in @XmlType(name = "order"). If the name is not specified in
	 * 
	 * @XmlType, use the field name
	 */
	private String variableName;

	/**
	 * the java type of a variable, such as "Order.class"
	 */
	private Class<?> type;

	private boolean required;

	private boolean multiOccurs;

	/**
	 * is it transmitted via soap header?
	 */
	private boolean header;

	private String description;

	public boolean isHeader() {
		return header;
	}

	public void setHeader(boolean header) {
		this.header = header;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String name) {
		this.variableName = name;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	@Override
	public String toString() {

		return new ToStringBuilder(this, SHORT_PREFIX_STYLE).append(type).append(variableName).toString();
	}

	public boolean isMultiOccurs() {
		return multiOccurs;
	}

	public void setMultiOccurs(boolean multiOccurs) {
		this.multiOccurs = multiOccurs;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
