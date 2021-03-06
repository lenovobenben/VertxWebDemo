# VertxWebDemo

## 项目意义
vertx-web 集成 mybatis ，对 DB 事务进行了封装，一般业务无需关心数据库事务。  
使之风格接近于 tomcat + ssm 。  
这样做抹杀了 vertx 的异步回调特性，只需要写同步代码就可以了。  
当然，如果您喜欢异步回调，直接调用 vertx 原生 API 即可，两者并不冲突。

阻塞的 Jedis API 支持。

稍微封装了基于 EventBus 的异步 RPC 调用。

## 一些说明

需要准备测试表：  
CREATE TABLE user (
	id INT(11) NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	age INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (id)
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;

  
测试 URL 地址:  
http://localhost:8888/html/t.html

## 我的逻辑代码怎么写？
普通业务逻辑：  

同步 Handler ，继承 BaseDbHandler / BaseBlockHandler / BaseHandler 。逻辑里面不要写异步代码！！！  

异步 Handler ，继续使用原生的 API 即可。

在 MyRouter 中注册你写的 Handler。  
  
  
拦截器逻辑：  
Interceptor 里一个 before ，一个 after 。

Handler 相当于 SSM 的 Controller 层。  
可以从中抽离出 Service 层，如果你喜欢。  
可以抽离出 DAO 层，如果你喜欢。  


服务器启动时执行的代码需要写在 MyVerticle 的 start 方法中。

建议所有的业务逻辑都写成单例模式！


