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
package org.mybatis.generator.codegen.mybatis3.javamapper.elements.service;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class SaveRecordServiceImplMethodGenerator extends AbstractJavaMapperMethodGenerator {

    private boolean isSimple;

    public SaveRecordServiceImplMethodGenerator(boolean isSimple) {
        super();
        this.isSimple = isSimple;
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();

        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType parameterType;
        if (isSimple) {
            parameterType = new FullyQualifiedJavaType(
                    introspectedTable.getBaseRecordType());
        } else {
            parameterType = introspectedTable.getRules()
                    .calculateAllFieldsClass();
        }
        interfaze.addImportedType(new FullyQualifiedJavaType("so.dian.mofa3.lang.enums.LogicDeleteEnum"));
        interfaze.addImportedType(new FullyQualifiedJavaType("so.dian.mofa3.lang.util.DateBuild"));
        interfaze.addImportedType(new FullyQualifiedJavaType("java.util.Date"));
        method.addAnnotation("@Override");
        method.setName("saveRecord");
        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "model"));
        method.addBodyLine("model.setDeleted(LogicDeleteEnum.FALSE.getDelete());");
        method.addBodyLine("Date date = new DateBuild().toDate();");
        method.addBodyLine("model.setCreateTime(date);");
        method.addBodyLine("model.setUpdateTime(date);");
        method.addBodyLine("return dao.saveRecord(model);");

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        addMapperAnnotations(interfaze, method);

        if (context.getPlugins().clientInsertMethodGenerated(method, interfaze,
                introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);

            interfaze.addMethod(method);
        }
    }

    public void addMapperAnnotations(Interface interfaze, Method method) {
    }
}
