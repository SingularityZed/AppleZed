## zed-thymeleaf项目
2020/01/16
generator的替代，因为在搭建zed-generator的时候忘记引入模板引擎 spring-boot-starter-freemarker
所以找到了跟springboot结合关系很好的thymeleaf项目，这个项目可以采用 XX.java 的文件形式进行模板化的操作
但是受众不多，资源偏向Html 搭建java模板费时，暂时不进行使用。

## HELP
测试项目时，因为是在everything项目中，因为引入mybatis-plus,所以需要注释掉mybatis-plus的POM依赖，才不会报错dataSource找不到
