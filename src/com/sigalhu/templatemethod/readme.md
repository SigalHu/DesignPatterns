#### 定义

模板方法模式要求定义一个操作中的算法框架，而将一些步骤延迟到子类中，使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。通常，算法框架可使用抽象类定义，其中，可以有一个或几个实现对基本方法进行调度的具体方法，也就是一个框架，叫做模板方法；而由子类实现，并被模板方法调用的方法叫做基本方法。

#### 类图

![](pic/1.png)

#### 实现

抽象模板类：
```java
package com.sigalhu.templatemethod.impl;

public abstract class AbstractClass {
    //基本方法
    protected abstract void doSomething();

    //基本方法
    protected abstract void doAnything();

    //模板方法
    public void templateMethod(){
        //调度基本方法，完成相关逻辑
        this.doAnything();
        this.doSomething();
    }
}
```
具体模板类：
```java
package com.sigalhu.templatemethod.impl;

public class ConcreteClass1 extends AbstractClass{
    //实现基本方法
    @Override
    protected void doAnything() {
        //业务逻辑处理
    }

    @Override
    protected void doSomething() {
        //业务逻辑处理
    }
}
```
```java
package com.sigalhu.templatemethod.impl;

public class ConcreteClass2 extends AbstractClass{
    //实现基本方法
    @Override
    protected void doAnything() {
        //业务逻辑处理
    }

    @Override
    protected void doSomething() {
        //业务逻辑处理
    }
}
```
场景类：
```java
package com.sigalhu.templatemethod.impl;

public class Client {
    public static void main(String[] args){
        AbstractClass class1 = new ConcreteClass1();
        AbstractClass class2 = new ConcreteClass2();
        //调用模板方法
        class1.templateMethod();
        class2.templateMethod();
    }
}
```

#### 优点

* 封装不变部分，扩展可变部分。把认为是不变部分的算法封装到父类实现，而可变部分的则可以通过继承来继续扩展；
* 提取公共部分，便于维护；
* 行为由父类控制，子类实现，因此子类可以通过扩展的方式增加相应的功能，符合开闭原则。

#### 缺点

在模板方法模式中，抽象类定义了部分抽象方法，由子类实现，子类执行的结构影响了父类的结果，也就是子类对父类产生了影响，这在复杂的项目中，会带来代码阅读的难度。
