package com.sigalhu.adapterpattern.impl;

public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.doSomething();
    }
}
