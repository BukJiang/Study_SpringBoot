package com.buk.asynccontroller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * TODO: 异步请求
 *
 * @author BuK
 * @since 2020/08/18
 */
@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

    /**
     * 异步请求
     *
     * @param request
     * @param response
     */
    @RequestMapping("/servlet")
    public void servlet(HttpServletRequest request, HttpServletResponse response) {
        AsyncContext asyncContext = request.startAsync();
        // 设置监听器:可设置其开始、完成、异常、超时等事件的回调处理
        asyncContext.addListener(new AsyncListener() {
            @Override
            public void onTimeout(AsyncEvent event) {
                log.info("[异步请求]-servlet: onTimeout->超时");
                // 做一些超时后的相关操作...
            }

            @Override
            public void onStartAsync(AsyncEvent event) {
                log.info("[异步请求]-servlet: onStartAsync->线程开始");
            }

            @Override
            public void onError(AsyncEvent event) {
                log.info("[异步请求]-servlet: onError->发生错误：{}", event.getThrowable());
            }

            @Override
            public void onComplete(AsyncEvent event) {
                log.info("[异步请求]-servlet: onComplete->执行完成");
                // 这里可以做一些清理资源的操作...
            }
        });
        //设置超时时间
        asyncContext.setTimeout(20000);
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    log.info("[异步请求]-servlet: ->内部线程开始：{}", Thread.currentThread().getName());
                    asyncContext.getResponse().setCharacterEncoding("utf-8");
                    asyncContext.getResponse().setContentType("text/html;charset=UTF-8");
                    asyncContext.getResponse().getWriter().println("[异步请求servlet]: ->这是异步的请求返回");
                } catch (Exception e) {
                    log.info("[异步请求]-servlet: ->异常：{}", e.getMessage());
                }
                // 异步请求完成通知
                // 此时整个请求才完成
                asyncContext.complete();
            }
        });
        // 此时之类 request的线程连接已经释放了
        log.info("[异步请求]-servlet: ->外部线程结束：{}", Thread.currentThread().getName());
    }

    /**
     * 异步请求
     *
     * @return
     */
    @RequestMapping("callable")
    public Callable<String> callable() {
        log.info("[异步请求]-callable: ->外部线程开始：{}", Thread.currentThread().getName());
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("[异步请求]-callable: ->内部线程开始：{}", Thread.currentThread().getName());
                Thread.sleep(3000);
                log.info("[异步请求]-callable: ->内部线程结束：{}", Thread.currentThread().getName());
                return "[异步请求callable]";
            }
        };
        log.info("[异步请求]-callable: ->外部线程结束：{}", Thread.currentThread().getName());
        return callable;
    }

    /**
     * 异步请求
     *
     * @return
     */
    @RequestMapping("webAsyncTask")
    public WebAsyncTask<String> webAsyncTask() {
        log.info("[异步请求]-webAsyncTask: 外部线程开始：{}", Thread.currentThread().getName());

        Callable<String> result = () -> {
            log.info("[异步请求]-webAsyncTask: 内部线程开始：{}", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(4);
            log.info("[异步请求]-webAsyncTask: 内部线程返回：{}", Thread.currentThread().getName());
            return "[异步请求]-webAsyncTask";
        };

        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(3000L, result);
        webAsyncTask.onTimeout(new Callable<String>() {
            @Override
            public String call() {
                log.info("[异步请求]-webAsyncTask: onTimeout->超时");
                // 做一些超时后的相关操作...
                return "[异步请求]-webAsyncTask: onTimeout->超时";
            }
        });
        return webAsyncTask;
    }

    /**
     * 异步请求
     *
     * @return
     */
    @RequestMapping("/atAsync")
    public Future<String> atAsync() throws InterruptedException {
        log.info("[异步请求]-atAsync: 外部线程开始：{}", Thread.currentThread().getName());

        // @Async异步，可配置 TaskExecutor Bean 线程池管理
        Thread.sleep(3000);

        log.info("[异步请求]-atAsync: 外部线程结束：{}", Thread.currentThread().getName());
        return new AsyncResult<>("[异步请求]-atAsync");
    }

    /**
     * 异步请求
     *
     * @return
     */
    @RequestMapping("/deferredResult")
    public DeferredResult<String> deferredResult() {
        log.info("[异步请求]-deferredResult: 外部线程开始：{}", Thread.currentThread().getName());
        // 设置超时时间
        DeferredResult<String> result = new DeferredResult<>(60 * 1000L);
        // 处理超时事件 采用委托机制
        result.onTimeout(new Runnable() {
            @Override
            public void run() {
                log.info("[异步请求]-deferredResult: onTimeout->超时");
                result.setResult("[异步请求]-deferredResult: onTimeout->超时");
            }
        });
        // 处理完成事件 采用委托机制
        result.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info("[异步请求]-deferredResult: onCompletion->执行完成");
                // 这里可以做一些清理资源的操作...
            }
        });

        ThreadPoolTaskExecutor myThreadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        myThreadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                // 处理业务逻辑
                log.info("[异步请求]-deferredResult: 内部线程开始：{}", Thread.currentThread().getName());
                // 返回结果
                result.setResult("[异步请求]-deferredResult");
            }
        });
        return result;
    }
}