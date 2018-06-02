#### 定义

中介者模式用一个中介对象封装一系列的对象交互，中介者使各对象不需要显示地相互作用，从而使其耦合松散，而且可以独立地改变它们之间的交互。

#### 类图

![](pic/1.png)

**`Mediator` 抽象中介者角色**

抽象中介者角色定义统一的接口，用于各同事角色之间的通信。

**`ConcreteMediator` 具体中介者角色**

具体中介者角色通过协调各同事角色实现协作行为，因此它必须依赖于各个同事角色。

**`Colleague` 同事角色**

每一个同事角色都知道中介者角色，而且与其他的同事角色通信的时候，一定要通过中介者角色协作。

每个同事类的行为分为两种：一种是同事本身的行为，与其他同事类或中介者没有任何的依赖，叫做自发行为；第二种是必须依赖中介者才能完成的行为，叫做依赖方法。

#### 实现

通用抽象中介者：
```java
package com.sigalhu.mediatorpattern.impl;

public abstract class Mediator {
    //定义同事类
    protected ConcreteColleague1 c1;
    protected ConcreteColleague2 c2;

    //通过getter/setter方法把同事类注入进来
    public ConcreteColleague1 getC1() {
        return c1;
    }
    public void setC1(ConcreteColleague1 c1) {
        this.c1 = c1;
    }
    public ConcreteColleague2 getC2() {
        return c2;
    }
    public void setC2(ConcreteColleague2 c2) {
        this.c2 = c2;
    }

    //中介者模式的业务逻辑
    public abstract void doSometing1();
    public abstract void doSometing2();
}
```
通用中介者：
```java
package com.sigalhu.mediatorpattern.impl;

public class ConcreteMediator extends Mediator {
    @Override
    public void doSometing1() {
        //调用同事类的方法，只要是public方法都可以调用
        super.c1.selfMethod1();
        super.c2.selfMethod2();
    }

    @Override
    public void doSometing2() {
        //调用同事类的方法，只要是public方法都可以调用
        super.c1.selfMethod1();
        super.c2.selfMethod2();
    }
}
```
抽象同事类：
```java
package com.sigalhu.mediatorpattern.impl;

public abstract class Colleague {
    protected Mediator mediator;
    public Colleague(Mediator _mediator){
        this.mediator = _mediator;
    }
}
```
具体同事类：
```java
package com.sigalhu.mediatorpattern.impl;

public class ConcreteColleague1 extends Colleague {
    //通过构造函数传递中介者
    public ConcreteColleague1(Mediator _mediator){
        super(_mediator);
    }

    //自有方法self-method
    public void selfMethod1(){
        //处理自己的业务逻辑
    }

    //依赖方法dep-method
    public void depMethod1(){
        //处理自己的业务逻辑
        //自己不能处理的业务逻辑，委托给中介者处理
        super.mediator.doSometing1();
    }
}
```
```java
package com.sigalhu.mediatorpattern.impl;

public class ConcreteColleague2 extends Colleague{
    //通过构造函数传递中介者
    public ConcreteColleague2(Mediator _mediator){
        super(_mediator);
    }

    //自有方法self-method
    public void selfMethod2(){
        //处理自己的业务逻辑
    }

    //依赖方法dep-method
    public void depMethod2(){
        //处理自己的业务逻辑
        //自己不能处理的业务逻辑，委托给中介者处理
        super.mediator.doSometing2();
    }
}
```

#### 优点

减少类间的依赖，把原有的一对多的依赖变成了一对一的依赖，同事类只依赖中介者，减少了依赖，当然同时也降低了类间的耦合。

#### 缺点

中介者会膨胀得很大，且逻辑复杂，原本多个对象直接的相互依赖关系转换成中介者和同事类的依赖关系，同事类越多，中介者的逻辑就越复杂。

#### 注意

中介者模式适用于多个对象之间紧密耦合的情况，紧密耦合的标准是：在类图中出现了蜘蛛网状结构。在这种情况下一定要考虑使用中介者模式，这有利于把蜘蛛网梳理为星型结构，使原本复杂混乱的关系变得清晰简单。
