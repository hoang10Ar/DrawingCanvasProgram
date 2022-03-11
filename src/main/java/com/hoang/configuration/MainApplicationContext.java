package com.hoang.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplicationContext {
    private static ApplicationContext mainAppContext;

    public static ApplicationContext getApplicationContext() {
        if(mainAppContext == null) {
            mainAppContext = new AnnotationConfigApplicationContext(DrawingProgramConfig.class);
        }
        return mainAppContext;
    }
}
