#### 定义

适配器模式将一个类的接口变换成客户端所期待的另一种接口，从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作，通常用来解决接口不相容的问题。

#### 类图

![](pic\1.png)

**`Target` 目标角色**

该角色定义把其他类转换为何种接口，也就是我们期望接口。

**`Adaptee` 源角色**

它是已经存在的、运行良好的类或对象，经过适配器角色的包装，从而转换成目标角色。

**`Adapter` 适配器角色**

适配器模式的核心角色，其他两个角色都是已经存在的角色，而适配器角色是需要新建立的，它的职责非常简单：通过继承或是类关联的方式，把源角色转换为目标角色。

#### 实现

目标角色：
```java
package com.sigalhu.adapterpattern.impl;

public interface Target {
    public void request();
}
```
目标角色的实现类：
```java
package com.sigalhu.adapterpattern.impl;

public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("if you need any help, pls call me!");
    }
}
```
源角色：
```java
package com.sigalhu.adapterpattern.impl;

public class Adaptee {
    //原有的业务逻辑
    public void doSomething(){
        System.out.println("I'm kind of busy, leave me alone, pls!");
    }
}
```
适配器角色：
```java
package com.sigalhu.adapterpattern.impl;

public class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.doSomething();
    }
}
```
场景类：
```java
package com.sigalhu.adapterpattern.impl;

public class Client {
    public static void main(String[] args){
        //原有的业务逻辑
        Target target = new ConcreteTarget();
        target.request();
        //现在增加了适配器角色后的业务逻辑
        Target target1 = new Adapter();
        target1.request();
    }
}
```

#### 优点

* 可以让两个没有任何关系的类一起运行。通过把非本系统接口的对象包装成本系统可以接受的对象，降低了系统大规模变更的风险；
* 增加了类的透明度。访问的`Target`目标角色的具体实现都委托给了源角色，这些对高层次模块是透明的，也是它不需要关心的；
* 提高了类的复用度。源角色在原有系统中还是可以正常使用，而在目标角色中也可以充当新的演员；
* 灵活性好。适配器基本上类似一个灵活的构件，想用就用，不想用就卸载。

#### 注意

适配器模式不是为了解决还处在开发阶段的问题，而是解决正在服役的项目问题，该模式主要用于应用扩展。
