#### 定义

使用共享对象可有效地支持大量的细粒度的对象，我们可以将这些对象的信息分为两个部分：内部状态与外部状态。

* 内部状态是对象可共享出来的信息，存储在享元对象内部并且不会随环境改变而改变，它可以作为一个对象的动态附加信息，不必直接储存在具体某个对象中，属于可以共享的部分；
* 外部状态是对象得以依赖的一个标记，是随环境改变而改变的、不可以共享的状态，它是一批对象的统一标识，是唯一的一个索引值。

#### 类图

![](pic/1.png)

**`Flyweight` 抽象享元角色**

简单地说就是一个产品的抽象类，同时定义出对象的外部状态和内部状态的接口或实现。

**`ConcreteFlyweight` 具体享元角色**

具体的一个产品类，实现抽象角色定义的业务。需要注意的是，内部状态处理应该与环境无关，不应该出现一个操作改变了内部状态，同时修改了外部状态，这是绝对不允许的。

**`UnsharedConcreteFlyweight` 不可共享的享元角色**

不存在外部状态或者安全要求（如线程安全）不能够使用共享技术的对象，该对象一般不会出现在享元工厂中。

**`FlyweightFactory` 享元工厂**

构造一个池容器，同时提供从池中获得对象的方法。

#### 实现

抽象享元角色：
```java
package com.sigalhu.flyweightpattern.impl;

public abstract class Flyweight {
    //内部状态
    private String intrinsic;
    //外部状态
    protected final String extrinsic;

    //要求享元角色必须接受外部状态
    public Flyweight(String extrinsic){
        this.extrinsic = extrinsic;
    }

    //定义业务操作
    public abstract void operate();

    //内部状态的getter/setter
    public String getIntrinsic() {
        return intrinsic;
    }
    public void setIntrinsic(String intrinsic) {
        this.intrinsic = intrinsic;
    }
}
```
具体享元角色：
```java
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
```
```java
package com.sigalhu.flyweightpattern.impl;

public class ConcreteFlyweight2 extends Flyweight {
    //接受外部状态
    public ConcreteFlyweight2(String extrinsic){
        super(extrinsic);
    }

    //根据外部状态进行逻辑处理
    @Override
    public void operate() {
        //业务逻辑
    }
}
```
享元工厂：
```java
package com.sigalhu.flyweightpattern.impl;

import java.util.HashMap;

public class FlyweightFactory {
    //定义一个池容器
    private static HashMap<String,Flyweight> pool = new HashMap<>();

    //享元工厂
    public static Flyweight getFlyweight(String extrinsic){
        //需要返回的对象
        Flyweight flyweight = null;
        //在池中没有该对象
        if(pool.containsKey(extrinsic)){
            flyweight = pool.get(extrinsic);
        } else {
            //根据外部状态创建享元对象
            flyweight = new ConcreteFlyweight1(extrinsic);
            //放置到池中
            pool.put(extrinsic, flyweight);
        }
        return flyweight;
    }
}
```

#### 优缺点

享元模式是一个非常简单的模式，它可以大大减少应用程序创建的对象，降低程序内存的占用，增强程序的性能，但它同时也提高了系统复杂性，需要分离出外部状态和内部状态，而且外部状态具有固化特性，不应该随内部状态改变而改变，否则导致系统的逻辑混乱。