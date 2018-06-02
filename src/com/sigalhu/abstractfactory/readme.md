#### 定义

抽象工厂模式要求为创建一组相关或相互依赖的对象提供一个接口，而且无须指定它们的具体类。

#### 类图

![](pic/1.png)

#### 实现

抽象产品类：
```java
package com.sigalhu.abstractfactory.impl;

public abstract class AbstractProductA {
    //每个产品共有的方法
    public void shareMethod(){
    }

    //每个产品相同方法，不同实现
    public abstract void doSomething();
}
```
```java
package com.sigalhu.abstractfactory.impl;

public abstract class AbstractProductB {
    //每个产品共有的方法
    public void shareMethod(){
    }

    //每个产品相同方法，不同实现
    public abstract void doSomething();
}
```
具体产品类：
```java
package com.sigalhu.abstractfactory.impl;

public class ProductA1 extends AbstractProductA {
    @Override
    public void doSomething() {
        System.out.println("产品A1的实现方法");
    }
}
```
```java
package com.sigalhu.abstractfactory.impl;

public class ProductA2 extends AbstractProductA {
    @Override
    public void doSomething() {
        System.out.println("产品A2的实现方法");
    }
}
```
```java
package com.sigalhu.abstractfactory.impl;

public class ProductB1 extends AbstractProductB {
    @Override
    public void doSomething() {
        System.out.println("产品B1的实现方法");
    }
}
```
```java
package com.sigalhu.abstractfactory.impl;

public class ProductB2 extends AbstractProductB {
    @Override
    public void doSomething() {
        System.out.println("产品B2的实现方法");
    }
}
```
抽象工厂类：
```java
package com.sigalhu.abstractfactory.impl;

public abstract class AbstractCreator {
    //创建A产品家族
    public abstract AbstractProductA createProductA();

    //创建B产品家族
    public abstract AbstractProductB createProductB();
}
```
产品等级1的实现类：
```java
package com.sigalhu.abstractfactory.impl;

public class Creator1 extends AbstractCreator {
    //只生产产品等级为1的A产品
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    //只生产产品等级为1的B产品
    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
```
产品等级2的实现类：
```java
package com.sigalhu.abstractfactory.impl;

public class Creator2 extends AbstractCreator {
    //只生产产品等级为2的A产品
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    //只生产产品等级为2的B产品
    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}
```
场景类：
```java
package com.sigalhu.abstractfactory.impl;

public class Client {
    public static void main(String[] args){
        //定义出两个工厂
        AbstractCreator creator1 = new Creator1();
        AbstractCreator creator2 = new Creator2();
        //产生A1的对象
        AbstractProductA a1 = creator1.createProductA();
        a1.doSomething();
        //产生A2的对象
        AbstractProductA a2 = creator2.createProductA();
        a2.doSomething();
        //产生B1的对象
        AbstractProductB b1 = creator1.createProductB();
        b1.doSomething();
        //产生B2的对象
        AbstractProductB b2 = creator2.createProductB();
        b2.doSomething();
    }
}
```

#### 优点

* 封装性。高层模块不关心每个产品的实现类，也不关心对象是如何创建出来的，只要知道工厂类就能创建一个需要的对象；
* 产品族内的约束为非公开状态。具体的产品族内的约束是在工厂内实现的，生产过程对调用工厂类的高层模块来说是透明的，它不需要知道这个约束。

#### 缺点

产品族扩展非常困难。如果需要增加一个产品，需要修改抽象工厂类，严重违反了开闭原则，抽象类与接口是一个契约，不应该被修改。虽然产品族扩展困难，但产品等级非常容易扩展，增加一个产品等级，只要增加一个工厂类负责新增加出来的产品生产任务即可。
