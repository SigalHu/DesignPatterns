#### 定义

备忘录模式在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样以后就可将该对象恢复到原先保存的状态。

#### 类图

![](pic\1.png)

**`Originator` 发起人角色**

记录当前时刻的状态，负责定义哪些属于备份范围的状态，负责创建和恢复备忘录数据。

**`Memento` 备忘录角色**

负责存储`Originator`发起人对象的内部状态，在需要的时候提供发起人需要的内部状态。

**`Caretaker` 备忘录管理员角色**

对备忘录进行管理、保存和提供备忘录。


#### 实现

发起人角色：
```java
package com.sigalhu.mementopattern.impl;

public class Originator {
    //内部状态
    private String state = "";

    public String getState(){
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //创建一个备忘录
    public Memento createMemento(){
        return new Memento(this.state);
    }

    //恢复一个备忘录
    public void restoreMemento(Memento memento){
        this.setState(memento.getState());
    }
}
```
备忘录角色：
```java
package com.sigalhu.mementopattern.impl;

public class Memento {
    //发起人的内部状态
    private String state = "";

    public Memento(String state){
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
```
备忘录管理员角色：
```java
package com.sigalhu.mementopattern.impl;

public class Caretaker {
    //备忘录对象
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
```
场景类：
```java
package com.sigalhu.mementopattern.impl;

public class Client {
    public static void main(String[] args) {
        //定义发起人
        Originator originator = new Originator();
        //定义备忘录管理员
        Caretaker caretaker = new Caretaker();
        //创建一个备忘录
        caretaker.setMemento(originator.createMemento());
        //恢复一个备忘录
        originator.restoreMemento(caretaker.getMemento());
    }
}
```

#### 注意

* 备忘录创建出来就要在“最近”的代码中使用，要主动管理它的生命周期，建立就要使用，不使用就要立刻删除其引用，等待垃圾回收器对它的回收处理；
* 不要在频繁建立备份的场景中使用备忘录模式，原因有二：一是控制不了备忘录建立的对象数量，二是大对象的建立是要消耗资源的，系统性能需要考虑。