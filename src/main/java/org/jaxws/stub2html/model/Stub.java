package org.jaxws.stub2html.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * Imagine a stub = an @XMLElement. It's a tree-like data structure
 * 
 * @author chenjianjx
 * 
 */
public class Stub {

	/**
	 * as "order" in @XmlType(name = "order")
	 */
	private String stubName;

	/**
	 * the java type of a element, such as "Order.class"
	 */
	private Class<?> type;

	private boolean required;

	private boolean multiOccurs;

	private boolean header;

	/**
	 * Child elements, such as {orderId, orderDate}
	 */
	private List<Stub> childStubs = new ArrayList<Stub>();

	private Stub parentStub;

	/**
	 * if parent stub's type = Product, is it FunProduct or NotFunProduct?
	 */
	private Class<?> subTypeOfParentStub;
	private String description = "TODO";

	public Stub getParentStub() {
		return parentStub;
	}

	public Stub getFirstAncestorWithType(Class<?> t) {
		Stub s = this.getParentStub();

		while (s != null) {
			if (t.equals(s.getType())) {
				return s;
			}
			s = s.getParentStub();
		}
		return null;
	}

	public List<String> getNamePathFromMeToRootAncestor() {
		List<String> list = new ArrayList<String>();
		Stub s = this;
		while (s != null) {
			list.add(s.getStubName());
			s = s.getParentStub();
		}
		return list;
	}

	public boolean isSameTypeWithSomeAncestor() {
		Stub ancestor = getFirstAncestorWithType(this.type);
		return ancestor != null;
	}

	public void setParentStubRelation(Stub parentStub) {
		if (parentStub == null) {
			return;
		}
		this.parentStub = parentStub;
		parentStub.childStubs.add(this);
	}

	public String getStubName() {
		return stubName;
	}

	public void setStubName(String stubName) {
		this.stubName = stubName;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public List<Stub> getChildStubs() {
		return childStubs;
	}

	// public void addChild(Stub e) {
	// childStubs.add(e);
	// e.setParentStub(this);
	// }

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

	public Class<?> getSubTypeOfParentStub() {
		return subTypeOfParentStub;
	}

	public void setSubTypeOfParentStub(Class<?> subTypeOfDeclaringStub) {
		this.subTypeOfParentStub = subTypeOfDeclaringStub;
	}

	public boolean isMultiOccurs() {
		return multiOccurs;
	}

	public void setMultiOccurs(boolean multiOccurs) {
		this.multiOccurs = multiOccurs;
	}

	public String getDescription() {
		if (this.header) {
			return "In header";
		}
		return description;
	}

	@Override
	public String toString() {
		String path = StringUtils.join(getNamePathFromMeToRootAncestor(), "<==");
		return "[Stub: " + type.getSimpleName() + "," + path + "]";
	}

	public void setStubDescription(String stubDescription) {
		this.description = stubDescription;
	}
}
