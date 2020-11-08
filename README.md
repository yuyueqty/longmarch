
# 简介
长征后台管理系统，采用前后端分离技术，后端SpringBoot+Mybatis-plus+Shiro框架，前端vue-element-admin框架，代码规范，学习简单，易于扩展，可作为基础框架使用。

------
### 项目演示
在线演示：https://www.longmarch.top  
后端仓库：https://github.com/yuyueqty/longmarch  
前端仓库：https://github.com/yuyueqty/longmarch-web  

### 系统特色

 - 前后端分离  
    后端：SpringBoot+Mybatis-pus+Shiro  
    前端：vue-element-admin 
 - 后端代码按照业务模块进行分层，方便后期项目拆分和扩展  
 - RBAC权限管理 控制到按钮级权限（实现无权限时按钮不可见）  
 - 实现路由动态加载，可通过后台页面进行管理  
 - 数据权限过滤，实现个人，部门，全部三种数据权限范围  
 - 前后端字典统一管理，前端动态获取字典实现Code和label自动转换  
 - 前后端代码自动生成工具，统一代码风格  
 - 日志记录使用注解方式，AOP进行统一拦截
 - 统一异常捕获，统一处理  
 - Spring Cache与Shiro Cache对接，统一管理，系统缓存，ehcache，
    redis三者缓存随意切换，可满足不同场景

### 已实现功能

 1. 系统管理  
    1.1 用户管理  
    1.2 角色管理  
    1.3 部门管理  
    1.4 权限管理  
    1.5 参数管理  
    1.6 字典管理  
 2. 监控管理  
    2.1 在线管理  
    2.2 操作日志    
    2.3 登录日志  
    2.4 SQL监控  
    2.5 API接口  
 3. 系统工具  
    3.1 任务管理  
    3.2 代码生成  
 4. 组件管理  
    4.1 图标  
    4.2 富文本编辑  
    4.3 Markdown  
 5. 内容管理  
    5.1 会员管理  
    5.2 文章标签  
    5.3 文章分类  
    5.4 文章管理  
 6. 微信管理  
    6.1 公众号管理  
    6.2 智能粉丝标签  
    6.3 标签规则  
 7. 抖音管理  
    7.1 抖音账号管理  
    7.2 抖音粉丝管理  
    7.3 抖音视频管理  
 
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

### 预览图片（更多内容请登录系统查看）
![用户管理][2]

![角色管理][3]

![部门管理][4]

![权限管理][5]

![字典管理][6]

![登录日志][7]

![操作日志][8]

![系统参数配置页面][9]

![任务管理][10]

![文章管理][11]

![编辑文章][12]

![文章分类管理][13]


  [2]: http://upload.longmarch.top/21.png
  [3]: http://upload.longmarch.top/13.jpg
  [4]: http://upload.longmarch.top/11.jpg
  [5]: http://upload.longmarch.top/12.jpg
  [6]: http://upload.longmarch.top/14.jpg
  [7]: http://upload.longmarch.top/15.jpg
  [8]: http://upload.longmarch.top/16.jpg
  [9]: http://upload.longmarch.top/3.jpg
  [10]: http://upload.longmarch.top/17.jpg
  [11]: http://upload.longmarch.top/18.png
  [12]: http://upload.longmarch.top/19.jpg
  [13]: http://upload.longmarch.top/20.png
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
  
