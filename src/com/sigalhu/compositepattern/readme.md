#### 定义

组合模式将对象组合成树形结构以表示“部分-整体”的层次结构，使得用户对单个对象和组合对象的使用具有一致性。

#### 类图

![](pic\1.png)

**`Component` 抽象构件角色**

定义参加组合对象的共有方法与属性，可以定义一些默认的行为或属性。

**`Leaf` 叶子构件**

叶子对象其下没有分支，是遍历的最小单位。

**`Composite` 树枝构件**

树枝对象的作用是组合树枝节点和叶子节点形成一个树形结构。

#### 实现

抽象构件：
```java
package com.sigalhu.compositepattern.impl;

public abstract class Component {
    //个体和整体都具有的共享
    public void doSomething(){
        //业务逻辑
    }
}
```
树枝构件：
```java
package com.sigalhu.compositepattern.impl;

import java.util.ArrayList;

public class Composite extends Component {
    //构件容器
    private ArrayList<Component> componentArrayList = new ArrayList<Component>();

    //增加一个叶子构件或树枝构件
    public void add(Component component){
        this.componentArrayList.add(component);
    }

    //删除一个叶子构件或树枝构件
    public void remove(Component component){
        this.componentArrayList.remove(component);
    }

    //获得分支下的所有叶子构件和树枝构件
    public ArrayList<Component> getChildren() {
        return this.componentArrayList;
    }
}
```
叶子构件：
```java
package com.sigalhu.compositepattern.impl;

public class Leaf extends Component {
    //可以覆写父类方法
    @Override
    public void doSomething() {
        super.doSomething();
    }
}
```
场景类：
```java
package com.sigalhu.compositepattern.impl;

public class Client {
    public static void main(String[] args) {
        //创建一个根节点
        Composite root = new Composite();
        root.doSomething();
        //创建一个树枝构件
        Composite branch = new Composite();
        //创建一个叶子节点
        Leaf leaf = new Leaf();
        //建立整体
        root.add(branch);
        branch.add(leaf);
    }

    //通过递归遍历树
    public static void display(Composite root) {
        for (Component c : root.getChildren()) {
            if (c instanceof Leaf) {  //叶子节点
                c.doSomething();
            } else {  //树枝节点
                display((Composite) c);
            }
        }
    }
}
```

#### 优点

* 高层模块调用简单，不必担心自己处理的是单个对象还是整个组合结构，简化了高层模块的代码；
* 节点自由增加，符合开闭原则，有利于后续维护。

#### 缺点

与依赖倒置原则冲突，限制了接口的影响范围。