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
package org.mybatis.generator.codegen.mybatis3.javamapper.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;

import java.util.Set;
import java.util.TreeSet;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class ExtendsBaseDaoGenerator extends
        AbstractJavaMapperMethodGenerator {


    public ExtendsBaseDaoGenerator() {
        super();
    }

    @Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getBaseRecordType());
        Method method = new Method("");

        FullyQualifiedJavaType returnType = introspectedTable.getRules().calculateAllFieldsClass();

        interfaze.addSuperInterface(new FullyQualifiedJavaType("BaseDAO<" + returnType.getShortName() + ">"));
        // 添加@Mapper包导入
        // 使用mofa3不需要
//        importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
        // model 导入包
        importedTypes.add(type);

        addMapperAnnotations(interfaze, method);

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(
                method, interfaze, introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            //interfaze.addMethod(method);
        }
    }

    public void addMapperAnnotations(Interface interfaze, Method method) {
    }
}
