package com.sigalhu.adapterpattern.impl;

public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("if you need any help, pls call me!");
    }
}
