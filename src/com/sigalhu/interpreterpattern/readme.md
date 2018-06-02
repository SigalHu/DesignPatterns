#### 定义

给定一门语言，定义它的文法的一种表示，并定义一个解释器，该解释器使用该表示来解释语言中的句子。

#### 类图

![](pic/1.png)

**`AbstractExpression` 抽象解释器**

抽象解释器是生成语法集合的关键，每个语法集合完成指定语法解析任务，它是通过递归调用的方式，最终由最小的语法单元进行解析完成。具体的解释任务由各个实现类完成，具体的解释器分别由`TerminalExpression`和`NonterminalExpression`完成。

**`TerminalExpression` 终结符表达式**

处理场景元素和数据的转换，实现与文法中的元素相关联的解释操作，通常一个解释器模式中只有一个终结符表达式，但有多个实例，对应不同的终结符。

注：终结符指需要具体赋值的对象，例如公式中的运算元素，除了需要赋值外，不需要做任何处理。

**`NonterminalExpression` 非终结符表达式**

文法中的每条规则对应于一个非终结表达式，并且每个文法规则都只关心自己周边的文法规则的结果，原则上每个文法规则都对应一个非终结表达式。

注：非终结表达式对应公式中的运算符号，需要编写算法进行处理，每个运算符号都要对应处理单元，否则公式无法进行。


#### 实现

抽象表达式：
```java
package com.sigalhu.interpreterpattern.impl;

public abstract class Expression {
    //每个表达式必须有一个解析任务
    public abstract Object interpreter(Context ctx);
}
```
终结符表达式：
```java
package com.sigalhu.interpreterpattern.impl;

public class TerminalExpression extends Expression {
    //通常终结符表达式只有一个，但是有多个对象
    @Override
    public Object interpreter(Context ctx) {
        return null;
    }
}
```
非终结符表达式：
```java
package com.sigalhu.interpreterpattern.impl;

public class NonterminalExpression extends Expression {
    //每个非终结符表达式都会对其他表达式产生依赖
    public NonterminalExpression(Expression... expressions){
    }

    @Override
    public Object interpreter(Context ctx) {
        //进行文法处理
        return null;
    }
}
```
客户类：
```java
package com.sigalhu.interpreterpattern.impl;

import java.util.Stack;

public class Client {
    public static void main(String[] args){
        Context ctx = new Context();
        //通常定一个语法容器，容纳一个具体的表达式
        Stack<Expression> stack = null;
        for(;;){
            //进行语法判断，并产生递归调用
        }
        //产生一个完整的语法数，由各个具体的语法分析进行解析
        Expression exp = stack.pop();
        //具体元素进入场景
        exp.interpreter(ctx);
    }
}
```

#### 优点

解释器是一个简单的语法分析工具，它最显著的优点就是扩展性，修改语法规则只要修改相应的非终结符表达式就可以了，若扩展语法，则只要增加非终结符类就可以了。

#### 缺点

* 会引起类膨胀。每个语法都要产生一个非终结符表达式，语法规则比较复杂时，就可能产生大量的类文件，为维护带来非常多的麻烦；
* 采用递归调用方法，导致调试非常复杂；
* 使用了大量的循环和递归，效率低下。

#### 注意

尽量不要在重要的模块中使用解释器模式，否则维护会是一个很大的问题。在项目中可以使用`shell`、`JRuby`、`Groovy`等脚本语言来代替解释器模式，弥补`Java`编译型语言的不足。