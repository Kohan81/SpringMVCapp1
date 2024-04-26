package com.kohan81.springMVC.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//этот файл заменяет web.xml
public class MySpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class};//теперь этот класс знает где находится Спринг конфигурация
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};//т.о. все запросы пользователя отправляем на наш Диспетчер-сервлет
    }
    //автор урока Neil Alishev предлогает установить зависимость javax.servlet-api
    //эта зависимость нужна этим абстрактным классом AbstractAnnotationConfigDispatcherServletInitializer
}
