package com.icodeview.rock.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icodeview.rock.admin.dto.RbacPermissionDto;
import com.icodeview.rock.admin.pojo.RbacPermission;
import com.icodeview.rock.admin.pojo.RbacRolePermission;
import com.icodeview.rock.admin.service.RbacPermissionService;
import com.icodeview.rock.admin.mapper.RbacPermissionMapper;
import com.icodeview.rock.admin.service.RbacRolePermissionService;
import com.icodeview.rock.admin.vo.MenuDataItem;
import com.icodeview.rock.exception.BadHttpRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Service
@Slf4j
public class RbacPermissionServiceImpl extends ServiceImpl<RbacPermissionMapper, RbacPermission>
        implements RbacPermissionService {
    @Resource
    @Lazy
    private RbacRolePermissionService rbacRolePermissionService;

    @Override
    public void createPermission(RbacPermissionDto dto) {
        RbacPermission permission = BeanUtil.copyProperties(dto, RbacPermission.class);
        permission.setCreatedAt(LocalDateTime.now());
        save(permission);
    }

    @Override
    public void updatePermission(RbacPermissionDto dto) {
        if (dto.getId() == null || dto.getId() == 0) {
            throw new BadHttpRequestException("权限id错误！");
        }
        RbacPermission permission = BeanUtil.copyProperties(dto, RbacPermission.class);
        permission.setUpdatedAt(LocalDateTime.now());
        updateById(permission);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        Integer childrenNum = lambdaQuery().eq(RbacPermission::getParentId, id).count();
        if(childrenNum>0){
            throw new BadHttpRequestException("请移除该权限的所有子权限后，再该删除该权限！");
        }
        Integer count = rbacRolePermissionService.lambdaQuery().eq(RbacRolePermission::getPermissionId, id).count();
        if (count > 0) {
            throw new BadHttpRequestException("请移除所有角色的该权限后，再该删除该权限！");
        }
        removeById(id);
    }

    @Override
    public List<RbacPermission> getIndex() {
        List<RbacPermission> list = lambdaQuery().list();
        return getRbacPermissions(list);
    }

    @Override
    public List<MenuDataItem> getMenuByIds(List<Integer> ids) {
        if(ids.isEmpty()){
            return new ArrayList<>();
        }
        List<RbacPermission> list = lambdaQuery().in(RbacPermission::getId, ids).list();
        return getMenuList(list);
    }

    private List<RbacPermission> getRbacPermissions(List<RbacPermission> list) {
        List<RbacPermission> roots = list.stream()
                .filter(rbacPermission -> rbacPermission.getParentId() == 0)
                .sorted(Comparator.comparing(RbacPermission::getPriority).reversed())
                .collect(Collectors.toList());

        List<RbacPermission> sub = list.stream().filter(rbacPermission -> rbacPermission.getParentId() != 0)
                .sorted(Comparator.comparing(RbacPermission::getPriority).reversed())
                .collect(Collectors.toList());
        roots.forEach(root -> buildSub(root, sub));
        return roots;
    }


    private void buildSub(RbacPermission parent, List<RbacPermission> subs) {
        List<RbacPermission> children = subs.stream()
                .filter(sub -> (sub.getParentId().equals(parent.getId())))
                .peek(permission -> {
                    permission.setParentKeys(Collections.singletonList(parent.getUrl()));
                })
                .collect(Collectors.toList());
        if (!children.isEmpty()) {
            parent.setChildren(children);
            children.forEach(child -> buildSub(child, subs));
        }
    }

    public List<MenuDataItem> getMenuList(List<RbacPermission> permissions){
        List<MenuDataItem> list = permissions.stream().map(this::generateMenuItem).collect(Collectors.toList());
        List<MenuDataItem> roots = list.stream().filter(item -> item.getParentId() == 0).collect(Collectors.toList());
        List<MenuDataItem> sub = list.stream().filter(item -> item.getParentId() != 0).collect(Collectors.toList());


        roots.forEach(root->getSubMenu(root,sub));
        return roots;
    }

    public void getSubMenu(MenuDataItem parent,List<MenuDataItem> subs){
        List<MenuDataItem> children = subs.stream().filter(sub -> sub.getParentId().equals(parent.getId()))
                .peek(menuDataItem -> {
                    menuDataItem.setParentKeys(Collections.singletonList(parent.getKey()));
                }).collect(Collectors.toList());
        if(!children.isEmpty()){
            parent.setChildren(children);
            children.forEach(child->{
                getSubMenu(child,subs);
            });
        }
    }

    private MenuDataItem generateMenuItem(RbacPermission permission){
        MenuDataItem item = new MenuDataItem();
        item.setId(permission.getId());
        item.setKey(permission.getUrl());
        item.setParentId(permission.getParentId());
        item.setName(permission.getName());
        item.setIcon(permission.getIcon());
        item.setPath(permission.getUrl());
        item.setHideInMenu(false);
        item.setHideChildrenInMenu(false);
        if(permission.getHideInMenu()==1){
            item.setHideInMenu(true);
        }
        if(permission.getHideChildrenInMenu()==1){
            item.setHideChildrenInMenu(true);
        }
        return item;
    }
}




