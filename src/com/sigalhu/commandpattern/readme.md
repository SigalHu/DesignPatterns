#### 定义

命令模式将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能。

#### 类图

![](pic/1.png)

**`Receiver` 接收者角色**

该角色就是干活的角色，命令传递到这里是应该被执行的。

**`Command` 命令角色**

需要执行的所有命令都在这里声明。

**`Invoker` 调用者角色**

接收到命令，并执行命令。

#### 实现

通用`Receiver`类：
```java
package com.sigalhu.commandpattern.impl;

public abstract class Receiver {
    //抽象接受者，定义每个接受者都必须完成的业务
    public abstract void doSomething();
}
```
具体的`Receiver`类：
```java
package com.sigalhu.commandpattern.impl;

public class ConcreteReceiver1  extends Receiver{
    //每个接受者都必须处理一定的业务逻辑
    @Override
    public void doSomething() {
    }
}
```
```java
package com.sigalhu.commandpattern.impl;

public class ConcreteReceiver2 extends Receiver{
    //每个接受者都必须处理一定的业务逻辑
    @Override
    public void doSomething() {
    }
}
```
抽象的`Command`类：
```java
package com.sigalhu.commandpattern.impl;

public abstract class Command {
    //每个命令类都必须有一个执行命令的方法
    public abstract void execute();
}
```
具体的`Command`类：
```java
package com.sigalhu.commandpattern.impl;

public class ConcreteCommand1 extends Command {
    //对哪个Receiver类进行命令处理
    public Receiver receiver;

    //构造函数传递接受者
    public ConcreteCommand1(Receiver _receiver){
        this.receiver = _receiver;
    }

    //必须实现一个命令
    @Override
    public void execute() {
        //业务处理
        this.receiver.doSomething();
    }
}
```
```java
package com.sigalhu.commandpattern.impl;

public class ConcreteCommand2 extends Command {
    //对哪个Receiver类进行命令处理
    public Receiver receiver;

    //构造函数传递接受者
    public ConcreteCommand2(Receiver _receiver){
        this.receiver = _receiver;
    }

    //必须实现一个命令
    @Override
    public void execute() {
        //业务处理
        this.receiver.doSomething();
    }
}
```
调用者`Invoker`类：
```java
package com.sigalhu.commandpattern.impl;

public class Invoker {
    private Command command;

    //接受命令
    public void setCommand(Command _command){
        this.command = _command;
    }

    //执行命令
    public void action(){
        this.command.execute();
    }
}
```
场景类：
```java
package com.sigalhu.commandpattern.impl;

public class Client {
    public static void main(String[] args){
        //首先声明调用者Invoker
        Invoker invoker = new Invoker();
        //定义接收者
        Receiver receiver = new ConcreteReceiver1();
        //定义一个发送给接收者的命令
        Command command = new ConcreteCommand1(receiver);
        //把命令交给调用者去执行
        invoker.setCommand(command);
        invoker.action();
    }
}
```

#### 优点

* 类间解耦。调用者角色与接收者角色之间没有任何依赖关系；
* 可扩展性。`Command`的子类可以非常容易地扩展；
* 命令模式结合其他模式会更优秀。例如可以结合责任链模式，实现命令族解析任务。

#### 缺点

如果有`N`个命令，`Command`的子类就要有`N`个，会导致类膨胀。

#### 注意

在项目中，约定的优先级最高，每一个命令是对一个或多个`Receiver`的封装，我们可以通过有意义的类名或命令名处理命令角色和接收者角色的耦合关系，减少高层模块对低层模块的依赖关系，提高系统整体的稳定性。因此，建议大家在实际项目开发时采用封闭`Receiver`的方式，减少`Client`对`Receiver`的依赖。