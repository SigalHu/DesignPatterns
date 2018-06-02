package com.sigalhu.interpreterpattern.impl;

public class TerminalExpression extends Expression {
    //通常终结符表达式只有一个，但是有多个对象
    @Override
    public Object interpreter(Context ctx) {
        return null;
    }
}
