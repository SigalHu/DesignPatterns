package com.sigalhu.builderpattern;

public class ConcreteBuilder extends Builder {
    private Product product = new Product();

    //设置产品零件
    @Override
    public void setPart() {
        //产品类内的逻辑处理
    }

    //组建一个产品
    @Override
    public Product buildProduct() {
        return product;
    }
}
