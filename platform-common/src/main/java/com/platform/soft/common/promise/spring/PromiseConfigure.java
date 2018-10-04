package com.platform.soft.common.promise.spring;

import com.platform.soft.common.promise.core.Promise;

import java.util.concurrent.ExecutorService;


public class PromiseConfigure implements org.springframework.beans.factory.InitializingBean {


    /**
     * 使用Spring容器的时候可以自定义线程池服务
     */
    ExecutorService executorService;


    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>This method allows the bean instance to perform initialization only
     * possible when all bean properties have been set and to throw an
     * exception in the event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such
     *                   as failure to set an essential property) or if initialization fails.
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Promise.setExecutorService(executorService);

    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }
}
