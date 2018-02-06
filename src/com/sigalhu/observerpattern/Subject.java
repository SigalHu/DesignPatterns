package com.sigalhu.observerpattern;

import java.util.Vector;

public abstract class Subject {
    //定义一个观察者数组
    private Vector<Observer> obsVector = new Vector<>();

    //增加一个观察者
    public void addObserver(Observer o){
        this.obsVector.add(o);
    }

    //删除一个观察者
    public void delObserver(Observer o){
        this.obsVector.remove(o);
    }

    //通知所有观察者
    public void notifyObservers(){
        for(Observer o:this.obsVector){
            o.update();
        }
    }
}
