package com.icodeview.rock.admin.service;

import com.icodeview.rock.admin.pojo.RbacUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface RbacUserRoleService extends IService<RbacUserRole> {
    List<Integer> getRoleIdByUserId(Integer userId);
    void attachRole(Integer userId,Integer roleId);
    void detachRole(Integer userId);
}
