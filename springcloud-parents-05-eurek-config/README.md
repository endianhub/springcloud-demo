## Spring Cloud Config-配置中心

在日常开发中一般都是直接把相关配置放在单独的配置文件中，通常以 properties 或者 yml 的格式出现，但是这样的方式有个明显的问题，那就是当配置文件发生改变的时候，必须重启服务才能使得新的配置文件生效，否则配置无法生效。

Spring Cloud Config可以实现微服务中的所有系统的配置文件的统一管理，而且还可以实现当配置文件发生变化的时候，系统会自动更新获取新的配置。

对于 Spring Cloud 来说就是通过 Config 来获取配置中心的配置信息来实现的。目前有一些用的比较多的开源的配置中心，比如携程的 Apollo、蚂蚁金服的 disconf 等。


##### 先在 github/gitree 中建立配置文件

- config/config-client-dev.yml
- config/config-client-prod.yml

**配置文件的内容大致如下：**

	data:
	  env: config-eureka-dev


#### 注意：
文件名不是乱起的，Spring Cloud Config 有它的一套访问规则，我们通过这套规则在浏览器上访问。

	/{application}/{profile}[/{label}]
	/{application}-{profile}.yml
	/{label}/{application}-{profile}.yml
	/{application}-{profile}.properties
	/{label}/{application}-{profile}.properties

{application} 就是应用名称，对应到配置文件上来，就是配置文件的名称部分，例如我上面创建的配置文件。

{profile} 就是配置文件的版本，我们的项目有开发版本、测试环境版本、生产环境版本，对应到配置文件上来就是以 application-{profile}.yml 加以区分，例如application-dev.yml、application-sit.yml、application-prod.yml。

{label} 表示 git 分支，默认是 master 分支，如果项目是以分支做区分也是可以的，那就可以通过不同的 label 来控制访问不同的配置文件了。

如果配置文件的名称是 application.properties/application.yml，如果直接通过该名称访问是获取不到的，因为在配置文件名需要通过-来进行获取，如果配置文件名称没有-，那么添加了-之后，会自动进行匹配搜索。


例如上面的 config-client-dev.yml 和 config-client-prod.yml 这两个是同一个项目的不同版本，项目名称为 config-client， 一个对应开发版，一个对应正式版。





<br><br>

### 手动刷新客户端配置内容(Spring Cloud Config)

客户端项目增加依赖项

	<dependency>
	  <groupId>org.springframework.boot</groupId>
	  <artifactId>spring-boot-starter-actuator</artifactId>
	</dependency>

客户端项目修改配置文件增加：

	#显示的暴露接入点
	management:
	  endpoints:
		web:
		  exposure:
			include: refresh,health,info


在使用配置中心的类上添加 @RefreshScope 注解，没有此注解刷新是不生效的。当配置更改时，标有 @RefreshScope 的 Bean 将得到特殊处理来生效配置。

使用 POST 刷新 http://[IP]:[客户端端口]/actuat/refresh 


<br><br>

### Spring Cloud 配置文件 application.yml和 bootstrap.yml区别

- bootstrap.yml（bootstrap.properties）用来在程序引导时执行，应用于更加早期配置信息读取，如可以使用来配置application.yml中使用到参数等。
- application.yml（application.properties) 应用程序特有配置信息，可以用来配置后续各个模块中需使用的公共参数等。

#### bootstrap.yml（bootstrap.properties）与application.yml（application.properties）执行顺序 

bootstrap.yml 先于 application.yml 加载