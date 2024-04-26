package com.kohan81.springMVC.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan("com.kohan81.springMVC")//указываем где лежат наши компоненты, где лежит наш контроллер
@EnableWebMvc //эта аннотация == <mvc:annotation-driven/> из файла: applicationContextMVC.xml

//теперь необходимо реализовать бины, отвечающие за конфигурацию шаблонизатора thymeleaf
public class SpringConfig implements WebMvcConfigurer {
//этот интерфейс используем когда хотим под себя настроить SpringMVC
// и в данном случае настраиваем шаблонизатор Thymeleaf
// по этому реализуем этот интерфейс и его м-д "configureViewResolvers"

    private final ApplicationContext applicationContext;
//также с помощью Спринга, и аннотации Autowired внедряем ApplicationContext
// ApplicationContext испульзуем в бине templateResolver() чтобы настроить Thymeleaf
    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");//указываем папку для представлений
        templateResolver.setSuffix(".html");//указываем расширение для представлений
        return templateResolver;
    }

    //тут также производим конфигурацию представлений
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }
//тут передаем Spring(у) информацию что используем шаблонизатор Thymeleaf
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }
}
//вся эта конфигурация эквивалентна applicationContextMVC.xml
//т.е. также используем @ComponentScan == <context:component-scan base-package="com.kohan81.springMVC"/>
//также включаем SpringMVC: @EnableWebMvc == <mvc:annotation-driven/>
// и также создаем бины которые относятся к Thymeleaf
//и теперь файл applicationContextMVC.xml больше не нужен