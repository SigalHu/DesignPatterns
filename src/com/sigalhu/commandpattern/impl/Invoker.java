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
