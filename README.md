## 简介
这是一套个人搭建的spring demo，基本上可以满足生产的简单运用。

## 服务职能
* 注册中心 服务列表，上下线  
    * eureka-service1  
    * eureka-service2  
* 网关中心 外部流量接入层  
    * gateway-service  
* 服务提供者和服务消费者 在实际情况下，一个应用大多是同时提供服务也消费服务  
    * producer-service  
    * consumer-service  
* 通用模块 熔断器控制台，请求链控制台  
    * common-service  
* 服务监控中心 检测服务状态  
    * admin-service  
* 安全登录框架demo  
    * security-demo  

## 技术栈:版本
* java:8  
* springboot:2.1.4  
* springcloud:Greenwich.SR1  
* lombok:1.18.6  
* mybatis-plus:3.2.0  
* h2:1.4.199  
* swargger2:2.9.2
等等  


## 本地HOST配置
127.0.0.1 eureka-test1.joinboom.net  
127.0.0.1 eureka-test2.joinboom.net  
127.0.0.1 producer-test.joinboom.net  
127.0.0.1 consumer-test.joinboom.net  
127.0.0.1 gateway-test.joinboom.net  
127.0.0.1 common-test.joinboom.net  
127.0.0.1 config-test.joinboom.com  
127.0.0.1 admin-test.joinboom.net  
127.0.0.1 security-test.joinboom.net  

## 服务名--描述--应用端口号--监控端口号
eureka-service1--注册中心1--7201--8201  
eureka-service2--注册中心2--7202--8202  
gateway-service--网关中心--7203--8203  
producer-service--服务生产者--7204--8204  
consumer-service--服务消费者--7205--8205  
common-service--通用服务--7206--8206  
apollo-configservice--阿波罗配置服务--7207  
apollo-adminservice--阿波罗管理服务--7208  
apollo-portal--阿波罗配置中心--7209  
admin-service--服务监控中心--7210--8210  
security-demo--安全登录框架案例--7211--8211  

apollo的三个服务参考[apollo-diy](https://github.com/wangtoye/apollo-diy)