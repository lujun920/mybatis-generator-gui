/*
 * Dian.so Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package org.mybatis.generator.codegen.mybatis3.javamapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.CountByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.service.ExtendsBaseServiceGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.service.GetRecordServiceMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.service.SelectByExampleServiceMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.PropertyRegistry;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

/**
 *
 * TODO
 * @author ${baizhang}
 * @version $Id: JavaServiceGenerator.java, v 0.1 2018-09-16 下午9:51 Exp $
 */
public class JavaServiceGenerator extends AbstractJavaClientGenerator {

    /**
     *
     */
    public JavaServiceGenerator() {
        super(true);
    }

    public JavaServiceGenerator(boolean requiresMatchedServiceGenerator) {
        super(requiresMatchedServiceGenerator);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
                introspectedTable.getFullyQualifiedTable().toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaServiceType());
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        interfaze.setInterface(true);
        /**
         * 版权信息
         */
        interfaze.addFileCommentLine("/*\n"
                + " * Dian.so Inc.\n"
                + " * Copyright (c) 2016-" + Calendar.getInstance().get(Calendar.YEAR) + " All Rights Reserved.\n"
                + " */");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //StringBuilder sb = new StringBuilder();
        //sb.append("/**\n");
        //sb.append(" * 继承于BaseService，默认有五个方法实现\n");
        //sb.append(" * listRecord、getRecord、saveRecord、removeRecord、updateRecord\n");
        //sb.append(" * 该类默认接口方法可以删除\n");
        //sb.append(" *\n");
        //sb.append(" * @author MBG工具生成\n");
        //sb.append(" * @version $Id: ").append(type.getShortName())
        //        .append(".java, v 0.1 ")
        //        .append(format.format(new Date()))
        //        .append(" Exp $\n");
        //sb.append(" */");
        //interfaze.addJavaDocLine(sb.toString());
        commentGenerator.addJavaFileComment(interfaze);

        String rootInterface = introspectedTable
                .getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        if (!stringHasValue(rootInterface)) {
            rootInterface = context.getJavaClientGeneratorConfiguration()
                    .getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        }

        if (stringHasValue(rootInterface)) {
            FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(
                    rootInterface);
            interfaze.addSuperInterface(fqjt);
            interfaze.addImportedType(fqjt);
        }

        // 继承于BaseService
        if (context.getJavaClientGeneratorConfiguration().getNonNeedMethod()) {
            StringBuilder sb = new StringBuilder();
            sb.append("/**\n");
            sb.append(" * 继承于BaseService，默认有五个方法实现\n");
            sb.append(" * listRecord、getRecord、saveRecord、removeRecord、updateRecord\n");
            sb.append(" *\n");
            sb.append(" * @author MBG工具生成\n");
            sb.append(" * @version $Id: ").append(type.getShortName())
                    .append(".java, v 0.1 ")
                    .append(format.format(new Date()))
                    .append(" Exp $\n");
            sb.append(" */");
            interfaze.addJavaDocLine(sb.toString());
            interfaze.addImportedType(new FullyQualifiedJavaType("so.dian.mofa3.template.service.IBaseService"));
            addExtendsBaseServiceGenerator(interfaze);
        }else{
            StringBuilder sb = new StringBuilder();
            sb.append("/**\n");
            sb.append(" * 工具生成默认有五个方法实现\n");
            sb.append(" * listRecord、getRecord、saveRecord、removeRecord、updateRecord\n");
            sb.append(" *\n");
            sb.append(" * @author MBG工具生成\n");
            sb.append(" * @version $Id: ").append(type.getShortName())
                    .append(".java, v 0.1 ")
                    .append(format.format(new Date()))
                    .append(" Exp $\n");
            sb.append(" */");
            interfaze.addJavaDocLine(sb.toString());

            addSelectByExampleWithoutBLOBsMethod(interfaze);
            addSelectByPrimaryKeyMethod(interfaze);
            addInsertMethod(interfaze);
            addDeleteByExampleMethod(interfaze);
            addUpdateByPrimaryKeySelectiveMethod(interfaze);
        }



        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        if (context.getPlugins().clientGenerated(interfaze, null,
                introspectedTable)) {
            answer.add(interfaze);
        }

        List<CompilationUnit> extraCompilationUnits = getExtraCompilationUnits();
        if (extraCompilationUnits != null) {
            answer.addAll(extraCompilationUnits);
        }

        return answer;
    }

    protected void addCountByExampleMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateCountByExample()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new CountByExampleMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addDeleteByExampleMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateDeleteByExample()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByExampleMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addInsertMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateInsert()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator(false);
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addSelectByExampleWithoutBLOBsMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleServiceMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new GetRecordServiceMethodGenerator(false);
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeySelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addExtendsBaseServiceGenerator(Interface interfaze) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new ExtendsBaseServiceGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }


    protected void initializeAndExecuteGenerator(
            AbstractJavaMapperMethodGenerator methodGenerator,
            Interface interfaze) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addInterfaceElements(interfaze);
    }

    public List<CompilationUnit> getExtraCompilationUnits() {
        return null;
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new XMLMapperGenerator();
    }
}