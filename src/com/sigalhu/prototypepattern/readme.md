#### 定义

原型模式是指用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象，简而言之，就是不通过`new`关键字来产生一个对象，而是通过对象复制来实现。

#### 类图

![](pic/1.png)

#### 实现

实现一个接口，然后重写`clone`方法，就完成了原型模式：

```java
package com.sigalhu.prototypepattern.impl;

public class PrototypeClass implements Cloneable{
    //覆写父类Object方法
    @Override
    protected PrototypeClass clone(){
        PrototypeClass prototypeClass = null;
        try{
            prototypeClass = (PrototypeClass)super.clone();
        } catch (CloneNotSupportedException e){
            //异常处理
        }
        return prototypeClass;
    }
}
```

#### 优点

* 性能优良。原型模式是在内存二进制流的拷贝，要比直接`new`一个对象性能好很多；
* 逃避构造函数的约束。由于直接在内存中拷贝，因此不会执行构造函数。

#### 注意

* 对象拷贝时构造函数不会执行；
* `Object`类提供的`clone`方法只是拷贝本对象，其对象内部的数组、引用对象等都不拷贝，还是指向原生对象的内部元素地址；
* 要使用`clone`方法，类的成员变量上不要增加`final`关键字。
