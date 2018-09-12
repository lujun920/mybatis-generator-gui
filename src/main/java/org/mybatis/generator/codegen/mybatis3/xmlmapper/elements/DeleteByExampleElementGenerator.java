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
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class DeleteByExampleElementGenerator extends
        AbstractXmlElementGenerator {

    public DeleteByExampleElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        //XmlElement answer = new XmlElement("delete"); //$NON-NLS-1$
        //String fqjt = introspectedTable.getExampleType();
        //answer.addAttribute(new Attribute(
        //        "id", introspectedTable.getDeleteByExampleStatementId())); //$NON-NLS-1$
        //answer.addAttribute(new Attribute("parameterType", fqjt)); //$NON-NLS-1$
        //context.getCommentGenerator().addComment(answer);
        //StringBuilder sb = new StringBuilder();
        //sb.append("delete from "); //$NON-NLS-1$
        //sb.append(introspectedTable
        //        .getAliasedFullyQualifiedTableNameAtRuntime());
        //answer.addElement(new TextElement(sb.toString()));
        //answer.addElement(getExampleIncludeElement());
        //
        //if (context.getPlugins().sqlMapDeleteByExampleElementGenerated(
        //        answer, introspectedTable)) {
        //    parentElement.addElement(answer);
        //}

        /**
         * 删除改为逻辑删除实现
         * baizhang
         */
        XmlElement answer = new XmlElement("update"); //$NON-NLS-1$
        String fqjt = introspectedTable.getBaseRecordType();
        answer.addAttribute(new Attribute(
                "id", "removeRecord")); //$NON-NLS-1$
        answer.addAttribute(new Attribute("parameterType", fqjt)); //$NON-NLS-1$
        context.getCommentGenerator().addComment(answer);
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE "); //$NON-NLS-1$
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        sb.append(" SET deleted = 1 WHERE ");
        sb.append(MyBatis3FormattingUtilities
                .getEscapedColumnName(introspectedTable.getPrimaryKeyColumns().get(0)));
        sb.append(" = "); //$NON-NLS-1$
        sb.append(MyBatis3FormattingUtilities
                .getParameterClause(introspectedTable.getPrimaryKeyColumns().get(0)));
        answer.addElement(new TextElement(sb.toString()));
        //answer.addElement(getExampleIncludeElement());

        if (context.getPlugins().sqlMapDeleteByExampleElementGenerated(
                answer, introspectedTable)) {
            parentElement.addElement(new TextElement(""));
            parentElement.addElement(new TextElement("<!-- ============ removeRecord 逻辑删除 ============ -->"));
            parentElement.addElement(answer);
        }
    }
}
