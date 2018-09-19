mybatis-generator-gui
==============

mybatis-generator-gui是基于[mybatis generator](http://www.mybatis.org/generator/index.html)开发一款界面工具, 本工具可以使你非常容易及快速生成Mybatis的Java POJO文件及数据库Mapping文件。

本工程是在 [MGG](https://github.com/astarring/mybatis-generator-gui)基础上加`mybatis generator`源码，对源码修改调整定制生成源码。删除了很多不必要的方法，类和mapper id

### 启动本软件

 IDE中运行

Eclipse or IntelliJ IDEA中启动, 找到```com.zzg.mybatis.generator.MainUI```类并运行就可以了

[原始软件使用指南](https://github.com/astarring/mybatis-generator-gui/wiki/Usage-Guide)

### 改动内容
- Bean删除不必要的内容，只保留基础的属性和注释，使用lombok @Data

- DAO，保留基础日常常用方法listRecord、getRecord、saveRecord、removeRecord、updateRecord

- 新增Service、ServiceImpl类生成，默认实现Dao方法调用

依然有很多细节部分没顾及到，尽力让生成后的代码调整最小，后续再逐渐完善

Copyright 2018 by baizhang
