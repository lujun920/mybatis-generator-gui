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
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.mybatis.generator.api.dom.OutputUtilities.calculateImports;
import static org.mybatis.generator.api.dom.OutputUtilities.javaIndent;
import static org.mybatis.generator.api.dom.OutputUtilities.newLine;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * The Class Interface.
 *
 * @author Jeff Butler
 */
public class Interface extends JavaElement implements CompilationUnit {
    
    /** The imported types. */
    private Set<FullyQualifiedJavaType> importedTypes;
    
    /** The static imports. */
    private Set<String> staticImports;

    /** The type. */
    private FullyQualifiedJavaType type;

    /** The super interface types. */
    private Set<FullyQualifiedJavaType> superInterfaceTypes;

    /**
     * 接口实现set
     */
    private Set<FullyQualifiedJavaType> superImplTypes;

    /** The methods. */
    private List<Method> methods;

    /** The file comment lines. */
    private List<String> fileCommentLines;

    /**
     * Instantiates a new interface.
     *
     * @param type
     *            the type
     */
    public Interface(FullyQualifiedJavaType type) {
        super();
        this.type = type;
        superInterfaceTypes = new LinkedHashSet<FullyQualifiedJavaType>();
        superImplTypes= new LinkedHashSet<>();
        methods = new ArrayList<Method>();
        importedTypes = new TreeSet<FullyQualifiedJavaType>();
        fileCommentLines = new ArrayList<String>();
        staticImports = new TreeSet<String>();
    }

    /**
     * Instantiates a new interface.
     *
     * @param type
     *            the type
     */
    public Interface(String type) {
        this(new FullyQualifiedJavaType(type));
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#getImportedTypes()
     */
    @Override
    public Set<FullyQualifiedJavaType> getImportedTypes() {
        return Collections.unmodifiableSet(importedTypes);
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#addImportedType(org.mybatis.generator.api.dom.java.FullyQualifiedJavaType)
     */
    @Override
    public void addImportedType(FullyQualifiedJavaType importedType) {
        if (importedType.isExplicitlyImported()
                && !importedType.getPackageName().equals(type.getPackageName())) {
            importedTypes.add(importedType);
        }
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#getFormattedContent()
     */
    @Override
    public String getFormattedContent() {
        StringBuilder sb = new StringBuilder();

        for (String commentLine : fileCommentLines) {
            sb.append(commentLine);
            newLine(sb);
        }

        if (stringHasValue(getType().getPackageName())) {
            sb.append("package "); //$NON-NLS-1$
            sb.append(getType().getPackageName());
            sb.append(';');
            newLine(sb);
            newLine(sb);
        }

        for (String staticImport : staticImports) {
            sb.append("import static "); //$NON-NLS-1$
            sb.append(staticImport);
            sb.append(';');
            newLine(sb);
        }

        if (staticImports.size() > 0) {
            newLine(sb);
        }
        
        Set<String> importStrings = calculateImports(importedTypes);
        for (String importString : importStrings) {
            sb.append(importString);
            newLine(sb);
        }

        if (importStrings.size() > 0) {
            newLine(sb);
        }

        sb.append("");
        newLine(sb);
        int indentLevel = 0;

        addFormattedJavadoc(sb, indentLevel);
        addFormattedAnnotations(sb, indentLevel);
        if(isDao()){
            sb.append("@Mapper");
        }
        if(isService()){
            sb.append("@Service");
        }

        newLine(sb);

        sb.append(getVisibility().getValue());

        if (isStatic()) {
            sb.append("static "); //$NON-NLS-1$
        }

        if (isFinal()) {
            sb.append("final "); //$NON-NLS-1$
        }

        sb.append("class "); //$NON-NLS-1$
        sb.append(getType().getShortName());

        if (getSuperInterfaceTypes().size() > 0) {
            sb.append(" extends "); //$NON-NLS-1$

            boolean comma = false;
            for (FullyQualifiedJavaType fqjt : getSuperInterfaceTypes()) {
                if (comma) {
                    sb.append(", "); //$NON-NLS-1$
                } else {
                    comma = true;
                }

                sb.append(JavaDomUtils.calculateTypeName(this, fqjt));
            }
        }

        // 接口实现
        if (getSuperImplTypes().size() > 0) {
            sb.append(" implements "); //$NON-NLS-1$

            boolean comma = false;
            for (FullyQualifiedJavaType fqjt : getSuperImplTypes()) {
                if (comma) {
                    sb.append(", "); //$NON-NLS-1$
                } else {
                    comma = true;
                }

                sb.append(JavaDomUtils.calculateTypeName(this, fqjt));
            }
        }

        sb.append(" {"); //$NON-NLS-1$
        indentLevel++;

        Iterator<Method> mtdIter = getMethods().iterator();
        while (mtdIter.hasNext()) {
            newLine(sb);
            Method method = mtdIter.next();
            if(isService()){
                sb.append(method.getFormattedContent(indentLevel, false, this));
            }else {
                sb.append(method.getFormattedContent(indentLevel, true, this));
            }

            if (mtdIter.hasNext()) {
                newLine(sb);
            }
        }

        indentLevel--;
        newLine(sb);
        javaIndent(sb, indentLevel);
        sb.append('}');

        return sb.toString();
    }

    /**
     * Adds the super interface.
     *
     * @param superInterface
     *            the super interface
     */
    public void addSuperInterface(FullyQualifiedJavaType superInterface) {
        superInterfaceTypes.add(superInterface);
    }

    public void addSuperImpl(FullyQualifiedJavaType superInterface) {
        superImplTypes.add(superInterface);
    }

    /**
     * Gets the methods.
     *
     * @return Returns the methods.
     */
    public List<Method> getMethods() {
        return methods;
    }

    /**
     * Adds the method.
     *
     * @param method
     *            the method
     */
    public void addMethod(Method method) {
        methods.add(method);
    }

    /**
     * Gets the type.
     *
     * @return Returns the type.
     */
    @Override
    public FullyQualifiedJavaType getType() {
        return type;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#getSuperClass()
     */
    @Override
    public FullyQualifiedJavaType getSuperClass() {
        // interfaces do not have superclasses
        return null;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#getSuperInterfaceTypes()
     */
    @Override
    public Set<FullyQualifiedJavaType> getSuperInterfaceTypes() {
        return superInterfaceTypes;
    }

    /**
     * Getter method for property <tt>superImplTypes</tt>.
     *
     * @return property value of superImplTypes
     */
    public Set<FullyQualifiedJavaType> getSuperImplTypes() {
        return superImplTypes;
    }

    /* (non-Javadoc)
         * @see org.mybatis.generator.api.dom.java.CompilationUnit#isJavaInterface()
         */
    @Override
    public boolean isJavaInterface() {
        return true;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#isJavaEnumeration()
     */
    @Override
    public boolean isJavaEnumeration() {
        return false;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#addFileCommentLine(java.lang.String)
     */
    @Override
    public void addFileCommentLine(String commentLine) {
        fileCommentLines.add(commentLine);
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#getFileCommentLines()
     */
    @Override
    public List<String> getFileCommentLines() {
        return fileCommentLines;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#addImportedTypes(java.util.Set)
     */
    @Override
    public void addImportedTypes(Set<FullyQualifiedJavaType> importedTypes) {
        this.importedTypes.addAll(importedTypes);
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#getStaticImports()
     */
    @Override
    public Set<String> getStaticImports() {
        return staticImports;
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#addStaticImport(java.lang.String)
     */
    @Override
    public void addStaticImport(String staticImport) {
        staticImports.add(staticImport);
    }

    /* (non-Javadoc)
     * @see org.mybatis.generator.api.dom.java.CompilationUnit#addStaticImports(java.util.Set)
     */
    @Override
    public void addStaticImports(Set<String> staticImports) {
        this.staticImports.addAll(staticImports);
    }
}
