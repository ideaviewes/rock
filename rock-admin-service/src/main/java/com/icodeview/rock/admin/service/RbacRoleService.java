package com.icodeview.rock.admin.service;

import com.icodeview.rock.admin.dto.RoleDto;
import com.icodeview.rock.admin.pojo.RbacRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface RbacRoleService extends IService<RbacRole> {
    List<String> getRoleByIds(List<Integer> roleIds);
    void createRole(RoleDto dto);
    void updateRole(RoleDto dto);
    void deleteRole(Long id);
    List<RbacRole> getIndex();
}
