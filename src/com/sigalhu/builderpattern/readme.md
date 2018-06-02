#### 定义

建造者模式将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。

#### 类图

![](pic/1.png)

**`Product` 产品类**

通常实现了模板方法模式，也就是有模板方法和基本方法。

**`Builder` 抽象建造者**

规范产品的组建，一般是由子类实现。

**`ConcreteBuilder` 具体建造者**

实现抽象类定义的所有方法，并返回一个组建好的对象。

**`Director` 导演类**

负责安排已有模板的顺序，然后告诉`Builder`开始建造。

#### 实现

产品类：
```java
package com.sigalhu.builderpattern.impl;

public class Product {
    public void doSomething(){
        //独立业务处理
    }
}
```
抽象建造者：
```java
package com.sigalhu.builderpattern.impl;

public abstract class Builder {
    //设置产品的不同部分，已获得不同产品
    public abstract void setPart();

    //建造产品
    public abstract Product buildProduct();
}
```
具体建造者：
```java
package com.sigalhu.builderpattern.impl;

public class ConcreteBuilder extends Builder {
    private Product product = new Product();

    //设置产品零件
    @Override
    public void setPart() {
        //产品类内的逻辑处理
    }

    //组建一个产品
    @Override
    public Product buildProduct() {
        return product;
    }
}
```
导演类：
```java
package com.sigalhu.builderpattern.impl;

public class Director {
    private Builder builder = new ConcreteBuilder();

    //构建不同的产品
    public Product getAProduct(){
        builder.setPart();
        //设置不同的零件，产生不同的产品
        return builder.buildProduct();
    }
}
```

#### 优点

* 封装性。使用建造者模式可以使客户端不必知道产品内部组成的细节；
* 建造者独立，容易扩展；
* 便于控制细节风险，可以对建造过程逐步细化，而不对其他的模块产生任何影响。

#### 注意

建造者模式最主要的功能是基本方法的调用顺序安排，也就是这些基本方法已经实现了，顺序不同产生的对象也不同；而工厂方法模式则重点是创建，创建零件是它的主要职责，组装顺序则不是它关心的。
