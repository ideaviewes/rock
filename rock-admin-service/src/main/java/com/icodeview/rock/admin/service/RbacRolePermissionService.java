package com.icodeview.rock.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.icodeview.rock.admin.dto.RbacPermissionRoleAuthDto;
import com.icodeview.rock.admin.pojo.RbacRolePermission;

import java.util.List;

/**
 *
 */
public interface RbacRolePermissionService extends IService<RbacRolePermission> {
    List<String> getPermissionByRoleIds(List<Integer> roleIds);
    List<Integer> getPermissionIdsByRoleIds(List<Integer> roleIds);
}
