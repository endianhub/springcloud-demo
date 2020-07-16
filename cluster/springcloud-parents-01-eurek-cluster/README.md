#### SpringCloud 微服务搭建 - 多台注册中心



在微服务中，注册中心非常核心，可以实现服务治理，如果一旦注册出现故障的时候，可能会导致整个微服务无法访问，在这时候就需要对注册中心实现高可用集群模式。

默认情况下Eureka是让服务注册中心，不注册自己

    register-with-eureka: true
    fetch-registry: true
    
Eureka高可用实际上将自己作为服务向其他服务注册中心注册自己，这样就可以形成一组相互注册的服务注册中心，从而实现服务清单的互相同步，达到高可用效果。

当其中一台服务器宕机后另外一台主机自动同步注册中心注册的服务，从而不影响服务的正常运行。


 