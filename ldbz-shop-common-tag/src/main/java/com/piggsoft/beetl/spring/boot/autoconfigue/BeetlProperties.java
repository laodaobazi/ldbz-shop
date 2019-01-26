/*
 *
 * Copyright (C) 1999-2016 IFLYTEK Inc.All Rights Reserved.
 * History：
 * Version   Author      Date                              Operation
 * 1.0       yaochen4    2016/11/30                           Create
 */
package com.piggsoft.beetl.spring.boot.autoconfigue;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author yaochen4
 * @version 1.0
 * @since 1.0
 */
@ConfigurationProperties(prefix = BeetlProperties.BEETL_PREFIX)
public class BeetlProperties {

    public static final String BEETL_PREFIX = "beetl";

    private String templatesPath;

    private Config config;

    public String getTemplatesPath() {
        return templatesPath;
    }

    public void setTemplatesPath(String templatesPath) {
        this.templatesPath = templatesPath;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public static class Config {

        private String delimiter_placeholder_start;
        private String delimiter_placeholder_end;
        private String delimiter_statement_start;
        private String delimiter_statement_end;
        private String native_call;
        private String ignore_client_io_error;
        private String direct_byte_output;
        private String template_root;
        private String template_charset;
        private String error_handler;
        private String mvc_strict;
        private String webapp_ext;
        private String html_tag_support;
        private String html_tag_flag;
        private String import_package;
        private String engine;
        private String native_secuarty_manager;
        private String resource_loader;
        private String html_tag_binding_attribute;
        private String function_tag_limiter;

        public Properties toProperties() {
            final Properties properties = new Properties();
            final Object o = this;
            ReflectionUtils.doWithMethods(this.getClass(), new ReflectionUtils.MethodCallback() {
                public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                    Object value = ReflectionUtils.invokeMethod(method, o);
                    if (value != null) {
                        properties.put(method.getName().replaceFirst("get", "").toUpperCase(), value);
                    }
                }
            }, new ReflectionUtils.MethodFilter() {
                public boolean matches(Method method) {
                    return method.getName().startsWith("get") && !method.getName().equals("getClass");
                }
            });
            return properties;
        }

        public String getDelimiter_placeholder_start() {
            return delimiter_placeholder_start;
        }

        public void setDelimiter_placeholder_start(String delimiter_placeholder_start) {
            this.delimiter_placeholder_start = delimiter_placeholder_start;
        }

        public String getDelimiter_placeholder_end() {
            return delimiter_placeholder_end;
        }

        public void setDelimiter_placeholder_end(String delimiter_placeholder_end) {
            this.delimiter_placeholder_end = delimiter_placeholder_end;
        }

        public String getDelimiter_statement_start() {
            return delimiter_statement_start;
        }

        public void setDelimiter_statement_start(String delimiter_statement_start) {
            this.delimiter_statement_start = delimiter_statement_start;
        }

        public String getDelimiter_statement_end() {
            return delimiter_statement_end;
        }

        public void setDelimiter_statement_end(String delimiter_statement_end) {
            this.delimiter_statement_end = delimiter_statement_end;
        }

        public String getNative_call() {
            return native_call;
        }

        public void setNative_call(String native_call) {
            this.native_call = native_call;
        }

        public String getIgnore_client_io_error() {
            return ignore_client_io_error;
        }

        public void setIgnore_client_io_error(String ignore_client_io_error) {
            this.ignore_client_io_error = ignore_client_io_error;
        }

        public String getDirect_byte_output() {
            return direct_byte_output;
        }

        public void setDirect_byte_output(String direct_byte_output) {
            this.direct_byte_output = direct_byte_output;
        }

        public String getTemplate_root() {
            return template_root;
        }

        public void setTemplate_root(String template_root) {
            this.template_root = template_root;
        }

        public String getTemplate_charset() {
            return template_charset;
        }

        public void setTemplate_charset(String template_charset) {
            this.template_charset = template_charset;
        }

        public String getError_handler() {
            return error_handler;
        }

        public void setError_handler(String error_handler) {
            this.error_handler = error_handler;
        }

        public String getMvc_strict() {
            return mvc_strict;
        }

        public void setMvc_strict(String mvc_strict) {
            this.mvc_strict = mvc_strict;
        }

        public String getWebapp_ext() {
            return webapp_ext;
        }

        public void setWebapp_ext(String webapp_ext) {
            this.webapp_ext = webapp_ext;
        }

        public String getHtml_tag_support() {
            return "true";
        }

        public void setHtml_tag_support(String html_tag_support) {
            this.html_tag_support = "true";
        }

        public String getHtml_tag_flag() {
            return "ldbz:";
        }

        public void setHtml_tag_flag(String html_tag_flag) {
            this.html_tag_flag = "ldbz:";
        }

        public String getImport_package() {
            return import_package;
        }

        public void setImport_package(String import_package) {
            this.import_package = import_package;
        }

        public String getEngine() {
            return engine;
        }

        public void setEngine(String engine) {
            this.engine = engine;
        }

        public String getNative_secuarty_manager() {
            return native_secuarty_manager;
        }

        public void setNative_secuarty_manager(String native_secuarty_manager) {
            this.native_secuarty_manager = native_secuarty_manager;
        }

        public String getResource_loader() {
            return resource_loader;
        }

        public void setResource_loader(String resource_loader) {
            this.resource_loader = resource_loader;
        }

        public String getHtml_tag_binding_attribute() {
            return html_tag_binding_attribute;
        }

        public void setHtml_tag_binding_attribute(String html_tag_binding_attribute) {
            this.html_tag_binding_attribute = html_tag_binding_attribute;
        }

        public String getFunction_tag_limiter() {
            return function_tag_limiter;
        }

        public void setFunction_tag_limiter(String function_tag_limiter) {
            this.function_tag_limiter = function_tag_limiter;
        }
    }
}
