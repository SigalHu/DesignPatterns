package com.sigalhu.proxypattern.impl.dynamicproxy;

public class RealSubject implements Subject {
    //业务操作
    @Override
    public void doSomething(String str) {
        System.out.println("do something!-->" + str);
    }
}
