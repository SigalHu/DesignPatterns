#### 定义

将抽象和实现解耦，使得两者可以独立地变化。

#### 类图

![](pic/1.png)

**`Abstraction` 抽象化角色**

定义出该角色的行为，同时保存为一个对实现化角色的引用，一般是抽象类。

**`Implementor` 实现化角色**

定义角色必需的行为和属性，一般是接口或抽象类。

**`RefinedAbstraction` 修正抽象化角色**

引用实现化角色对抽象化角色进行修正。

**`ConcreteImplementor` 具体实现化角色**

实现接口或抽象类定义的方法和属性。

#### 实现

实现化角色：
```java
package com.sigalhu.bridgepattern.impl;

public interface Implementor {
    //基本方法
    public void doSomething();
    public void doAnything();
}
```
具体实现化角色：
```java
package com.sigalhu.bridgepattern.impl;

public class ConcreteImplementor1 implements Implementor {
    @Override
    public void doSomething() {
        //业务逻辑处理
    }

    @Override
    public void doAnything() {
        //业务逻辑处理
    }
}
```
```java
package com.sigalhu.bridgepattern.impl;

public class ConcreteImplementor2 implements Implementor {
    @Override
    public void doSomething() {
        //业务逻辑处理
    }

    @Override
    public void doAnything() {
        //业务逻辑处理
    }
}
```
抽象化角色：
```java
package com.sigalhu.bridgepattern.impl;

public abstract class Abstraction {
    //定义对实现化角色的引用
    private Implementor imp;

    //约束子类必须实现该构造函数
    public Abstraction(Implementor imp){
        this.imp = imp;
    }

    //自身的行为和属性
    public void request(){
        this.imp.doSomething();
    }

    //获得实现化角色
    public Implementor getImp() {
        return imp;
    }
}
```
具体抽象化角色：
```java
package com.sigalhu.bridgepattern.impl;

public class RefinedAbstraction extends Abstraction {
    //覆写构造函数
    public RefinedAbstraction(Implementor imp){
        super(imp);
    }

    //修正父类的行为
    @Override
    public void request() {
        //业务处理
        super.request();
        super.getImp().doAnything();
    }
}
```
场景类：
```java
package com.sigalhu.bridgepattern.impl;

public class Client {
    public static void main(String[] args){
        //定义一个实现化角色
        Implementor imp = new ConcreteImplementor1();
        //定义一个抽象化角色
        Abstraction abs = new RefinedAbstraction(imp);
        //执行行文
        abs.request();
    }
}
```

#### 优点

* 抽象与实现分离，实现可以不受抽象的约束，不用再绑定在一个固定的抽象层次上；
* 优秀的扩充能力，只要对外暴露的接口允许增加实现或抽象，其变化的可能性已经被减到最小；
* 实现细节对客户透明，客户不用关心细节的实现，它已经由抽象层通过聚合关系完成了封装。

#### 注意

使用桥梁模式时主要考虑如何拆分抽象和实现，并不是一涉及到继承就要考虑使用该模式。桥梁模式的意图是对变化的封装，尽量把可能变化的因素封装到最细、最小的逻辑单元中，避免风险扩散。