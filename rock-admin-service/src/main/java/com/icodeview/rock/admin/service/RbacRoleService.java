package com.icodeview.rock.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.icodeview.rock.admin.dto.RbacRoleDto;
import com.icodeview.rock.admin.pojo.RbacPermission;
import com.icodeview.rock.admin.pojo.RbacRole;

import java.util.List;

/**
 *
 */
public interface RbacRoleService extends IService<RbacRole> {
    List<String> getRoleByIds(List<Integer> roleIds);
    void createRole(RbacRoleDto dto);
    void updateRole(RbacRoleDto dto);
    void deleteRole(Long id);
    List<RbacRole> getIndex();
}
