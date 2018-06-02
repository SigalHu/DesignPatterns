package com.sigalhu.singleton.impl;

public class Singleton3 {
    private static Singleton3 singleton = null;

    //限制产生多个对象
    private Singleton3(){}

    //通过该方法获得实例对象
    public static Singleton3 getSingleton(){
        if(singleton == null){
            synchronized (Singleton3.class){
                if(singleton == null){
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }
}
