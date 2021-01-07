package com.yamamoto.yuta.david.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;

import java.util.List;
import java.util.Optional;

public class ReturnOptionalPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientSelectByPrimaryKeyMethodGenerated(
            Method method,
            Interface interfaze,
            IntrospectedTable introspectedTable
    ) {

        Optional<FullyQualifiedJavaType> returnType = method.getReturnType();

        returnType.ifPresent(t -> {

            method.setReturnType(
                    new FullyQualifiedJavaType(
                            "java.util.Optional<"
                                    + t.getFullyQualifiedName()
                                    + ">"));
        });

        return true;
    }
}
