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

## 规范
add ,delete,update,query  不要出现edit
@Validated 校验采用 ，不要用 @Valid


