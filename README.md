# sharding-jdbc-example
spring+hibernate examples using dangdang-sharding-jdbc.

网上很多例子是基于spring+mybatis的方式构建的测试功能，
由于项目里使用hibernate较多，因此调整为hibernate框架映射

1. 基于spring 配置文件方式
2. 使用hibernate的orm映射框架
3. 直接修改配置文件jdbc.properties，跑测试用例即可

两个测试用例，三个model，测试用例在src/main/test下
其中，User,WechatUser是分别以uid、openid进行hash到不同库表中的
UserApplication不以分库表的形式读写。
