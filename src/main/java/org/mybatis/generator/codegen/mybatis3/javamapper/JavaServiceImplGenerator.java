/*
 * Dian.so Inc.
 * Copyright (c) 2016-2018 All Rights Reserved.
 */
package org.mybatis.generator.codegen.mybatis3.javamapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.service.ExtendsBaseServiceImplGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.service.GetRecordServiceImplMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.service.RemoveRecordServiceImplMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.service.SaveRecordServiceImplMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.service.SelectByExampleServiceImplMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.service.UpdateRecordServiceImplMethodGenerator;
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
public class JavaServiceImplGenerator extends AbstractJavaClientGenerator {

    /**
     *
     */
    public JavaServiceImplGenerator() {
        super(true);
    }

    public JavaServiceImplGenerator(boolean requiresMatchedServiceGenerator) {
        super(requiresMatchedServiceGenerator);
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
                introspectedTable.getFullyQualifiedTable().toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaServiceImplType());
        Interface interfaze = new Interface(type);
        interfaze.setService(true);
        interfaze.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType implementationType = new FullyQualifiedJavaType(
                introspectedTable.getDAOImplementationType());

        /**
         * 版权信息
         */
        interfaze.addFileCommentLine("/*\n"
                + " * Dian.so Inc.\n"
                + " * Copyright (c) 2016-" + Calendar.getInstance().get(Calendar.YEAR) + " All Rights Reserved.\n"
                + " */");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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


        // 继承于BaseServiceImpl
        if (context.getJavaClientGeneratorConfiguration().getNonNeedMethod()) {
            StringBuilder sb = new StringBuilder();
            sb.append("/**\n");
            sb.append(" * 继承于BaseServiceImpl，默认有五个方法实现\n");
            sb.append(" * listRecord、getRecord、saveRecord、removeRecord、updateRecord\n");
            sb.append(" *\n");
            sb.append(" * @author MBG工具生成\n");
            sb.append(" * @version $Id: ").append(type.getShortName())
                    .append(".java, v 0.1 ")
                    .append(format.format(new Date()))
                    .append(" Exp $\n");
            sb.append(" */");
            interfaze.addJavaDocLine(sb.toString());

            interfaze.addImportedType(new FullyQualifiedJavaType("so.dian.mofa3.template.service.BaseServiceImpl"));
            //interfaze.addImportedType(new FullyQualifiedJavaType("so.dian.mofa3.template.model.BaseModel"));

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

            interfaze.addImportedType(new FullyQualifiedJavaType(type.getFullyQualifiedName()+"AAAAAAA"));

            interfaze.setAutowiredLines(Arrays.asList("private "+ type.getShortName()+"DAO dao;"));

            addSelectByExampleServiceImplMethodGenerator(interfaze);
            addGetRecordServiceImplMethodGenerator(interfaze);
            addSaveRecordServiceImplMethodGenerator(interfaze);
            addRemoveRecordServiceImplMethodGenerator(interfaze);
            addUpdateRecordServiceImplMethodGenerator(interfaze);
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

    protected void addRemoveRecordServiceImplMethodGenerator(Interface interfaze) {
        if (introspectedTable.getRules().generateDeleteByExample()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new RemoveRecordServiceImplMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addSaveRecordServiceImplMethodGenerator(Interface interfaze) {
        if (introspectedTable.getRules().generateInsert()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new SaveRecordServiceImplMethodGenerator(false);
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addSelectByExampleServiceImplMethodGenerator(Interface interfaze) {
        if (introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleServiceImplMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addGetRecordServiceImplMethodGenerator(Interface interfaze) {
        if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new GetRecordServiceImplMethodGenerator(false);
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addUpdateRecordServiceImplMethodGenerator(Interface interfaze) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new UpdateRecordServiceImplMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    protected void addExtendsBaseServiceGenerator(Interface interfaze) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new ExtendsBaseServiceImplGenerator();
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