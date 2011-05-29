package org.jaxws.stub2html.view.freemarker;

import java.io.File;
import java.io.IOException;

import org.jaxws.stub2html.view.JavaNameDisplayStrategy;

import freemarker.template.Template;

/**
 * 
 * @author chenjianjx
 * 
 */
public class FilePathFreemarkerWebServiceDisplayEngine extends FreemarkerWebServiceDisplayEngine {

    private File templateFile;

    private FilePathFreemarkerWebServiceDisplayEngine(JavaNameDisplayStrategy nameDisplayingStrategy, File templateFile) {
        super(nameDisplayingStrategy);
        if (!templateFile.exists() || !templateFile.isFile()) {
            throw new IllegalArgumentException(templateFile + " doesn't exist or is not a file");
        }
        setTemplateLoadingDir(templateFile.getParentFile());
        this.templateFile = templateFile;
    }

    private void setTemplateLoadingDir(File parentFile) {
        try {
            configuration.setDirectoryForTemplateLoading(parentFile);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static FilePathFreemarkerWebServiceDisplayEngine createEngine(JavaNameDisplayStrategy nameDisplayingStrategy, File templateFile) {
        return new FilePathFreemarkerWebServiceDisplayEngine(nameDisplayingStrategy, templateFile);

    }

    @Override
    protected Template getTemplate() {
        try {
            return configuration.getTemplate(templateFile.getName());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
