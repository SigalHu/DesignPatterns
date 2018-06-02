#### 定义

定义一组算法，将每个算法都封装起来，并且使它们之间可以互换。

#### 类图

![](pic/1.png)

**`Context` 封装角色**

也叫上下文角色，起承上启下封装作用，屏蔽高层模块对策略、算法的直接访问，封装可能存在的变化。

**`Strategy` 抽象策略角色**

策略、算法家族的抽象，通常为接口，定义每个策略或算法必须具有的方法和属性。

**`ConcreteStrategy` 具体策略角色**

实现抽象策略中的操作，包含具体的算法。

#### 实现

抽象的策略角色：
```java
package com.sigalhu.strategypattern.impl;

public interface Strategy {
    //策略模式的运算法则
    public void doSomething();
}
```
具体的策略角色：
```java
package com.sigalhu.strategypattern.impl;

public class ConcreteStrategy1 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("具体策略1的运算法则");
    }
}
```
```java
package com.sigalhu.strategypattern.impl;

public class ConcreteStrategy2 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("具体策略2的运算法则");
    }
}
```
封装角色：
```java
package com.sigalhu.strategypattern.impl;

public class Context {
    //抽象策略
    private Strategy strategy = null;

    //构造函数设置具体策略
    public Context(Strategy _strategy){
        this.strategy = _strategy;
    }

    //封装后的策略方法
    public void doAnything(){
        this.strategy.doSomething();
    }
}
```
高层模块：
```java
package com.sigalhu.strategypattern.impl;

public class Client {
    public static void main(String[] args){
        //声明一个具体的策略
        Strategy strategy = new ConcreteStrategy1();
        //声明上下文对象
        Context context = new Context(strategy);
        //执行封装后的方法
        context.doAnything();
    }
}
```

#### 优点

* 算法可以自由切换。只要实现抽象策略，它就成为策略家族的一员，通过封装角色对其进行封装，保证对外提供可自由切换的策略；
* 避免使用多重条件判断。使用策略模式后，可以由其他模块决定采用何种策略，策略家族对外提供的访问接口就是封装类，简化了操作，同时避免了条件语句判断；
* 扩展性良好。在现有的系统中增加一个策略，只要实现接口就可以，其他都不用修改，类似于一个可反复拆卸的插件。

#### 缺点

* 策略类数量增多。每一个策略都是一个类，复用的可能性很小，类数量增多；
* 所有的策略类都需要对外暴露。上层模块必须知道有哪些策略，然后才能决定使用哪一个策略，与迪米特法则违背，但可以使用其他模式来修正这个缺陷，如工厂方法模式、代理模式或享元模式。

#### 使用场景

* 多个类只有在算法或者行为上稍有不同的场景；
* 算法需要自由切换的场景；
* 需要屏蔽算法规则的场景。

#### 注意

如果系统中的一个策略家族的具体策略数量超过4个，则需要考虑使用混合模式，解决策略类膨胀和对外暴露的问题，否则会对系统维护造成困难。