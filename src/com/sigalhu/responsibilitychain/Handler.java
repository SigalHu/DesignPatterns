package com.sigalhu.responsibilitychain;

import com.sun.javaws.progress.PreloaderDelegate;

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
