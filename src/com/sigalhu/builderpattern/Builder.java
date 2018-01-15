package com.sigalhu.builderpattern;

public abstract class Builder {
    //设置产品的不同部分，已获得不同产品
    public abstract void setPart();

    //建造产品
    public abstract Product buildProduct();
}
