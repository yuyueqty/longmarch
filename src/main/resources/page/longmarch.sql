INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `path`, `component`, `redirect`, `name`, `hidden`, `title`, `icon`, `cache`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '会员管理', NULL, 'test:member:manage', 1, 1, 'member', 'member/index', '', 'MemberManage', 0, 'memberManage', 'user', 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '查看', NULL, 'test:member:show', 2, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '添加', NULL, 'test:member:create', 2, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '修改', NULL, 'test:member:update', 2, 1, 1, 1, NOW());

INSERT INTO sys_permission(`parent_id`, `parent_ids`, `permission_name`, `description`, `permission_string`, `type`, `status`, `sort`, `create_by`, `create_time`)
VALUES (0, 'NaN', '删除', NULL, 'test:member:delete', 2, 1, 1, 1, NOW());
