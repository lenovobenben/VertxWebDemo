# VertxWebDemo

## 项目意义
vertx-web 集成 mybatis ，对 DB 事务进行了封装，一般业务无需关心数据库事务。  
使之风格接近于 tomcat + ssm 。

## 一些说明

需要准备测试表：  
CREATE TABLE &apos;user&apos; (
	&apos;id&apos; INT(11) NOT NULL AUTO_INCREMENT,
	&apos;name&apos; VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_unicode_ci',
	&apos;age&apos; INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (&apos;id&apos;)
)
COLLATE='utf8_unicode_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;

  
测试 URL 地址:  
http://localhost:8888/html/t.html

## 我的逻辑代码怎么写？
普通业务逻辑：  
编写 Handler ，继承 BaseDbHandler 或 BaseHandler 。  
在 MyRouter 中注册(即初始化)你写的 Handler。  
  
  
拦截器逻辑：  
Interceptor 里一个 before ，一个 after 。

Handler 相当于 SSM 的 Controller 层。
可以从中抽离出 Service 层，如果你喜欢。  
可以抽离出 DAO 层，如果你喜欢。  


服务器启动时执行的代码需要写在 MyVerticle 的 start 方法中。

建议所有的业务逻辑都写成单例模式！


