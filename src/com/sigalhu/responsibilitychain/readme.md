#### 定义

责任链模式通过将多个对象连成一条链，并沿着这条链传递请求，直到有对象处理它为止，这样可以避免请求的发送者与接受者之间的耦合关系。

#### 类图

![](pic/1.png)

抽象处理者实现三个职责：一是定义一个请求的处理方法`handleMessage`，唯一对外开放；二是定义一个链的编排方法`setNext`，设置下一个处理者；三是定义具体请求者必须实现的两个方法：定义自己能够处理的级别`getHandlerLevel`和具体的处理任务`echo`。

在处理者中涉及三个类：`Level`类负责定义请求和处理级别，`Request`类负责封装请求，`Response`负责封装链中的返回结果。

#### 实现

抽象处理者：
```java
package com.sigalhu.responsibilitychain.impl;

public abstract class Handler {
    private Handler nextHandler;

    //每个处理者都必须对请求做出处理
    public final Response handleMessage(Request request){
        Response response = null;
        //判断是否是自己的处理级别
        if(this.getHandlerLevel().equals(request.getRequestLevel())){
            response = this.echo(request);
        } else {
            //判断是否有下一个处理者
            if(this.nextHandler != null){
                response = this.nextHandler.handleMessage(request);
            } else {
                //没有适当的处理者，业务自行处理
            }
        }
        return response;
    }

    //设置下一个处理者是谁
    public void setNext(Handler _handler){
        this.nextHandler = _handler;
    }

    //每个处理者都有一个处理级别
    protected abstract Level getHandlerLevel();

    //每个处理者都必须实现处理任务
    protected abstract Response echo(Request request);
}
```
具体处理者：
```java
package com.sigalhu.responsibilitychain.impl;

public class ConcreteHandler1 extends Handler {
    //定义自己的处理逻辑
    @Override
    protected Response echo(Request request) {
        return null;
    }

    //设置自己的处理级别
    @Override
    protected Level getHandlerLevel() {
        return null;
    }
}
```
```java
package com.sigalhu.responsibilitychain.impl;

public class ConcreteHandler2 extends Handler{
    //定义自己的处理逻辑
    @Override
    protected Response echo(Request request) {
        return null;
    }

    //设置自己的处理级别
    @Override
    protected Level getHandlerLevel() {
        return null;
    }
}
```
```java
package com.sigalhu.responsibilitychain.impl;

public class ConcreteHandler3 extends Handler {
    //定义自己的处理逻辑
    @Override
    protected Response echo(Request request) {
        return null;
    }

    //设置自己的处理级别
    @Override
    protected Level getHandlerLevel() {
        return null;
    }
}
```
模块中有关框架代码：
```java
package com.sigalhu.responsibilitychain.impl;

public class Level {
    //定义一个请求和处理等级
}
```
```java
package com.sigalhu.responsibilitychain.impl;

public class Request {
    //请求的等级
    public Level getRequestLevel(){
        return null;
    }
}
```
```java
package com.sigalhu.responsibilitychain.impl;

public class Response {
    //处理者返回的数据
}
```
场景类：
```java
package com.sigalhu.responsibilitychain.impl;

public class Client {
    public static void main(String[] args){
        //声明所有的处理节点
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        //设置链中的阶段顺序1-->2-->3
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        //提交请求，返回结果
        Response response = handler1.handleMessage(new Request());
    }
}
```

#### 优点

将请求与处理分开，请求者可以不用知道是谁处理的，处理者可以不用知道请求的全貌，两者解耦，提高系统的灵活性。

#### 缺点

* 性能问题。每个请求都是从链头遍历到链尾，特别是在链比较长的时候，性能是一个非常大的问题；
* 调试不方便。特别是链条比较长，环节比较多的时候，由于采用了类似递归的方式，调试的时候逻辑可能比较复杂。

#### 注意

链中节点数量需要控制，避免出现超长链的情况，一般的做法是在`Handler`中设置一个最大节点数量，在`setNext`方法中判断是否已经超过其阈值，超过则不允许该链建立，避免无意识地破坏系统性能。