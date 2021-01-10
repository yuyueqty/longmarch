TRUNCATE TABLE sys_permission;
-- 系统管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '系统管理', '系统管理', 'sys:manage', 1, 1, '/system', 'Layout', '/system/user', 'System', 'system', 'setting', 0, 1, 100, 1, NOW());

-- 用户管理菜单ID
SELECT @systemId := LAST_INSERT_ID();

-- 用户管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@systemId, @systemId, '用户管理', '用户管理', 'sys:user:manage', 1, 1, 'user', 'user/index', '', 'UserManage', 'userManage', 'user', 0, 1, 110, 1, NOW());

-- 用户管理菜单ID
SELECT @userId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@userId, CONCAT(@systemId,',',@userId), '查看用户', '查看用户', 'sys:user:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@userId, CONCAT(@systemId,',',@userId), '添加用户', '添加用户', 'sys:user:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@userId, CONCAT(@systemId,',',@userId), '修改用户', '修改用户', 'sys:user:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@userId, CONCAT(@systemId,',',@userId), '删除用户', '删除用户', 'sys:user:delete', 2, 1, 0, 1, 4, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@userId, CONCAT(@systemId,',',@userId), '修改密码', '修改密码', 'sys:user:change:password', 2, 1, 0, 1, 5, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@userId, CONCAT(@systemId,',',@userId), '导出用户', '导出用户', 'sys:user:export', 2, 1, 0, 1, 6, 1, NOW());

-- 角色管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@systemId, @systemId, '角色管理', '角色管理', 'sys:role:manage', 1, 1, 'role', 'role/index', '', 'RoleManage', 'roleManage', 'peoples', 0, 1, 120, 1, NOW());

-- 角色管理菜单ID
SELECT @roleId := LAST_INSERT_ID();

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@roleId, CONCAT(@systemId,',',@roleId), '查看角色', '查看角色', 'sys:role:show', 2, 1, 0, 1, 300, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@roleId, CONCAT(@systemId,',',@roleId), '添加角色', '添加角色', 'sys:role:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@roleId, CONCAT(@systemId,',',@roleId), '修改角色', '修改角色', 'sys:role:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@roleId, CONCAT(@systemId,',',@roleId), '删除角色', '删除角色', 'sys:role:delete', 2, 1, 0, 1, 4, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@roleId, CONCAT(@systemId,',',@roleId), '导出角色', '导出角色', 'sys:role:export', 2, 1, 0, 1, 5, 1, NOW());

-- 部门管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@systemId, @systemId, '部门管理', '部门管理', 'sys:department:manage', 1, 1, 'department', 'department/index', '', 'DepartmentManage', 'departmentManage', 'tree', 0, 1, 130, 1, NOW());

-- 部门管理菜单ID
SELECT @departmentId := LAST_INSERT_ID();

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@departmentId, CONCAT(@systemId,',',@departmentId), '查看部门', '查看部门', 'sys:department:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@departmentId, CONCAT(@systemId,',',@departmentId), '添加部门', '添加部门', 'sys:department:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@departmentId, CONCAT(@systemId,',',@departmentId), '修改部门', '修改部门', 'sys:department:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@departmentId, CONCAT(@systemId,',',@departmentId), '删除部门', '删除部门', 'sys:department:delete', 2, 1, 0, 1, 4, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@departmentId, CONCAT(@systemId,',',@departmentId), '导出部门', '导出部门', 'sys:department:export', 2, 1, 0, 1, 5, 1, NOW());

-- 权限管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@systemId, @systemId, '权限管理', '权限管理', 'sys:permission:manage', 1, 1, 'permission', 'permission/index', '', 'PermissionManage', 'permissionManage', 'lock', 0, 1, 140, 1, NOW());

-- 权限管理菜单ID
SELECT @permissionId := LAST_INSERT_ID();

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@permissionId, CONCAT(@systemId,',',@permissionId), '查看权限', '查看权限', 'sys:permission:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@permissionId, CONCAT(@systemId,',',@permissionId), '添加权限', '添加权限', 'sys:permission:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@permissionId, CONCAT(@systemId,',',@permissionId), '修改权限', '修改权限', 'sys:permission:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@permissionId, CONCAT(@systemId,',',@permissionId), '删除权限', '删除权限', 'sys:permission:delete', 2, 1, 0, 1, 4, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@permissionId, CONCAT(@systemId,',',@permissionId), '导出权限', '导出权限', 'sys:permission:export', 2, 1, 0, 1, 5, 1, NOW());

-- 参数管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@systemId, @systemId, '参数管理', '参数管理', 'sys:parameter:manage', 1, 1, 'parameter', 'parameter/index', '', 'ParameterManage', 'parameterManage', 'parameter', 0, 1, 150, 1, NOW());

-- 参数管理菜单ID
SELECT @parameterId := LAST_INSERT_ID();

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@parameterId, CONCAT(@systemId,',',@parameterId), '查看参数', '查看参数', 'sys:parameter:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@parameterId, CONCAT(@systemId,',',@parameterId), '添加参数', '添加参数', 'sys:parameter:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@parameterId, CONCAT(@systemId,',',@parameterId), '修改参数', '修改参数', 'sys:parameter:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@parameterId, CONCAT(@systemId,',',@parameterId), '删除参数', '删除参数', 'sys:parameter:delete', 2, 1, 0, 1, 4, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@parameterId, CONCAT(@systemId,',',@parameterId), '导出参数', '导出参数', 'sys:parameter:export', 2, 1, 0, 1, 5, 1, NOW());

-- 字典管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@systemId, @systemId, '字典管理', '字典管理', 'sys:dictionary:manage', 1, 1, 'dictionary', 'dictionary/index', '', 'DictionaryManage', 'dictionaryManage', 'dictionary', 0, 1, 160, 1, NOW());

-- 字典管理菜单ID
SELECT @dictionaryId := LAST_INSERT_ID();

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@dictionaryId, CONCAT(@systemId,',',@dictionaryId), '查看字典', '查看字典', 'sys:dictionary:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@dictionaryId, CONCAT(@systemId,',',@dictionaryId), '添加字典', '添加字典', 'sys:dictionary:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@dictionaryId, CONCAT(@systemId,',',@dictionaryId), '修改字典', '修改字典', 'sys:dictionary:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@dictionaryId, CONCAT(@systemId,',',@dictionaryId), '删除字典', '删除字典', 'sys:dictionary:delete', 2, 1, 0, 1, 4, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@dictionaryId, CONCAT(@systemId,',',@dictionaryId), '导出字典', '导出字典', 'sys:dictionary:export', 2, 1, 0, 1, 5, 1, NOW());

-- 系统监控
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '系统监控', '系统监控', 'sys:monitoring:manage', 1, 1, '/monitoring', 'Layout', '/monitoring/onlineuser', 'SysMonitor', 'sysMonitor', 'monitor', 0, 1, 200, 1, NOW());

-- 在线管理菜单ID
SELECT @monitoringId := LAST_INSERT_ID();

-- 在线用户管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@monitoringId, @monitoringId, '在线管理', '在线管理', 'sys:monitoring:onlineuser:manage', 1, 1, 'onlineuser', 'user/onlineUser', '', 'OnlineUserManage', 'onlineUserManage', 'onlineuser', 0, 1, 210, 1, NOW());

-- 用户管理菜单ID
SELECT @onlineUserId := LAST_INSERT_ID();

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@onlineUserId, CONCAT(@monitoringId,',',@onlineUserId), '踢出用户', '踢出用户', 'sys:kickout:onlineuser', 2, 1, 0, 1, 1, 1, NOW());

-- 操作日志
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@monitoringId, @monitoringId, '操作日志', '操作日志', 'sys:operatelog:manage', 1, 1, 'operatelog', 'log/loginLog', '', 'OperateLogManage', 'operateLogManage', 'log', 0, 1, 220, 1, NOW());

-- 操作日志菜单ID
SELECT @operatelogId := LAST_INSERT_ID();

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@operatelogId, CONCAT(@systemId,',',@operatelogId), '查看日志', '查看日志', 'sys:operateLog:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@operatelogId, CONCAT(@systemId,',',@operatelogId), '添加日志', '添加日志', 'sys:operateLog:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@operatelogId, CONCAT(@systemId,',',@operatelogId), '修改日志', '修改日志', 'sys:operateLog:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@operatelogId, CONCAT(@systemId,',',@operatelogId), '删除日志', '删除日志', 'sys:operateLog:delete', 2, 1, 0, 1, 4, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@operatelogId, CONCAT(@systemId,',',@operatelogId), '导出日志', '导出日志', 'sys:operateLog:export', 2, 1, 0, 1, 5, 1, NOW());

-- 登录日志
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@monitoringId, @monitoringId, '登录日志', '登录日志', 'sys:loginlog:manage', 1, 1, 'loginlog', 'log/loginLog', '', 'LoginLogManage', 'loginLogManage', 'login-log', 0, 1, 230, 1, NOW());

-- 登录日志菜单ID
SELECT @loginlogId := LAST_INSERT_ID();

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@loginlogId, CONCAT(@systemId,',',@loginlogId), '查看日志', '查看日志', 'sys:loginLog:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@loginlogId, CONCAT(@systemId,',',@loginlogId), '添加日志', '添加日志', 'sys:loginLog:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@loginlogId, CONCAT(@systemId,',',@loginlogId), '修改日志', '修改日志', 'sys:loginLog:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@loginlogId, CONCAT(@systemId,',',@loginlogId), '删除日志', '删除日志', 'sys:loginLog:delete', 2, 1, 0, 1, 4, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@loginlogId, CONCAT(@systemId,',',@loginlogId), '导出日志', '导出日志', 'sys:loginLog:export', 2, 1, 0, 1, 5, 1, NOW());

-- SQL监控
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@monitoringId, @monitoringId, 'SQL监控', 'SQL监控', 'monitoring:sql:manage', 1, 1, 'onlineuser', 'druid/login', '', 'SqlMonitor', 'sqlMonitor', 'sql', 0, 1, 210, 1, NOW());

-- API接口
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@monitoringId, @monitoringId, 'API接口', 'API接口', 'monitoring:api:manage', 1, 1, 'api', 'druid/login', '', 'SwaggerApi', 'swaggerApi', 'api', 0, 1, 210, 1, NOW());

-- 系统工具
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '系统工具', '系统工具', 'tool:manage', 1, 1, '/tool', 'Layout', '', 'SystemTool', 'systemTool', 'tool', 0, 1, 300, 1, NOW());

-- 系统工具菜单ID
SELECT @toolId := LAST_INSERT_ID();

-- 任务管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@toolId, @toolId, '任务管理', '任务管理', 'tool:job:manage', 1, 1, 'schedule', 'job/schedule', '', 'ScheduleManage', 'scheduleManage', 'schedule', 0, 1, 310, 1, NOW());

-- 任务管理菜单ID
SELECT @jobId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@jobId, CONCAT(@toolId,',',@jobId), '查看任务', '查看任务', 'job:schedule:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@jobId, CONCAT(@toolId,',',@jobId), '创建任务', '创建任务', 'job:schedule:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@jobId, CONCAT(@toolId,',',@jobId), '修改任务', '修改任务', 'job:schedule:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@jobId, CONCAT(@toolId,',',@jobId), '删除任务', '删除任务', 'job:schedule:delete', 2, 1, 0, 1, 4, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@jobId, CONCAT(@toolId,',',@jobId), '立即任务', '立即任务', 'job:schedule:run', 2, 1, 0, 1, 5, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@jobId, CONCAT(@toolId,',',@jobId), '暂停任务', '暂停任务', 'job:schedule:pause', 2, 1, 0, 1, 6, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@jobId, CONCAT(@toolId,',',@jobId), '恢复任务', '恢复任务', 'job:schedule:resume', 2, 1, 0, 1, 7, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@jobId, CONCAT(@toolId,',',@jobId), '重置任务', '重置任务', 'job:schedule:reset', 2, 1, 0, 1, 8, 1, NOW());

-- 代码生成
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@toolId, @toolId, '代码生成', '代码生成', 'sys:generator:manage', 1, 1, 'generator', 'generator/table', '', 'Generator', 'generator', 'generator', 0, 1, 320, 1, NOW());

-- 代码生成菜单ID
SELECT @codeId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@codeId, CONCAT(@toolId,',',@codeId), '配置', '配置', 'sys:generator:update', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@jobId, CONCAT(@toolId,',',@jobId), '下载', '下载', 'sys:generator:download', 2, 1, 0, 1, 2, 1, NOW());

-- 组件管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '组件管理', '组件管理', 'components:manage', 1, 1, '/components', 'Layout', '', 'Components', 'components', 'component', 0, 1, 400, 1, NOW());

-- 组件管理菜单ID
SELECT @componentsId := LAST_INSERT_ID();

-- 任务管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@componentsId, @componentsId, '图标', '图标', 'components:icons:manage', 1, 1, 'icons', 'icons/index', '', 'Icons', 'icons', 'icon', 0, 1, 410, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@componentsId, @componentsId, '富文本编辑', '富文本编辑', 'components:tinymce:manage', 1, 1, 'tinymce', 'components-demo/tinymce', '', 'Tinymce', 'tinymce', 'fwb', 0, 1, 420, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@componentsId, @componentsId, 'Markdown', 'Markdown', 'components:markdown:manage', 1, 1, 'markdown', 'components-demo/markdown', '', 'Markdown', 'markdown', 'markdown', 0, 1, 430, 1, NOW());

-- 内容管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '内容管理', '内容管理', 'cms:manage', 1, 1, '/cms', 'Layout', 'cms/article', 'CmsManage', 'cmsManage', 'cms', 0, 1, 500, 1, NOW());

-- 系统工具菜单ID
SELECT @cmsId := LAST_INSERT_ID();

-- 文章管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@cmsId, @cmsId, '文章管理', '文章管理', 'cms:article:manage', 1, 1, 'article', 'cms/article', '', 'ArticleManage', 'articleManage', 'article', 0, 1, 510, 1, NOW());

-- 文章管理菜单ID
SELECT @articleId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@articleId, CONCAT(@cmsId,',',@articleId), '查看文章', '查看文章', 'cms:article:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@articleId, CONCAT(@cmsId,',',@articleId), '创建文章', '创建文章', 'cms:article:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@articleId, CONCAT(@cmsId,',',@articleId), '修改文章', '修改文章', 'cms:article:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@articleId, CONCAT(@cmsId,',',@articleId), '删除文章', '删除文章', 'cms:article:delete', 2, 1, 0, 1, 4, 1, NOW());

-- 文章分类
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@cmsId, @cmsId, '文章分类', '文章分类', 'cms:category:manage', 1, 1, 'category', 'cms/category', '', 'CategoryManage', 'categoryManage', 'category', 0, 1, 520, 1, NOW());

-- 文章分类菜单ID
SELECT @categoryId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@categoryId, CONCAT(@cmsId,',',@categoryId), '查看分类', '查看分类', 'cms:category:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@categoryId, CONCAT(@cmsId,',',@categoryId), '创建分类', '创建分类', 'cms:category:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@categoryId, CONCAT(@cmsId,',',@categoryId), '修改分类', '修改分类', 'cms:category:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@categoryId, CONCAT(@cmsId,',',@categoryId), '删除分类', '删除分类', 'cms:category:delete', 2, 1, 0, 1, 4, 1, NOW());

-- 文章标签
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@cmsId, @cmsId, '文章标签', '文章标签', 'cms:tag:manage', 1, 1, 'tag', 'cms/tag', '', 'ArticleTagManage', 'articleTagManage', 'eye-open', 0, 1, 530, 1, NOW());

-- 文章标签菜单ID
SELECT @tagId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@tagId, CONCAT(@cmsId,',',@tagId), '查看标签', '查看标签', 'cms:tag:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@tagId, CONCAT(@cmsId,',',@tagId), '创建标签', '创建标签', 'cms:tag:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@tagId, CONCAT(@cmsId,',',@tagId), '修改标签', '修改标签', 'cms:tag:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@tagId, CONCAT(@cmsId,',',@tagId), '删除标签', '删除标签', 'cms:tag:delete', 2, 1, 0, 1, 4, 1, NOW());

-- 微信管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '微信管理', '微信管理', 'wx:manage', 1, 1, '/wx', 'Layout', '/wx/wxgzh', 'Wx_manage', 'wx_manage', 'wechat', 0, 1, 600, 1, NOW());

-- 微信管理菜单ID
SELECT @wxId := LAST_INSERT_ID();

-- 公众号管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@wxId, @wxId, '公众号管理', '公众号管理', 'wx:gzhAccount:manage', 1, 1, 'gzhaccount', 'wx/wxgzh', '', 'GzhAccountManage', 'gzhAccountManage', 'gzh', 0, 1, 610, 1, NOW());

-- 公众号管理菜单ID
SELECT @gzhId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@gzhId, CONCAT(@wxId,',',@gzhId), '查看公众号', '查看公众号', 'wx:gzhAccount:show', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@gzhId, CONCAT(@wxId,',',@gzhId), '创建公众号', '创建公众号', 'wx:gzhAccount:create', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@gzhId, CONCAT(@wxId,',',@gzhId), '修改公众号', '修改公众号', 'wx:gzhAccount:update', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@gzhId, CONCAT(@wxId,',',@gzhId), '删除公众号', '删除公众号', 'wx:gzhAccount:delete', 2, 1, 0, 1, 4, 1, NOW());

-- 粉丝管理
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@wxId, @wxId, '粉丝管理', '粉丝管理', 'wx:gzhuser:manage', 1, 1, 'gzhuser', 'wx/wxuser', '', 'GzhUserManage', 'gzhUserManage', 'fensi', 0, 1, 620, 1, NOW());

-- 粉丝管理菜单ID
SELECT @gzhuserId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@gzhuserId, CONCAT(@wxId,',',@gzhuserId), '同步微信用户', '同步微信用户', 'wx:gzhuser:sync:all', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@gzhuserId, CONCAT(@wxId,',',@gzhuserId), '解析分维标签', '解析分维标签', 'wx:gzhuser:analyse:all', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@gzhuserId, CONCAT(@wxId,',',@gzhuserId), '解析营销标签', '解析营销标签', 'wx:gzhuser:analysis', 2, 1, 0, 1, 3, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@gzhuserId, CONCAT(@wxId,',',@gzhuserId), '取消用户标签', '取消用户标签', 'wx:gzhuser:remove', 2, 1, 0, 1, 4, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@gzhuserId, CONCAT(@wxId,',',@gzhuserId), '下载用户标签', '下载用户标签', 'wx:gzhuser:download', 2, 1, 0, 1, 4, 1, NOW());

-- 标签规则
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `title`, `icon`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@wxId, @wxId, '标签规则', '标签规则', 'wx:tagrule:manage', 1, 1, 'tagrule', 'wx/tagrule', '', 'GzhTagRuleManage', 'gzhTagRuleManage', 'guide', 0, 1, 630, 1, NOW());

-- 标签规则菜单ID
SELECT @tagruleId := LAST_INSERT_ID();

-- 按钮 SQL
INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@tagruleId, CONCAT(@wxId,',',@tagruleId), '创建规则', '创建规则', 'wx:gzhTag:create', 2, 1, 0, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@tagruleId, CONCAT(@wxId,',',@tagruleId), '更新规则', '更新规则', 'wx:gzhTag:update', 2, 1, 0, 1, 2, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `hidden`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (@tagruleId, CONCAT(@wxId,',',@tagruleId), '删除规则', '删除规则', 'wx:gzhTag:delete', 2, 1, 0, 1, 3, 1, NOW());






















































