package com.sigalhu.iteratorpattern;

public interface Aggregate {
    //增加元素
    public void add(Object object);
    //减少元素
    public void remove(Object object);
    //由迭代器来遍历所有元素
    public Iterator iterator();
}
