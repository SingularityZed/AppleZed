## 待办
generator

基础RBAC 



## 重建项目
2019/12/12  pom依赖问题重建一次




#AppleZed

#v1.0
springboot init 

## 定义POJO

VO 展示层,交互前端显示

DTO 数据转换层 数据库到service层处理

AO 接受数据实体, 查询,增加,编辑,接受字段封装

INFO 数据库对应信息实体封装  不用DO的是防止服务间调用误解

RequestEntity (org.springframework.http) 用这个实体进行接受数据,返回的响应码在Headers 的 General中

## 开发线
0、pom依赖
1、RBAC 基础
2、SpringBoot+Security+JWT
3、Spring Cloud
4、Redis
5、OSS

## 定义分层
controller rest层
manager 管理层 可以调用多个service 主要是不常用来与Dao层交互,没必要与service在进行一层交互 ，调用第三方时单独请求即可
service 层 直接与Dao层交互
Dao 与数据库交互

查询：分页是必须的事情，然后后面再说查询的权限限制

新增：controller 调用manager或者service ,校验数据是否符合，比如重复之类，

编辑：要编辑的数据是否存在，编辑的校验，比如重复

删除：软删除，逻辑不可删除



## 规范
add ,delete,update,query  不要出现edit
@Validated 校验采用 ，不要用 @Valid


## 遇到问题
#### 2019/12/13  
在启动类所在的pom.xml配置中加了 ' <packaging>pom</packaging> ' 导致配置文件 application.yml 读取不到。
解决地址:https://www.jb51.net/article/171586.htm
扩展:maven中的packaging标签 https://www.cnblogs.com/seven717/p/9802813.html


## 版本管理
暂时不需要分，具体再想办法