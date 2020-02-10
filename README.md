# 简介
长征后台管理系统，是一个轻量级的SpringBoot快捷开发项目，代码规范，学习简单，易于扩展，可作为基础框架使用。

------
### 项目演示
项目地址：https://www.longmarch.top  
账号密码：admin/111111  
视频演示：http://www.56.com/u61/v_MTYyMzg3Nzcw.html  

### 系统特色

 - 前后端分离
    后端：SpringBoot+MyBatis-pus+Shiro
    前端：vue-element-admin
 - 后端代码按照业务模块进行分层，方便后期项目拆分和扩展
 - RBAC权限管理 控制到按钮级权限（实现无权限时按钮不可见）
 - 数据权限过滤，实现个人，部门，全部三种数据权限范围
 - 前后端字典统一管理，前端动态获取字典实现Code和label自动转换
 - 后端代码自动生成工具，统一代码风格
 - 注解Log日志拦截，登录日志和操作日志
 - 统一异常捕获，统一处理
 - Spring Cache与Shiro Cache对接，统一管理

### 已实现功能

 1. 认证授权
 2. 用户管理
 3. 角色管理
 4. 部门管理
 5. 权限管理
 6. 字典管理
 7. 参数管理
 8. 登录日志
 9. 业务日志
 10. 任务管理
 11. 内容管理
    11.1 分类管理
    11.2 文章管理

### 项目选项
| 技术        | 版本   |  地址  |
| --------   | :-----:  | :----  |
| Spring Boot     | 2.1.6.RELEASE |   http://projects.spring.io/spring-boot/     |
| mybatis-plus-boot-starter        |   3.3.1.tmp   |   http://mp.baomidou.com/guide/install.html#spring-boot   |
| druid-spring-boot-starter        |    1.1.17    |  https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter  |
| druid-spring-boot-starter.sersion        |    1.1.17    |  https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter  |
| shiro-spring-boot-web-starter        |    1.4.2    |  http://shiro.apache.org  |
| hutool-all        |    5.1.0    |  https://hutool.cn/docs/#/  |
| shiro-ehcache        |    1.3.2    |  http://shiro.apache.org  |
| springfox-swagger2        |    2.9.2    |  https://github.com/springfox/springfox  |
| swagger-bootstrap-ui        |    1.9.6    |  https://git.oschina.net/xiaoym/swagger-bootstrap-ui  |
| qiniu-java-sdk        |    [7.2.0, 7.2.99]    |  https://github.com/qiniu/java-sdk  |
| vue-element-admin        |    last-version    |  https://github.com/PanJiaChen/vue-element-admin  |

### 预览图片
![用户管理页面][1]

![角色管理][2]

![部门管理][3]

![权限管理][4]

![字典管理][5]

![登录日志][6]

![操作日志][7]

![系统参数配置页面][8]

![任务管理][9]

![文章管理][10]

![编辑文章][11]

![文章分类管理][12]

  [1]: http://upload.longmarch.top/2.jpg
  [2]: http://upload.longmarch.top/13.jpg
  [3]: http://upload.longmarch.top/11.jpg
  [4]: http://upload.longmarch.top/12.jpg
  [5]: http://upload.longmarch.top/14.jpg
  [6]: http://upload.longmarch.top/15.jpg
  [7]: http://upload.longmarch.top/16.jpg
  [8]: http://upload.longmarch.top/3.jpg
  [9]: http://upload.longmarch.top/17.jpg
  [10]: http://upload.longmarch.top/18.png
  [11]: http://upload.longmarch.top/19.jpg
  [12]: http://upload.longmarch.top/20.png
  