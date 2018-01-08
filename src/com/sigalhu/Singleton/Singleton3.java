package com.sigalhu.Singleton;

public class Singleton3 {
    private static Singleton3 singleton = null;

    private Singleton3(){}

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
