package com.icodeview.rock.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icodeview.rock.admin.pojo.RbacRolePermission;
import com.icodeview.rock.admin.service.RbacRolePermissionService;
import com.icodeview.rock.admin.mapper.RbacRolePermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Service
public class RbacRolePermissionServiceImpl extends ServiceImpl<RbacRolePermissionMapper, RbacRolePermission>
        implements RbacRolePermissionService{
    @Resource
    private RbacRolePermissionMapper rbacRolePermissionMapper;
    @Override
    public List<String> getPermissionByRoleIds(List<Integer> roleIds) {
        return rbacRolePermissionMapper.getPermissionUrl(roleIds);
    }
}




