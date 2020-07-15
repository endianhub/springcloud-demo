## SpringCloud 微服务搭建 - Hystrix 服务保护

<br>

## 本节主要使用 Hystrix 解决服务雪崩效应 - 接口方式

Hystrix 能够帮我们解决哪些事情？
- 断路器
- 服务降级
- 服务熔断
- 服务隔离


### 解决服务雪崩效应的方式有：

#### 服务降级
在高并发情况下，防止用户一直等待，使用服务降级方式(直接返回一个友好的提示给客户端，调用fallBack方法)，目的方便用户体验。比如秒杀系统：当请求人数过多的时候，提示用户重新尝试或正在排队中....

#### 服务熔断
熔断机制目的为了保护服务，在高并发的情况下，如果请求达到一定极限(可以自己设置阔值)如果流量超出了设置阈值，让后直接拒绝访问，保护当前服务。使用服务降级方式返回一个友好提示，服务熔断和服务降级一起使用.

#### 服务隔离
因为默认情况下，只有一个线程池会维护所有的服务接口，如果大量的请求访问同一个接口，达到tomcat 线程池默认极限，可能会导致其他服务无法访问。

**解决服务雪崩效应：** 使用服务隔离机制(线程池方式和信号量)，使用线程池方式实現隔离的原理:  相当于每个接口(服务)都有自己独立的线程池，因为每个线程池互不影响，这样的话就可以解决服务雪崩效应。

**线程池隔离：** 每个服务接口，都有自己独立的线程池，每个线程池互不影响。

**信号量隔离：** 使用一个原子计数器（或信号量）来记录当前有多少个线程在运行，当请求进来时先判断计数器的数值，若超过设置的最大线程个数则拒绝该请求，若不超过则通行，这时候计数器+1，请求返 回成功后计数器-1。

#### 服务限流
服务限流就是对接口访问进行限制，常用服务限流算法令牌桶、漏桶。计数器也可以进行粗暴限流实现。

<br>
<hr>
<br>

使用 Hystrix 功能，需要引入以下配置：

    <!-- hystrix断路器 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
    </dependency>

实现 Hystrix 服务保护有两种方式：
- 注解方式
- 接口方式

### 使用接口的方式

使用接口的方式实现 Hystrix 服务保护，需要在 @FeignClient 注解上做修改，fallback 指向熔断类即可（推荐使用）。

    @FeignClient(value = "api-member-service", fallback = MemberServiceFallback.class)

Hystrix 服务超时时间默认1秒，超出1秒则不能访问，可以禁止服务超时时间或调整服务超时时间，如：

    hystrix:
      command:
        default:
          execution:
            timeout:
              enabled: false # hystrix禁止服务超时时间

或
          
    hystrix:
      command:
        default:
          execution:
            isolation:
              thread:
                timeoutInMilliseconds: 5000 # 设置超时时间