package org.jaxws.stub2html.view.freemarker;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jaxws.stub2html.model.Stub;
import org.jaxws.stub2html.model.WebServiceStubSet;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.DeepUnwrap;

/**
 * 
 * @author chenjianjx
 * 
 */
public class FreemarkerWebServiceDisplayer {

	private final Template template;
	private final WebServiceStubSet serviceStubSet;

	FreemarkerWebServiceDisplayer(Template template, WebServiceStubSet serviceStubSet) {
		this.template = template;

		this.serviceStubSet = serviceStubSet;
	}

	public String displayWebSerivce() {

		try {

			Map<String, Object> rootMap = new HashMap<String, Object>();
			rootMap.put("service", serviceStubSet);

			rootMap.put("stubName", new DisplayStubNameMethodModel());
			rootMap.put("stubOgnl", new DisplayStubOgnlPathMethodModel());
			rootMap.put("stubType", new DisplayStubTypeMethodModel());
			rootMap.put("className", new DisplayClassNameMethodModel());
			rootMap.put("description", new DisplayStubDescriptionMethodModel());

			StringWriter out = new StringWriter();
			template.process(rootMap, out);
			return out.toString();

		} catch (IOException e) {
			throw new IllegalStateException(e);
		} catch (TemplateException e) {
			throw new IllegalStateException(e);
		}

	}

	private final class DisplayStubOgnlPathMethodModel implements TemplateMethodModelEx {

		@SuppressWarnings("rawtypes")
		@Override
		public Object exec(List arguments) throws TemplateModelException {
			Stub stub = (Stub) DeepUnwrap.unwrap((TemplateModel) arguments.get(0));
			String parentPath = (String) DeepUnwrap.unwrap((TemplateModel) arguments.get(1));
			if (parentPath == null || StringUtils.isEmpty(parentPath)) {
				return getStubNameConsideringMultiple(stub);
			}
			return parentPath + "." + getStubNameConsideringMultiple(stub);
		}
	}

	private final class DisplayStubNameMethodModel implements TemplateMethodModelEx {

		@SuppressWarnings("rawtypes")
		@Override
		public Object exec(List arguments) throws TemplateModelException {
			Stub stub = (Stub) DeepUnwrap.unwrap((TemplateModel) arguments.get(0));
			return getStubNameConsideringMultiple(stub);
		}
	}

	private final class DisplayStubDescriptionMethodModel implements TemplateMethodModelEx {

		@SuppressWarnings("rawtypes")
		@Override
		public Object exec(List arguments) throws TemplateModelException {
			Stub stub = (Stub) DeepUnwrap.unwrap((TemplateModel) arguments.get(0));
			return stub.getDescription();
		}
	}

	private final class DisplayClassNameMethodModel implements TemplateMethodModelEx {

		@SuppressWarnings("rawtypes")
		@Override
		public Object exec(List arguments) throws TemplateModelException {
			Class<?> clazz = (Class<?>) DeepUnwrap.unwrap((TemplateModel) arguments.get(0));
			return clazz.getSimpleName();
		}

	}

	private final class DisplayStubTypeMethodModel implements TemplateMethodModelEx {

		@SuppressWarnings("rawtypes")
		@Override
		public Object exec(List arguments) throws TemplateModelException {
			Stub stub = (Stub) DeepUnwrap.unwrap((TemplateModel) arguments.get(0));
			String typeText = stub.getType().getSimpleName();
			if (stub.isMultiOccurs()) {
				typeText += "[]";
			}
			return typeText;
		}
	}

	private static String getStubNameConsideringMultiple(Stub stub) {
		if (StringUtils.isEmpty(stub.getStubName())) {
			return "";
		}
		if (stub.isMultiOccurs()) {
			return stub.getStubName() + "[]";
		} else {
			return stub.getStubName();
		}
	}

}
