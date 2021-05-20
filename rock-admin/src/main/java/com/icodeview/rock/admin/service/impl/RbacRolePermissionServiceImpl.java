package com.icodeview.rock.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icodeview.rock.admin.dto.RbacPermissionRoleAuthDto;
import com.icodeview.rock.admin.pojo.RbacRolePermission;
import com.icodeview.rock.admin.service.RbacRolePermissionService;
import com.icodeview.rock.admin.mapper.RbacRolePermissionMapper;
import com.icodeview.rock.exception.BadHttpRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Integer> getPermissionIdsByRoleIds(List<Integer> roleIds) {
        if(roleIds.isEmpty()){
            return new ArrayList<>();
        }
        return lambdaQuery().in(RbacRolePermission::getRoleId, roleIds)
                .select(RbacRolePermission::getPermissionId)
                .list().stream().mapToInt(RbacRolePermission::getPermissionId).boxed().collect(Collectors.toList());
    }
}




