package com.icodeview.rock.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icodeview.rock.admin.dto.RoleDto;
import com.icodeview.rock.admin.pojo.RbacRole;
import com.icodeview.rock.admin.pojo.RbacUserRole;
import com.icodeview.rock.admin.service.RbacRoleService;
import com.icodeview.rock.admin.mapper.RbacRoleMapper;
import com.icodeview.rock.admin.service.RbacUserRoleService;
import com.icodeview.rock.exception.BadHttpRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class RbacRoleServiceImpl extends ServiceImpl<RbacRoleMapper, RbacRole>
        implements RbacRoleService{
    @Resource
    private RbacUserRoleService rbacUserRoleService;
    @Override
    public List<String> getRoleByIds(List<Integer> roleIds) {
        return lambdaQuery().in(roleIds != null && !roleIds.isEmpty(), RbacRole::getId,roleIds)
                .select(RbacRole::getCode)
                .list()
                .stream().map(RbacRole::getCode).collect(Collectors.toList());
    }

    @Override
    public void createRole(RoleDto dto) {
        RbacRole rbacRole = BeanUtil.copyProperties(dto, RbacRole.class);
        save(rbacRole);
    }

    @Override
    public void updateRole(RoleDto dto) {
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
        return lambdaQuery().orderByDesc(RbacRole::getId).list();
    }
}




