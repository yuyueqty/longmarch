# longmarch

**长征CMS**

```
<video id="video" controls="" preload="none" poster="http://media.w3.org/2010/05/sintel/poster.png">
  <source id="mp4" src="http://gslb.miaopai.com/stream/PAEyMDoxMSB9hV6BVT1l5SHT-sMVVRVgHlL7bA__.mp4?mpflag=64&amp;vend=1&amp;os=3&amp;partner=1&amp;platform=2&amp;cookie_id=&amp;refer=miaopai&amp;scid=PAEyMDoxMSB9hV6BVT1l5SHT-sMVVRVgHlL7bA__ " type="video/mp4">
</video>
```
演示地址：`http://www.longmarch.top`

账号密码：`admin/111111`

建议：为防止其他人修改密码，最好自己注册账号。

视频操作演示：http://www.56.com/u61/v_MTYyMzg3Nzcw.html

项目采用前后端分离架构

前端使用Vue(vue-element-admin)

后端使用SpringBoot+MyBatis-pus+Shiro

Shiro认证授权，系统字典，系统参数统一使用缓存（ehcache）

工具包使用hutool

文件存储使用七牛空间

已实现功能：
1. 系统认证授权
2. 用户管理
3. 角色管理
4. 权限管理
5. 字典管理
6. 参数管理
7. 日志管理（登录日志/操作日志）
8. 任务管理
9. 内容管理
    9.1 分类管理
    9.2 文章管理
