package com.fastcampus.ch3.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain2 {
    public static void main(String[] args) {
        ApplicationContext ac = new GenericXmlApplicationContext("file:ch3/src/main/webapp/WEB-INF/spring/**/root-context_aop.xml");
        MyMath mm = (MyMath) ac.getBean("myMath");
        mm.add(3, 5);
        mm.add(1, 2, 3);
        mm.multiply(3, 5);

    }
}
