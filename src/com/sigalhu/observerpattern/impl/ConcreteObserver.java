package com.sigalhu.observerpattern.impl;

public class ConcreteObserver implements Observer {
    //实现更新方法
    @Override
    public void update() {
        System.out.println("接收到消息，并进行处理！");
    }
}
