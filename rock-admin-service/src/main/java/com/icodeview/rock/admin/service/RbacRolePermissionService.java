package com.icodeview.rock.admin.service;

import com.icodeview.rock.admin.pojo.RbacRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface RbacRolePermissionService extends IService<RbacRolePermission> {
    List<String> getPermissionByRoleIds(List<Integer> roleIds);
}
