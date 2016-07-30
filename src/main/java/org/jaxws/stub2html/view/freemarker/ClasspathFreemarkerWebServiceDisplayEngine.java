package org.jaxws.stub2html.view.freemarker;

import java.io.IOException;

import freemarker.template.Template;

/**
 * 
 * @author chenjianjx
 * 
 */
public class ClasspathFreemarkerWebServiceDisplayEngine extends FreemarkerWebServiceDisplayEngine {

	private String absoluteFtlClassPath;

	private ClasspathFreemarkerWebServiceDisplayEngine(String absoluteFtlClassPath) {
		super();

		if (!absoluteFtlClassPath.startsWith("/")) {
			throw new IllegalArgumentException("Template's class-path has to start with '/'");
		}
		configuration.setClassForTemplateLoading(ClasspathFreemarkerWebServiceDisplayEngine.class, "/");
		this.absoluteFtlClassPath = absoluteFtlClassPath;
	}

	public static FreemarkerWebServiceDisplayEngine createEngine(String absoluteFtlClassPath) {
		return new ClasspathFreemarkerWebServiceDisplayEngine(absoluteFtlClassPath);
	}

	public static FreemarkerWebServiceDisplayEngine createEngine() {
		String ftlPath = "/service.ftl";
		return createEngine(ftlPath);
	}

	protected Template getTemplate() {
		try {
			return configuration.getTemplate(absoluteFtlClassPath);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
