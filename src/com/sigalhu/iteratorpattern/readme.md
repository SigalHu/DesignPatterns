#### 定义

迭代器模式提供一种方法访问一个容器对象中各个元素，而又不需要暴露该对象的内部细节。

#### 类图

![](pic\1.png)

**`Iterator` 抽象迭代器**

抽象迭代器负责定义访问和遍历元素的接口，基本上是有固定的3个方法：`first()`获得第一个元素，`next()`访问下一个元素，`isDone()`是否已经访问到底部。

**`ConcreteIterator` 具体迭代器**

具体迭代器实现迭代器接口，完成容器元素的遍历。

**`Aggregate` 抽象容器**

抽象容器负责提供创建具体迭代器的接口。

**`ConcreteAggregate` 具体容器**

具体容器实现抽象容器定义的方法，创建出容纳迭代器的对象。

#### 实现

抽象迭代器：
```java
package com.sigalhu.iteratorpattern.impl;

public interface Iterator {
    //遍历到下一个元素
    public Object next();
    //是否已经遍历到尾部
    public boolean hasNext();
    //删除当前指向的元素
    public boolean remove();
}
```
具体迭代器：
```java
package com.sigalhu.iteratorpattern.impl;

import java.util.Vector;

public class ConcreteIterator implements Iterator {
    private Vector vector = new Vector();
    //定义当前游标
    public int cursor = 0;

    @SuppressWarnings("unchecked")
    public ConcreteIterator(Vector _vector){
        this.vector = _vector;
    }

    //判断是否到达尾部
    @Override
    public boolean hasNext() {
        if(this.cursor == this.vector.size()){
            return false;
        } else {
            return true;
        }
    }

    //返回下一个元素
    @Override
    public Object next() {
        Object result = null;
        if(this.hasNext()){
            result = this.vector.get(this.cursor++);
        }else{
            result = null;
        }
        return result;
    }

    //删除当前元素
    @Override
    public boolean remove() {
        this.vector.remove(this.cursor);
        return true;
    }
}
```
抽象容器：
```java
package com.sigalhu.iteratorpattern.impl;

public interface Aggregate {
    //增加元素
    public void add(Object object);
    //减少元素
    public void remove(Object object);
    //由迭代器来遍历所有元素
    public Iterator iterator();
}
```
具体容器：
```java
package com.sigalhu.iteratorpattern.impl;

import java.util.Vector;

public class ConcreteAggregate implements Aggregate {
    //容纳对象的容器
    private Vector vector = new Vector();

    //增加一个元素
    @Override
    public void add(Object object) {
        this.vector.add(object);
    }

    //删除一个元素
    @Override
    public void remove(Object object) {
        this.vector.remove(object);
    }

    //返回迭代器对象
    @Override
    public Iterator iterator() {
        return new ConcreteIterator(this.vector);
    }
}
```
场景类：
```java
package com.sigalhu.iteratorpattern.impl;

public class Client {
    public static void main(String[] args) {
        //声明出容器
        Aggregate agg = new ConcreteAggregate();
        //产生对象数据放进去
        agg.add("abc");
        agg.add("aaa");
        agg.add("1234");
        //遍历
        Iterator iterator = agg.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
```

#### 优点

迭代器模式提供了遍历容器的方便性，容器只要管理增减元素就可以了，需要遍历时交由迭代器进行。