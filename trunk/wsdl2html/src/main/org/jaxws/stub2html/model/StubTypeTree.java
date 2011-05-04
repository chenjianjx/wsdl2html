package org.jaxws.stub2html.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A tree-like structure indicating the inheritance of java classes involved in
 * the stubs <br/>
 * 
 * <b>Important</b>: B.class is considered the child type of A.class, if and
 * only if <br/>
 * a. A.class is assignable from B.class in terms of Java language<br/>
 * b. and A.class is annotated as @XmlSeeAlso(B.class)
 * 
 * 
 * @author chenjianjx
 * 
 */
public class StubTypeTree {

	private Class<?> type;

	private List<StubTypeTree> children = new ArrayList<StubTypeTree>();

	private StubTypeTree parent;

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public List<StubTypeTree> getChildren() {
		return children;
	}

	public void addChild(StubTypeTree child) {
		children.add(child);
	}

	public StubTypeTree getParent() {
		return parent;
	}

	public void setParent(StubTypeTree parent) {
		this.parent = parent;
		parent.addChild(this);
	}
}
