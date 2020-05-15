## 待办
generator

基础RBAC 

菜单角色等处理



## 重建项目
2019/12/12  pom依赖问题重建一次

2020/01/15  代码生成找不到模板,决定采用thymeleaf的方式生成
https://github.com/BoomManPro/thymeleaf-template
https://www.thymeleaf.org/
https://github.com/thymeleaf/thymeleaf/issues/395

2020/01/16  
2020/01/16  


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

verify类与manager类可能会互相调用，但是这是随着业务复杂度必然会出现的，不影响建立类的初衷与排查

查询：分页是必须的事情，然后后面再说查询的权限限制

新增：controller 调用manager或者service ,校验数据是否符合，比如重复之类，

编辑：要编辑的数据是否存在，编辑的校验，比如重复

删除：软删除，逻辑不可删除



## 规范
add ,delete,update,query  不要出现edit ，前端显示编辑，但是后端必更新
@Validated 校验采用 ，不要用 @Valid

接口API定义，开放前端为 api/service名称/版本/具体业务
接口API定义，服务间调用 inner/service名称/版本/具体业务

#### 注解注入(方便,但可能引用不到)
@Autowired
尽量通过set方法注入 避免引入不到 

#### 构造器注入(旨在不变性):
   private final GeneratorService generatorService;
    public GeneratorController(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }
    
#### 通过set方法注入(可配置性):
    private GeneratorService generatorService;
    @Autowired
    public void setGeneratorService(GeneratorService generatorService) {
        this.generatorService = generatorService;
    }
#### DTO
DTO 的目的是为了数据转换，所以没必要加上swagger注解，而且直接继承实体类即可，
而扩展字段就可以清晰的看到，但是mapper.xml中引用注意用getter方法

## 遇到问题
#### 2019/12/13  
在启动类所在的pom.xml配置中加了 ' <packaging>pom</packaging> ' 导致配置文件 application.yml 读取不到。
解决地址:https://www.jb51.net/article/171586.htm
扩展:maven中的packaging标签 https://www.cnblogs.com/seven717/p/9802813.html

LambdaQueryChainWrapper  链式查询 就是多表查询
eg:https://mybatis.plus/guide/crud-interface.html#count


GlobalExceptionHandler捕获异常没有成功，可能是扫描Bean没成功，加下面注解解决 2020/01/16
@ComponentScan(basePackages = "com.zed")


## 版本管理
POM版本管理，因为国内的版本以及Maven的版本落后，优先使用稳定版本
暂时不需要分，具体再想办法
