#### 定义

封装一些作用于某种数据结果中的各元素的操作，它可以在不改变数据结构的前提下定义作用于这些元素的新的操作。

#### 类图

![](pic/1.png)

**`Visitor` 抽象访问者**

抽象类或者接口，声明访问者可以访问哪些元素，具体到程序中就是`visit`方法的参数定义哪些对象是可以被访问的。

**`ConcreteVisitor` 具体访问者**

它影响访问者访问到一个类后该怎么干，要做什么事。

**`Element` 抽象元素**

接口或者抽象类，声明接受哪一类访问者访问，程序上是通过`accept`方法中的参数来定义的。

**`ConcreteElement` 具体元素**

实现`accept`方法。通常是`visitor.visit(this)`。

**`ObjectStruture` 结构对象**

元素生产者，一般容纳在多个不同类、不同接口的容器，如`List`、`Set`、`Map`等，在项目中，一般很少抽象出这个角色。

#### 实现

抽象元素：
```java
package com.sigalhu.visitorpattern.impl;

public abstract class Element {
    //定义业务逻辑
    public abstract void doSomething();
    //允许谁来访问
    public abstract void accept(IVisitor visitor);
}
```
具体元素：
```java
package com.sigalhu.visitorpattern.impl;

public class ConcreteElement1 extends Element {
    //完善业务逻辑
    @Override
    public void doSomething() {
        //业务处理
    }

    //允许那个访问者访问
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
```
```java
package com.sigalhu.visitorpattern.impl;

public class ConcreteElement2 extends Element {
    //完善业务逻辑
    @Override
    public void doSomething() {
        //业务处理
    }

    //允许那个访问者访问
    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
```
抽象访问者：
```java
package com.sigalhu.visitorpattern.impl;

public interface IVisitor {
    //可以访问哪些对象
    public void visit(ConcreteElement1 el1);
    public void visit(ConcreteElement2 el2);
}
```
具体访问者：
```java
package com.sigalhu.visitorpattern.impl;

public class Visitor implements IVisitor {
    //访问el1元素
    @Override
    public void visit(ConcreteElement1 el1) {
        el1.doSomething();
    }

    //访问el2元素
    @Override
    public void visit(ConcreteElement2 el2) {
        el2.doSomething();
    }
}
```
结构对象：
```java
package com.sigalhu.visitorpattern.impl;

import java.util.Random;

public class ObjectStruture {
    //对象生成器，这里通过一个工厂方法模式模拟
    public static Element createElement(){
        Random rand = new Random();
        if(rand.nextInt(100) > 50){
            return new ConcreteElement1();
        } else {
            return new ConcreteElement2();
        }
    }
}
```
场景类：
```java
package com.sigalhu.visitorpattern.impl;

public class Client {
    public static void main(String[] args){
        for(int i=0;i<10;i++){
            //获得元素对象
            Element el = ObjectStruture.createElement();
            //接受访问者访问
            el.accept(new Visitor());
        }
    }
}
```

#### 优点

* 符合单一职责原则。`Element`抽象类的两个子类负责数据的加载，而`Visitor`类则负责报表的展现，两个不同的职责非常明确地分离开来，各自演绎变化；
* 优秀的扩展性。由于职责分开，继续增加对数据的操作是非常快捷的；
* 灵活性非常高。

#### 缺点

* 具体元素对访问者公布细节；
* 具体元素变更比较困难；
* 访问者依赖的是具体元素，而不是抽象元素，违背了依赖倒置原则。