package com.sigalhu.flyweightpattern.impl;

public class ConcreteFlyweight1 extends Flyweight {
    //接受外部状态
    public ConcreteFlyweight1(String extrinsic){
        super(extrinsic);
    }

    //根据外部状态进行逻辑处理
    @Override
    public void operate() {
        //业务逻辑
    }
}
