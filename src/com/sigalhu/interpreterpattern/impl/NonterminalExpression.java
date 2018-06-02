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
