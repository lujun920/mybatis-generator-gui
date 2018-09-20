/**
 *    Copyright 2006-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.api.dom.java;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.dom.OutputUtilities;

/**
 * The Class JavaElement.
 *
 * @author Jeff Butler
 */
public abstract class JavaElement {
    
    /** The java doc lines. */
    private List<String> javaDocLines;

    /** The visibility. */
    private JavaVisibility visibility = JavaVisibility.DEFAULT;

    /** The is static. */
    private boolean isStatic;

    /** The is final. */
    private boolean isFinal;

    /** The annotations. */
    private List<String> annotations;

    private boolean isClass;

    private boolean isInterface;

    /**
     * 是否为dao接口
     */
    private boolean isDao;

    /**
     * 是否为Service接口
     */
    private boolean isService;

    /**
     * 是否为Service 接口实现
     */
    private boolean isServiceImpl;

    /**
     * Instantiates a new java element.
     */
    public JavaElement() {
        super();
        javaDocLines = new ArrayList<String>();
        annotations = new ArrayList<String>();
    }
    
    /**
     * Copy Constructor.
     *
     * @param original
     *            the original
     */
    public JavaElement(JavaElement original) {
        this();
        this.annotations.addAll(original.annotations);
        this.isFinal = original.isFinal;
        this.isStatic = original.isStatic;
        this.javaDocLines.addAll(original.javaDocLines);
        this.visibility = original.visibility;
        this.isDao= original.isDao;
        this.isService= original.isService;
        this.isServiceImpl= original.isServiceImpl;
        this.isClass= original.isClass;
        this.isInterface= original.isInterface;
    }

    /**
     * Gets the java doc lines.
     *
     * @return Returns the javaDocLines.
     */
    public List<String> getJavaDocLines() {
        return javaDocLines;
    }

    /**
     * Adds the java doc line.
     *
     * @param javaDocLine
     *            the java doc line
     */
    public void addJavaDocLine(String javaDocLine) {
        javaDocLines.add(javaDocLine);
    }

    /**
     * Gets the annotations.
     *
     * @return the annotations
     */
    public List<String> getAnnotations() {
        return annotations;
    }

    /**
     * Adds the annotation.
     *
     * @param annotation
     *            the annotation
     */
    public void addAnnotation(String annotation) {
        annotations.add(annotation);
    }

    /**
     * Gets the visibility.
     *
     * @return Returns the visibility.
     */
    public JavaVisibility getVisibility() {
        return visibility;
    }

    /**
     * Sets the visibility.
     *
     * @param visibility
     *            The visibility to set.
     */
    public void setVisibility(JavaVisibility visibility) {
        this.visibility = visibility;
    }

    /**
     * Adds the suppress type warnings annotation.
     */
    public void addSuppressTypeWarningsAnnotation() {
        addAnnotation("@SuppressWarnings(\"unchecked\")"); //$NON-NLS-1$
    }

    /**
     * Adds the formatted javadoc.
     *
     * @param sb
     *            the sb
     * @param indentLevel
     *            the indent level
     */
    public void addFormattedJavadoc(StringBuilder sb, int indentLevel) {
        for (String javaDocLine : javaDocLines) {
            OutputUtilities.javaIndent(sb, indentLevel);
            sb.append(javaDocLine);
            OutputUtilities.newLine(sb);
        }
    }

    /**
     * Adds the formatted annotations.
     *
     * @param sb
     *            the sb
     * @param indentLevel
     *            the indent level
     */
    public void addFormattedAnnotations(StringBuilder sb, int indentLevel) {
        for (String annotation : annotations) {
            OutputUtilities.javaIndent(sb, indentLevel);
            sb.append(annotation);
            OutputUtilities.newLine(sb);
        }
    }

    /**
     * Checks if is final.
     *
     * @return true, if is final
     */
    public boolean isFinal() {
        return isFinal;
    }

    /**
     * Sets the final.
     *
     * @param isFinal
     *            the new final
     */
    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    /**
     * Checks if is static.
     *
     * @return true, if is static
     */
    public boolean isStatic() {
        return isStatic;
    }

    /**
     * Sets the static.
     *
     * @param isStatic
     *            the new static
     */
    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    /**
     * Getter method for property <tt>isDao</tt>.
     *
     * @return property value of isDao
     */
    public boolean isDao() {
        return isDao;
    }

    /**
     * Setter method for property <tt>isDao</tt>.
     *
     * @param isDao  value to be assigned to property isDao
     */
    public void setDao(final boolean dao) {
        isDao = dao;
    }

    /**
     * Getter method for property <tt>isService</tt>.
     *
     * @return property value of isService
     */
    public boolean isService() {
        return isService;
    }

    /**
     * Setter method for property <tt>isService</tt>.
     *
     * @param isService  value to be assigned to property isService
     */
    public void setService(final boolean service) {
        isService = service;
    }

    /**
     * Getter method for property <tt>isClass</tt>.
     *
     * @return property value of isClass
     */
    public boolean isClass() {
        return isClass;
    }

    /**
     * Setter method for property <tt>isClass</tt>.
     *
     * @param isClass  value to be assigned to property isClass
     */
    public void setClass(final boolean aClass) {
        isClass = aClass;
    }

    /**
     * Getter method for property <tt>isInterface</tt>.
     *
     * @return property value of isInterface
     */
    public boolean isInterface() {
        return isInterface;
    }

    /**
     * Setter method for property <tt>isInterface</tt>.
     *
     * @param isInterface  value to be assigned to property isInterface
     */
    public void setInterface(final boolean anInterface) {
        isInterface = anInterface;
    }
}
