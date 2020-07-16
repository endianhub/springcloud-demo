#### SpringCloud 微服务搭建 - 使用 Feign 服务间通信


在 SpringCloud 中服务与服务之间通信有两种方式：
- RestTemplate：是采用服务别名方式调用，根据别名去注册中心上获取对应的服务调用地址；
- fegin：是直接调用

<br>

### 本节主要谈的是使用 Feign 通信。

<br>

Feign客户端是一个web声明式http远程调用工具，提供了接口和注解方式进行调用。想要使用 Feign 通信，需要引入

    <!-- SpringBoot整合fegnin客户端 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>

使用 @FeignClient 注解指向调用的服务名称，如：

    @FeignClient(value = "api-member-service")

并再启动类中加入 @EnableFeignClients 注解，开启 Feign 通信。

Feign 通信客户端默认等待时间为1秒，超出后会报连接超时的问题，浏览器会显示500，但后台仍然会继续执行。想要解决此问题需要再配置文件中加入

    # 设置feign客户端超时时间
    ribbon:
      # 指的是建立连接所用的时间，适用于网络状况正常的情况下，两端连接所用的时间。
      ReadTimeout: 5000
      # 指的是建立连接后从服务器读取到可用资源所用的时间。
      ConnectTimeout: 5000

<br>

如果在处理大量请求（超出最大线程池）时会产生微服务全部瘫痪，导致浏览器一直等待相应，俗称服务雪崩效应。

模拟最大线程池量

    server:
      tomcat:
        threads:
          max: 10 # 假设模拟最大线程数


<br>

##### 注册中心
- springcloud-eureka


##### 服务提供者
springcloud-api-member-service


##### 服务消费者
springcloud-order-server


<br><br>


##### 参考地址：
- https://blog.csdn.net/weixin_44448094/article/details/88535475
- https://www.cnblogs.com/Guroer/p/10480359.html