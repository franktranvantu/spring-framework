package com.frank.springframework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args ) {

//        IOutputGenerator outputGenerator = new CsvOutputGenerator();
//        outputGenerator.generateOutput();

//        OutputHelper helper = new OutputHelper();
//        helper.generateOutput();

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        OutputHelper outputHelper = context.getBean("outputHelper", OutputHelper.class);
        outputHelper.generateOutput();
    }
}
