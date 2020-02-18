INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `hidden`, `title`, `icon`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '${table.comment}管理', NULL, '${package.ModuleName}:${table.entityName?lower_case}:manage', 1, 1, '${table.entityName?lower_case}', '<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/index', '', '${table.entityName}Manage', 0, '${table.entityName?uncap_first}Manage', 'user', 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '查看', NULL, '${package.ModuleName}:${table.entityName?lower_case}:show', 2, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '添加', NULL, '${package.ModuleName}:${table.entityName?lower_case}:create', 2, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '修改', NULL, '${package.ModuleName}:${table.entityName?lower_case}:update', 2, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '删除', NULL, '${package.ModuleName}:${table.entityName?lower_case}:delete', 2, 1, 1, 1, NOW());
