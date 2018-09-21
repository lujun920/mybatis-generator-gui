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

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class SelectByExampleWithoutBLOBsElementGenerator extends
        AbstractXmlElementGenerator {

    public SelectByExampleWithoutBLOBsElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        String fqjt = introspectedTable.getBaseRecordType();//.getExampleType();
        XmlElement answer = new XmlElement("select");
        answer.addAttribute(new Attribute("id",
                "listRecord"));
        answer.addAttribute(new Attribute(
                "resultMap", introspectedTable.getBaseResultMapId())); 
        answer.addAttribute(new Attribute("parameterType", fqjt));
        context.getCommentGenerator().addComment(answer);
        answer.addElement(new TextElement("SELECT"));

        StringBuilder sb = new StringBuilder();
        if (stringHasValue(introspectedTable
                .getSelectByExampleQueryId())) {
            sb.append('\'');
            sb.append(introspectedTable.getSelectByExampleQueryId());
            sb.append("' as QUERYID,"); 
            answer.addElement(new TextElement(sb.toString()));
        }
        answer.addElement(getBaseColumnListElement());

        sb.setLength(0);
        sb.append("FROM "); 
        sb.append(introspectedTable
                .getAliasedFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        answer.addElement(getExampleIncludeElement());

        if (context.getPlugins()
                .sqlMapSelectByExampleWithoutBLOBsElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(new TextElement(""));
            parentElement.addElement(new TextElement("<!-- ============ listRecord 列表查询 ============ -->"));
            parentElement.addElement(answer);

        }
    }
}
