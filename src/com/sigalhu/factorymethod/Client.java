package com.sigalhu.factorymethod;

public class Client {
    public static void main(String[] args){
        Creator creator = new ConcreateCreator();
        Product product = creator.createProduct(ConcreteProduct1.class);
        //继续业务处理
    }
}
