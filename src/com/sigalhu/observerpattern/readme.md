#### 定义

观察者模式定义对象间一种一对多的依赖关系，使得每当一个对象改变状态，则所有依赖于它的对象都会得到通知并被自动更新。

#### 类图

![](pic\1.png)

**`Subject` 被观察者**

被观察者必须能够动态地增加、取消观察者，一般是抽象类或实现类，仅仅完成作为被观察者必须实现的职责：管理观察者并通知观察者。

**`Observer` 观察者**

观察者接收到消息后，即进行update操作，对接收到的信息进行处理。

**`ConcreteSubject` 具体的被观察者**

具体的被观察者定义被观察者的业务逻辑，同时定义对哪些事件进行通知。

**`ConcreteObserver` 具体的观察者**

具体的观察者在接收到消息后进行相应的处理。

#### 实现

被观察者：
```java
package com.sigalhu.observerpattern.impl;

import java.util.Vector;

public abstract class Subject {
    //定义一个观察者数组
    private Vector<Observer> obsVector = new Vector<>();

    //增加一个观察者
    public void addObserver(Observer o){
        this.obsVector.add(o);
    }

    //删除一个观察者
    public void delObserver(Observer o){
        this.obsVector.remove(o);
    }

    //通知所有观察者
    public void notifyObservers(){
        for(Observer o:this.obsVector){
            o.update();
        }
    }
}
```
具体的被观察者：
```java
package com.sigalhu.observerpattern.impl;

public class ConcreteSubject extends Subject {
    //具体的业务
    public void doSomething(){
        //do something
        super.notifyObservers();
    }
}
```
观察者：
```java
package com.sigalhu.observerpattern.impl;

public interface Observer {
    //更新方法
    public void update();
}
```
具体的观察者：
```java
package com.sigalhu.observerpattern.impl;

public class ConcreteObserver implements Observer {
    //实现更新方法
    @Override
    public void update() {
        System.out.println("接收到消息，并进行处理！");
    }
}
```
场景类：
```java
package com.sigalhu.observerpattern.impl;

public class Client {
    public static void main(String[] args) {
        //创建一个被观察者
        ConcreteSubject subject = new ConcreteSubject();
        //定义一个观察者
        Observer obs = new ConcreteObserver();
        //观察者观察被观察者
        subject.addObserver(obs);
        //观察者开始活动了
        subject.doSomething();
    }
}
```

#### 优点

* 观察者与被观察者之间是抽象耦合，不管是增加观察者还是被观察者都非常容易扩展；
* 建立了一套触发机制。

#### 缺点

开发和调试比较复杂，而且在`Java`中消息的通知默认是顺序执行，一个观察者卡壳，会影响整体的执行效率，因此一般采用异步方式处理。

#### 建议

在一个观察者模式中最多出现一个对象既是观察者也是被观察者，也就是说消息最多转发一次（传递两次）。