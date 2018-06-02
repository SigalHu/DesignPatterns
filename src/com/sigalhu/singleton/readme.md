#### 定义

单例模式要求一个类只能生成一个对象。开发者可以通过定义一个私有访问权限的构造函数，来避免被其他类`new`出一个对象，其他类对该类的访问可以通过`getInstance`获得同一个对象。

#### 类图

![](pic/1.png)

#### 实现

单例模式根据申请对象的时机可分为饿汉式单例与懒汉式单例，其中，饿汉式单例在程序启动就申请对象；懒汉式单例在其他类获取单例时申请对象。需要注意的是，懒汉式单例还需要考虑多线程同时调用`getInstance`方法的影响。

饿汉式单例：
```java
package com.sigalhu.singleton.impl;

public class Singleton {
    private static final Singleton singleton = new Singleton();

    //限制产生多个对象
    private Singleton(){}

    //通过该方法获得实例对象
    public static Singleton getSingleton(){
        return singleton;
    }

    //类中其他方法，尽量是static
    public static void daSomething(){
    }
}
```
线程不安全的懒汉式单例：
```java
package com.sigalhu.singleton.impl;

public class Singleton {
    private static Singleton singleton = null;

    //限制产生多个对象
    private Singleton(){}

    //通过该方法获得实例对象
    public static Singleton getSingleton(){
        if(singleton == null){
            singleton = new Singleton();
        }
        return singleton;
    }
}
```
线程安全的懒汉式单例：
```java
package com.sigalhu.singleton.impl;

public class Singleton {
    private static Singleton singleton = null;

    //限制产生多个对象
    private Singleton(){}

    //通过该方法获得实例对象
    public static Singleton getSingleton(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
```

#### 优点

* 内存中只有一个实例，减少了内存开支；
* 只生成一个实例，减少了系统的性能开销；
* 可以避免对资源的多重占用；
* 可以在系统设置全局的访问点，优化和共享资源访问。

#### 缺点

* 单例模式一般没有接口，扩展很困难，若要扩展，除了修改代码基本上没有第二种途径可以实现；
* 若单例模式没有完成，是不能进行测试的，没有接口也不能使用`mock`的方法虚拟一个对象；
* 单例模式与单一职责原则冲突。