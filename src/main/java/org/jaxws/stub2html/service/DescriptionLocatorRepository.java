package org.jaxws.stub2html.service;

import org.jaxws.wsdl2html.ui.Wsdl2HtmlMain;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class DescriptionLocatorRepository {
    private static DescriptionLocatorRepository instance = null;
    private DescriptionLocatorRepository() {}
    private List<Description> contents = new ArrayList();

    public static synchronized DescriptionLocatorRepository getInstance() {
        if (instance == null) instance = new DescriptionLocatorRepository();
        return instance;
    }

    public String findDescriptionByMethod(Method method) {
        if (contents.isEmpty()) {
            loadContents();
        }
        String descriptionFound = "";
        for (Description description : contents) {
            if (description.getSymbol().contains(method.getName())) {
                descriptionFound = description.getContent();
            }
        }
        return descriptionFound;
    }

    private void loadContents() {
        File dir = new File(Wsdl2HtmlMain.getDescriptionsPath());
        File[] files = dir.listFiles(new FilenameFilter() {
                                        public boolean accept(File dir, String filename) {
                                            return filename.endsWith(".txt");
                                        }
                                    });
        if (files == null) {
            return;
        }
        for (File file : files) {
            try {
                String content = "";
                for (String allLines : Files.readAllLines(file.toPath(), Charset.defaultCharset())) {
                    content += allLines;
                }
                contents.add(new Description(file.getName(), content));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    static class Description {
        private final String symbol;
        private final String content;

        public Description(String symbol, String content) {
            this.symbol = symbol;
            this.content = content;
        }

        public String getSymbol() {
            return symbol;
        }

        public String getContent() {
            return content;
        }
    }

}
