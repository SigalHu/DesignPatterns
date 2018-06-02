package com.sigalhu.responsibilitychain.impl;

public class ConcreteHandler3 extends Handler {
    //定义自己的处理逻辑
    @Override
    protected Response echo(Request request) {
        return null;
    }

    //设置自己的处理级别
    @Override
    protected Level getHandlerLevel() {
        return null;
    }
}
