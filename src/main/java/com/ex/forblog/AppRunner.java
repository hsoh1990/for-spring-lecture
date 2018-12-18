package com.ex.forblog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner{

    @Value("#{1 + 1}")
    int value;

    @Value("#{'hello' + 'world'}")
    String greeting;

    @Value("#{1 eq 1}")
    boolean trueOrFalse;

    @Value("hello")
    String hello;

    @Value("${app.about}")
    String appAbout;

    @Value("#{${my.value} eq 100}")
    boolean myValueIs100;

    @Value("#{sample.data}")
    int sampleData;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("==============");
        System.out.println(value);
        System.out.println(greeting);
        System.out.println(trueOrFalse);
        System.out.println(hello);
        System.out.println(appAbout);
        System.out.println(myValueIs100);
        System.out.println(sampleData);

        ExpressionParser parser = new SpelExpressionParser();
        Expression exception = parser.parseExpression("2 + 100");
        Integer val = exception.getValue(Integer.class);
        System.out.println(val);

    }
}
