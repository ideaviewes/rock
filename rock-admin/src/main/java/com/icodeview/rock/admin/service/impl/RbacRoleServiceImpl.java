package com.icodeview.rock.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icodeview.rock.admin.dto.RbacPermissionRoleAuthDto;
import com.icodeview.rock.admin.dto.RbacRoleDto;
import com.icodeview.rock.admin.mapper.RbacRoleMapper;
import com.icodeview.rock.admin.pojo.RbacPermission;
import com.icodeview.rock.admin.pojo.RbacRole;
import com.icodeview.rock.admin.pojo.RbacRolePermission;
import com.icodeview.rock.admin.pojo.RbacUserRole;
import com.icodeview.rock.admin.service.RbacRolePermissionService;
import com.icodeview.rock.admin.service.RbacRoleService;
import com.icodeview.rock.admin.service.RbacUserRoleService;
import com.icodeview.rock.exception.BadHttpRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class RbacRoleServiceImpl extends ServiceImpl<RbacRoleMapper, RbacRole>
        implements RbacRoleService{
    @Resource
    private RbacRoleMapper rbacRoleMapper;
    @Resource
    private RbacUserRoleService rbacUserRoleService;
    @Resource
    private RbacRolePermissionService rbacRolePermissionService;
    @Override
    public List<String> getRoleByIds(List<Integer> roleIds) {
        return lambdaQuery().in(roleIds != null && !roleIds.isEmpty(), RbacRole::getId,roleIds)
                .select(RbacRole::getCode)
                .list()
                .stream().map(RbacRole::getCode).collect(Collectors.toList());
    }

    @Override
    public void createRole(RbacRoleDto dto) {
        RbacRole rbacRole = BeanUtil.copyProperties(dto, RbacRole.class);
        save(rbacRole);
    }

    @Override
    public void updateRole(RbacRoleDto dto) {
        if(dto.getId()==null || dto.getId()==0){
            throw new BadHttpRequestException("请填写角色id！");
        }
        RbacRole rbacRole = BeanUtil.copyProperties(dto, RbacRole.class);
        updateById(rbacRole);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteRole(Long id) {
        RbacRole rbacRole = getById(id);
        Integer count = rbacUserRoleService.lambdaQuery().eq(RbacUserRole::getRoleId, id).count();
        if(count>0){
            throw new BadHttpRequestException("请移除所有管理员的"+rbacRole.getName()+"角色后，再删除该角色！");
        }
        removeById(id);
    }

    @Override
    public List<RbacRole> getIndex() {
        return lambdaQuery().orderByAsc(RbacRole::getId).list();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void authPermission(RbacPermissionRoleAuthDto dto) {
        Integer roleId = dto.getRoleId();
        List<Integer> permissionIds = dto.getPermissionIds();
        if(permissionIds.isEmpty()){
            throw new BadHttpRequestException("请选择要分配的权限！");
        }
        rbacRolePermissionService.lambdaUpdate().eq(RbacRolePermission::getRoleId,dto.getRoleId())
                .remove();
        ArrayList<RbacRolePermission> rbacRolePermissions = new ArrayList<>();
        permissionIds.forEach(permissionId->{
            RbacRolePermission rbacRolePermission = new RbacRolePermission();
            rbacRolePermission.setRoleId(roleId);
            rbacRolePermission.setPermissionId(permissionId);
            rbacRolePermissions.add(rbacRolePermission);
        });
        rbacRolePermissionService.saveBatch(rbacRolePermissions);
    }

    @Override
    public List<Integer> getPermissionIdsByRoleId(Integer roleId) {
         return rbacRolePermissionService.getPermissionIdsByRoleIds(Collections.singletonList(roleId));
    }
}




