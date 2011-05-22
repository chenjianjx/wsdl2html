package org.jaxws.stub2html.view.simple;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jaxws.stub2html.model.Stub;
import org.jaxws.stub2html.model.StubTypeTree;
import org.jaxws.stub2html.model.WebMethodStubSet;
import org.jaxws.stub2html.view.JavaNameDisplayStrategy;

/**
 * Display stubs as simple HTML Tables
 * 
 * @author chenjianjx
 * 
 */
public class SimpleWebMethodDisplayer {

    private StringBuffer html;
    private WebMethodStubSet methodStubSet;
    private JavaNameDisplayStrategy nameDisplayingStrategy;

    public SimpleWebMethodDisplayer(WebMethodStubSet methodStubSet, JavaNameDisplayStrategy nameDisplayingStrategy) {
        this.methodStubSet = methodStubSet;
        this.nameDisplayingStrategy = nameDisplayingStrategy;
        this.html = new StringBuffer();
    }

    public String displayWebMethod() {
        displayMethodName();
        displayRequest();

        displayResponse();

        if (!methodStubSet.getStubTypeTreeRepository().isEmpty()) {
            displayStubTreeRepository();
        }

        return html.toString();
    }

    private void displayStubTreeRepository() {
        html.append("<h3>Type Hierachy</h3>");
        html.append(newLines());
        for (StubTypeTree tree : methodStubSet.getStubTypeTreeRepository().getAllTrees()) {
            if (tree.getParent() != null) {
                continue;
            }
            html.append(displayTree(0, tree));
            html.append(newLines());
        }

    }

    private String displayTree(int indence, StubTypeTree tree) {

        StringBuffer treeHtml = new StringBuffer();
        insertBlanks(treeHtml, indence);
        addNormalLineBreak(treeHtml, nameDisplayingStrategy.displayClassName(tree.getType()));
        addNormalLineBreak(treeHtml, "<br/>");
        for (StubTypeTree child : tree.getChildren()) {
            treeHtml.append(displayTree(indence + 1, child));

        }
        return treeHtml.toString();
    }

    private void displayResponse() {
        html.append("<h3>Response</h3>");
        html.append(newLines());

        if (methodStubSet.getResponseStub() == null) {
            html.append("No Response");
        } else {
            html.append(toTable(methodStubSet.getResponseStub()));
        }

    }

    private String toTable(Stub stub) {
        return toTable(Arrays.asList(stub));
    }

    private void displayMethodName() {
        html.append("<h3>" + methodStubSet.getMethodName() + "</h3>");
        html.append(newLines());
    }

    private void displayRequest() {
        html.append("<h3>Request</h3>");
        html.append(newLines());
        html.append(toTable(methodStubSet.getRequestStubs()));
        html.append(newLines());
    }

    private String toTable(List<Stub> stubs) {

        StringBuffer table = new StringBuffer();
        addNormalLineBreak(table, "<table border='1' width='1000px'>\n");
        addNormalLineBreak(table, "	<tr bgColor='#EEEEEE'>");
        addNormalLineBreak(table, "		<td width='380px'>Name</td>");
        addNormalLineBreak(table, "		<td width='200px'>Scope</td>");
        addNormalLineBreak(table, "		<td width='50px'>Type</td>");
        addNormalLineBreak(table, "		<td width='10px'>Required</td>");
        addNormalLineBreak(table, "		<td width='10px'>Multiple</td>");
        addNormalLineBreak(table, "	</tr>");
        for (Stub stub : stubs) {
            table.append(toRows(0, stub));
        }
        addNormalLineBreak(table, "</table>");

        return table.toString();
    }

    private void addNormalLineBreak(StringBuffer stringBuffer, String line) {
        stringBuffer.append(line + "\n");
    }

    private String newLines() {
        return "<p/><p/><p/><p/>";
    }

    private String toRows(int indence, Stub stub) {

        StringBuffer rows = new StringBuffer();
        addNormalLineBreak(rows, "<tr>");
        addNormalLineBreak(rows, toTD(indence, nameDisplayingStrategy.displayElementName(stub.getStubName())));
        addNormalLineBreak(rows, toTD(displaySubType(stub)));
        addNormalLineBreak(rows, toTD(defaultHtml(nameDisplayingStrategy.displayElementType(stub.getType()))));
        addNormalLineBreak(rows, toTD((stub.isRequired() ? "Y" : "&nbsp;")));
        addNormalLineBreak(rows, toTD((stub.isMultiOccurs() ? "Y" : "&nbsp;")));
        addNormalLineBreak(rows, "</tr>");
        for (Stub child : stub.getChildStubs()) {
            rows.append(toRows(indence + 1, child));
        }
        return rows.toString();
    }

    private String defaultHtml(String str) {
        if (StringUtils.isEmpty(str)) {
            return "&nbsp";
        }
        return str;
    }

    private String displaySubType(Stub stub) {
        if (stub.getSubTypeOfParentStub() == null) {
            return "&nbsp;";
        }
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(" Only For: ");
        stringBuffer.append(nameDisplayingStrategy.displayClassName(stub.getSubTypeOfParentStub()));
        return stringBuffer.toString();

    }

    private String toTD(int indence, String content) {
        StringBuffer td = new StringBuffer();
        addNormalLineBreak(td, "<td>");
        insertBlanks(td, indence);
        addNormalLineBreak(td, content);
        addNormalLineBreak(td, "</td>");
        return td.toString();
    }

    private void insertBlanks(StringBuffer stringBuffer, int indence) {
        for (int i = 0; i < indence; i++) {
            stringBuffer.append("&nbsp;&nbsp;&nbsp;&nbsp;");
        }
    }

    private String toTD(String content) {
        return this.toTD(0, content);
    }

}
