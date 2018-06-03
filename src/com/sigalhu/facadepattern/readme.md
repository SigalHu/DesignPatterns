#### 定义

门面模式要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行，门面模式提供一个高层次的接口，使得子系统更易于使用。

#### 类图

![](pic\1.png)

**`Facade` 门面角色**

客户端可以调用这个角色的方法，此角色知晓子系统的所有功能和责任，一般情况下，本角色会将所有从客户端发来的请求委派到相应的子系统去，也就是说该角色没有实际的业务逻辑，只是一个委托类。

**`Subsystem` 子系统角色**

可以同时有一个或者多个子系统，每个子系统都是一个类的集合，且其并不知道门面的存在，对于子系统而言，门面仅仅是另外一个客户端而已。

#### 实现

子系统：
```java
package com.sigalhu.facadepattern.impl;

public class ClassA {
    public void doSomethingA(){
        //业务逻辑
    }
}
```
```java
package com.sigalhu.facadepattern.impl;

public class ClassB {
    public void doSomethingB(){
        //业务逻辑
    }
}
```
```java
package com.sigalhu.facadepattern.impl;

public class ClassC {
    public void doSomethingC(){
        //业务逻辑
    }
}
```
门面对象：
```java
package com.sigalhu.facadepattern.impl;

public class Facade {
    //被委托的对象
    private ClassA a = new ClassA();
    private ClassB b = new ClassB();
    private ClassC c = new ClassC();

    //提供给外部访问的方法
    public void methodA(){
        this.a.doSomethingA();
    }
    public void methodB(){
        this.b.doSomethingB();
    }
    public void methodC(){
        this.c.doSomethingC();
    }
}
```

#### 优点

* 减少系统的相互依赖，如果不使用门面模式，外界访问直接深入到子系统内部，相互之间是一种强耦合的关系；
* 提高了灵活性，子系统内部的改变只要不影响到门面对象，就不会对客户端代码造成影响；
* 提高了安全性，可以通过门面对象来控制对子系统的访问。

#### 缺点

不符合开闭原则，对修改关闭，对扩展开放。

#### 注意

门面对象只是提供一个访问子系统的路径而已，它不应该也不能参与具体的业务逻辑，否则就会产生一个倒依赖的问题：子系统必须依赖门面才能被访问。