package com.jiankun.community.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("Singleton")  // 默认参数 singleton 都为单例
//@Scope("prototype")  // prototype每次访问bean都会有新的实例
public class AlphaService {

    public AlphaService() {
        System.out.println("Instantiating AlphaService");
    }

    @PostConstruct
    public void init() {
        System.out.println("Initializing AlphaService");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy AlphaService");
    }
}
