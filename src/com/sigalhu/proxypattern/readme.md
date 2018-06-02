#### 定义

代理模式为其他对象提供一种代理以控制对这个对象的访问。

#### 类图

![](pic/1.png)

**`Subject` 抽象主题角色**

`Subject`抽象主题角色可以是抽象类也可以是接口，是一个最普通的业务类型定义，无特殊要求。

**`RealSubject` 具体主题角色**

`RealSubject`具体主题角色是业务逻辑的具体执行者。

**`Proxy` 代理主题角色**

`Proxy`代理主题角色负责对真实角色的应用，把所有抽象主题类定义的方法限制委托给真实主题角色实现，并且在真实主题角色处理完毕前后做预处理和善后处理工作。

#### 实现

抽象主题类：
```java
package com.sigalhu.proxypattern.impl;

public interface Subject {
    //定义一个方法
    public void request();
}
```
真实主题类：
```java
package com.sigalhu.proxypattern.impl;

public class RealSubject implements Subject {
    //实现方法
    @Override
    public void request() {
        //业务逻辑处理
    }
}
```
代理类：
```java
package com.sigalhu.proxypattern.impl;

public class Proxy implements Subject {
    //要代理哪个实现类
    private Subject subject = null;

    //默认被代理者
    public Proxy(){
        this.subject = new Proxy();
    }

    //通过构造函数传递代理者
    public Proxy(Subject subject){
        this.subject = subject;
    }

    //实现接口中定义的方法
    @Override
    public void request() {
        this.before();
        this.subject.request();
        this.after();
    }

    //预处理
    private void before(){
        //do something
    }
    //善后处理
    private void after(){
        //do something
    }
}
```

#### 优点

* 职责清晰，真实的角色就是实现实际的业务逻辑，不用关心其他非本职责的事务，通过后期的代理完成一件事务，附带的结果就是编程简洁清晰；
* 高扩展性，具体角色类的不管如何变化，只要它实现了接口，代理类就可以在不做任何修改的情况下使用；
* 智能化，参考`AOP`。

#### 动态代理

动态代理是在实现阶段不用关心代理谁，而在运行阶段才指定代理哪一个对象。

![](pic/2.png)

动态代理实现代理的职责，业务逻辑`Subject`实现相关的逻辑功能，两者之间没有必然的相互耦合的关系。通知`Advice`从另一个切面切入，最终在高层模块也就是`Client`进行耦合，完成逻辑的封装任务。

抽象主题：
```java
package com.sigalhu.proxypattern.impl.dynamicproxy;

public interface Subject {
    //业务操作
    public void doSomething(String str);
}
```
真实主题：
```java
package com.sigalhu.proxypattern.impl.dynamicproxy;

public class RealSubject implements Subject {
    //业务操作
    @Override
    public void doSomething(String str) {
        System.out.println("do something!-->" + str);
    }
}
```
动态代理的`Handler`类：
```java
package com.sigalhu.proxypattern.impl.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    //被代理的对象
    private Object target = null;

    //通过构造函数传递一个对象
    public MyInvocationHandler(Object object){
        this.target = object;
    }

    //代理方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable{
        //执行被代理的方法
        return method.invoke(this.target, args);
    }
}
```
动态代理类：
```java
package com.sigalhu.proxypattern.impl.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxy<T> {
    public static <T> T newProxyInstance(ClassLoader loader,
                                         Class<?>[] interfaces,
                                         InvocationHandler h){
        //寻找JoinPoint连接点，AOP框架使用元数据定义
        if(true){
            //执行一个前置通知
            (new BeforeAdvice()).exec();
        }
        //执行目标，并返回结果
        return (T) Proxy.newProxyInstance(loader,interfaces,h);
    }
}
```
通知接口及实现：
```java
package com.sigalhu.proxypattern.impl.dynamicproxy;

public interface IAdvice {
    //通知只有一个方法，执行即可
    public void exec();
}
```
```java
package com.sigalhu.proxypattern.impl.dynamicproxy;

public class BeforeAdvice implements IAdvice {
    @Override
    public void exec() {
        System.out.println("前置通知！");
    }
}
```
场景类：
```java
package com.sigalhu.proxypattern.impl.dynamicproxy;

import java.lang.reflect.InvocationHandler;

public class Client {
    public static void main(String[] args){
        //定义一个主题
        Subject subject = new RealSubject();
        //定义一个Handler
        InvocationHandler handler = new MyInvocationHandler(subject);
        //定义主题的代理
        Subject proxy = DynamicProxy.newProxyInstance(
                subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),
                handler);
        //代理的行为
        proxy.doSomething("Finish");
    }
}
```